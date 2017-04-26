package com.orafaaraujo.depuis.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.databinding.ItemFactBinding;
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
        factViewModel.setFact(mFacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mFacts.size();
    }

    public void updateFacts(List<Fact> facts) {
        mFacts.addAll(facts);
    }

    public void onItemDismiss(int position) {
        removerItem(position);
    }

    private void removerItem(int position) {
        mFacts.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mFacts.size());
    }

}
