package com.orafaaraujo.depuis.helper.bindingadapter;


import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Rafael Araujo on 12/05/17.
 */
@Singleton
public class DepuisDataBindingComponent implements android.databinding.DataBindingComponent {

    @Inject
    BindingAdapterHelper mBindingAdapterHelper;

    @Inject
    DepuisDataBindingComponent() {
    }

    @Override
    public BindingAdapterHelper getBindingAdapterHelper() {
        return mBindingAdapterHelper;
    }
}
