package com.orafaaraujo.depuis.view.bindingadapter;


/**
 * Created by Rafael Araujo on 12/05/17.
 */
public class DepuisDataBindingComponent implements android.databinding.DataBindingComponent {

    BindingAdapterHelper mBindingAdapterHelper;

    public DepuisDataBindingComponent(BindingAdapterHelper bindingAdapterHelper) {
        mBindingAdapterHelper = bindingAdapterHelper;
    }

    @Override
    public BindingAdapterHelper getBindingAdapterHelper() {
        return mBindingAdapterHelper;
    }
}
