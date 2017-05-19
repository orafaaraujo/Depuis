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
import com.orafaaraujo.depuis.model.FactModel;
import com.orafaaraujo.depuis.view.adapter.viewholder.BindingHolder;
import com.orafaaraujo.depuis.view.bindingadapter.DepuisDataBindingComponent;
import com.orafaaraujo.depuis.viewmodel.FactViewModel;

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
    DepuisDataBindingComponent mBindingComponent;

    @Inject
    Provider<FactViewModel> mFactViewModelProvider;

    @Inject
    RxBus mRxBus;

    private final List<FactModel> mFactModels;

    @Inject
    FactAdapter() {
        mFactModels = new ArrayList<>(0);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFactBinding viewDataBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_fact,
                        parent,
                        false,
                        mBindingComponent);
        return new BindingHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ItemFactBinding binding = holder.getBinding();
        final FactViewModel factViewModel = mFactViewModelProvider.get();
        binding.setViewModel(factViewModel);
        factViewModel.setPosition(position);
        factViewModel.setFactModel(mFactModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mFactModels.size();
    }

    public void updateFacts(List<FactModel> factModels) {
        mFactModels.clear();
        mFactModels.addAll(factModels);
        // TODO If has a new FactModel update a list with the current position!!!
        notifyDataSetChanged();
    }

    public void insertFact(int position, FactModel factModel) {
        mFactModels.add(position, factModel);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mFactModels.size());
    }

    public void onItemDismiss(int position) {
        sendRemoveEvent(position);
        removerItem(position);
    }

    private void sendRemoveEvent(int position) {
        mRxBus.sendEvent(
                FactTO.builder()
                        .setFact(mFactModels.get(position))
                        .setPosition(position)
                        .setDelete(true)
                        .setNewFact(false)
                        .setClose(false)
                        .build());
    }

    private void removerItem(int position) {
        mFactModels.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mFactModels.size());
    }

    public void notifyClosedFact(int position, FactModel factModel) {
        mFactModels.remove(position);
        mFactModels.add(position, factModel);
        notifyItemChanged(position);
    }
}