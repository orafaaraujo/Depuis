package com.orafaaraujo.depuis.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.databinding.ItemFactBinding;
import com.orafaaraujo.depuis.helper.RxBus;
import com.orafaaraujo.depuis.helper.buses.FactTO;
import com.orafaaraujo.depuis.model.Fact;
import com.orafaaraujo.depuis.view.adapter.viewholder.BindingHolder;
import com.orafaaraujo.depuis.viewModel.FactViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by rafael on 18/01/17.
 */

public class FactAdapter extends RecyclerView.Adapter<BindingHolder> {

    @Inject
    Context mContext;

    @Inject
    Provider<FactViewModel> mFactViewModelProvider;

    @Inject
    RxBus mRxBus;

    private final List<Fact> mFacts;

    @Inject
    FactAdapter() {
        mFacts = new ArrayList<>(0);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFactBinding viewDataBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_fact,
                        parent,
                        false);
        return new BindingHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ItemFactBinding binding = holder.getBinding();
        final FactViewModel factViewModel = mFactViewModelProvider.get();
        binding.setViewModel(factViewModel);
        factViewModel.setPosition(position);
        factViewModel.setFact(mFacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mFacts.size();
    }

    public void updateFacts(List<Fact> facts) {
        mFacts.clear();
        mFacts.addAll(facts);
        // TODO If has a new Fact update a list with the current position!!!
        notifyDataSetChanged();
    }

    public void insertFact(int position, Fact fact) {
        mFacts.add(position, fact);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mFacts.size());
    }

    public void onItemDismiss(int position) {
        sendRemoveEvent(position);
        removerItem(position);
    }

    private void sendRemoveEvent(int position) {
        mRxBus.sendEvent(
                FactTO.builder()
                        .setFact(mFacts.get(position))
                        .setPosition(position)
                        .setDelete(true)
                        .setNewFact(false)
                        .setClose(false)
                        .build());
    }

    private void removerItem(int position) {
        mFacts.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mFacts.size());
    }

    public void notifyClosedFact(int position, Fact fact) {
        mFacts.remove(position);
        mFacts.add(position, fact);
        notifyItemChanged(position);
    }
}