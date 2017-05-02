package com.orafaaraujo.depuis.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
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
        mMainViewModel.setFactTO(factTO);
        showSnackBar(factTO.fact());
    }

    private void showSnackBar(Fact fact) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.main_recycler_fact),
                getString(R.string.main_deleted, fact.title()), Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.main_deleted_undo, v -> mMainViewModel.undoDeleteFact());
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.main_delete_undo));
        snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                mMainViewModel.deleteFact();
            }
        });
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainViewModel.fetchFacts();
    }
}