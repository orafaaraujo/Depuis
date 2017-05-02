package com.orafaaraujo.depuis.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.orafaaraujo.depuis.helper.buses.FactTO;
import com.orafaaraujo.depuis.model.Fact;
import com.orafaaraujo.depuis.repository.database.FactDatabase;
import com.orafaaraujo.depuis.view.activity.NewFactActivity;
import com.orafaaraujo.depuis.view.adapter.FactAdapter;
import com.orafaaraujo.depuis.view.helper.SimpleItemTouchHelperCallback;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class MainViewModel extends BaseObservable {

    @Inject
    Context mContext;

    @Inject
    FactAdapter mAdapter;

    @Inject
    RecyclerView.LayoutManager mLayoutManager;

    @Inject
    DividerItemDecoration mDividerItemDecoration;

    @Inject
    SimpleItemTouchHelperCallback mTouchHelperCallback;

    @Inject
    FactDatabase mDatabase;

    public ObservableField<Boolean> mShow = new ObservableField<>(true);

    private FactTO mFactToDelete;

//    private boolean mIgnoreNext = false;

    @Inject
    MainViewModel() {
        Timber.tag("deleteFact");
    }

    public FactAdapter getAdapter() {
        return mAdapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public DividerItemDecoration getDividerItemDecoration() {
        return mDividerItemDecoration;
    }

    public void fetchFacts() {
        List<Fact> facts = mDatabase.fetchAll();
        mAdapter.updateFacts(facts);
    }

    public RecyclerView.OnScrollListener getScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mShow.set(dy <= 0);
            }
        };
    }

    public ItemTouchHelper getItemTouch() {
        mTouchHelperCallback.setAdapter(mAdapter);
        return new ItemTouchHelper(mTouchHelperCallback);
    }

    public View.OnClickListener getFabClick() {
        return v -> mContext.startActivity(new Intent(mContext, NewFactActivity.class));
    }

    public void setFactTO(FactTO factTO) {
        Timber.i("setFactTO");
        if (mFactToDelete != null) {
            mDatabase.deleteFact(mFactToDelete.fact());
        }
        mFactToDelete = factTO;
    }

    public void deleteFact() {
        Timber.i("deleteFact");
        if (mFactToDelete != null) {
            mDatabase.deleteFact(mFactToDelete.fact());
        }
        mFactToDelete = null;
    }

    public void undoDeleteFact() {
        Timber.i("undoDeleteFact");
        mAdapter.insertFact(mFactToDelete);
        mLayoutManager.scrollToPosition(mFactToDelete.position());
        mFactToDelete = null;
    }
}

