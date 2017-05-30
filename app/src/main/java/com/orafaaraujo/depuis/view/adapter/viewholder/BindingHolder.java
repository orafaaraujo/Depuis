package com.orafaaraujo.depuis.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;

import com.orafaaraujo.depuis.databinding.ItemFactBinding;

/**
 * Created by rafael on 19/01/17.
 */

public class BindingHolder extends RecyclerView.ViewHolder {

    private ItemFactBinding mBinding;

    public BindingHolder(ItemFactBinding binding) {
        super(binding.itemFactLayout);
        this.mBinding = binding;
    }

    public ItemFactBinding getBinding() {
        return mBinding;
    }
}
