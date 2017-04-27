package com.orafaaraujo.depuis.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.orafaaraujo.depuis.repository.database.MockDatabase;
import com.orafaaraujo.depuis.repository.database.RequeryDatabase;
import com.orafaaraujo.depuis.view.activity.NewFactActivity;
import com.orafaaraujo.depuis.view.adapter.FactAdapter;
import com.orafaaraujo.depuis.view.helper.SimpleItemTouchHelperCallback;

import javax.inject.Inject;

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
    RequeryDatabase mDatabase;

    public ObservableField<Boolean> mShow = new ObservableField<>(true);

    @Inject
    MainViewModel() {
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
        mAdapter.updateFacts(MockDatabase.fetchFacts());
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
}
