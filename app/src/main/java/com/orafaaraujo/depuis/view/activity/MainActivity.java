package com.orafaaraujo.depuis.view.activity;

import android.app.AlertDialog;
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

        mRxBus.getNewFactEvents()
                .subscribe(this::handleNewFactAdded, Timber::e);

        mRxBus.getDeletedFactEvents()
                .subscribe(this::handlerDeleteFact, Timber::e);

        mRxBus.getClosedFactEvents()
                .subscribe(this::handlerCloseFact, Timber::e);

        mMainViewModel.fetchFacts();
    }

    private void handleNewFactAdded(FactTO factTO) {
        mMainViewModel.newFactAdded(factTO);
    }

    private void handlerDeleteFact(FactTO factTO) {
        mMainViewModel.setFactToDelete(factTO);
        showSnackBar(factTO.fact());
    }

    private void handlerCloseFact(FactTO factTO) {
        if (factTO.fact().endTime() == -1) {
            showDialog(factTO);
        } else {
            Snackbar.make(findViewById(R.id.main_recycler_fact),
                    R.string.fact_close_snack_bar_message, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void showDialog(FactTO factTO) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle(R.string.fact_close_title);
        builder.setMessage(R.string.fact_close_message);
        builder.setPositiveButton(android.R.string.yes,
                (dialog, which) -> mMainViewModel.closeFact(factTO));
        builder.setNegativeButton(android.R.string.no, (dialog, which) -> dialog.dismiss());
        builder.show();
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
        mMainViewModel.updateFields();
    }
}