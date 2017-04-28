package com.orafaaraujo.depuis.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.dagger.Injector;
import com.orafaaraujo.depuis.databinding.ActivityMainBinding;
import com.orafaaraujo.depuis.helper.RxBus;
import com.orafaaraujo.depuis.helper.buses.FactTO;
import com.orafaaraujo.depuis.model.Fact;
import com.orafaaraujo.depuis.viewModel.MainViewModel;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject
    public MainViewModel mMainViewModel;

    @Inject
    RxBus mRxBus;

    private FactTO mFactTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getApplicationComponent().inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mMainViewModel);
        setSupportActionBar(binding.toolbar);

        mRxBus.getEvents()
                .filter(o -> o instanceof FactTO)
                .map(o -> (FactTO) o)
                .subscribe(this::handlerRemoveFact, Timber::e);
    }

    private void handlerRemoveFact(FactTO factTO) {
        mFactTO = factTO;
        showSnackBar(factTO.fact());
    }

    private void showSnackBar(Fact fact) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.main_recycler_fact),
                "Remove " + fact.title() + "?", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", v -> undoAction());
        snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                mFactTO = null;
            }
        });
        snackbar.show();
    }

    private void undoAction() {
        mMainViewModel.insertFact(mFactTO);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainViewModel.fetchFacts();
    }


}