package com.orafaaraujo.depuis.viewModel;

import android.databinding.BaseObservable;

import com.orafaaraujo.depuis.model.Fact;

import java.util.Date;

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

    public String getFactComment() {
        return fact.comment();
    }

    public String getFactBegin() {
        return new Date(fact.timestamp()).toString();
    }

    public String getFactCurrentTime() {
        return new Date(fact.timestamp()).toString();
    }


    public boolean getFactCount() {
        return fact.count();
    }


}
