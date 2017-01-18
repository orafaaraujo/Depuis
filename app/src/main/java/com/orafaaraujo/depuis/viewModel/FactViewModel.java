package com.orafaaraujo.depuis.viewModel;

import android.databinding.BaseObservable;

import com.orafaaraujo.depuis.model.Fact;

/**
 * Created by rafael on 18/01/17.
 */

public class FactViewModel extends BaseObservable {

    private Fact fact;

    public FactViewModel(Fact fact) {
        this.fact = fact;
    }

    public String getFactTitle() {
        return fact.title();
    }
}
