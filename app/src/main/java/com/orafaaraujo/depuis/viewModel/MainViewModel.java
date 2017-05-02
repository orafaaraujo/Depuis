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

import java.util.LinkedList;
import java.util.List;

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
    FactDatabase mDatabase;

    public ObservableField<Boolean> mShow = new ObservableField<>(true);

    private LinkedList<FactTO> mDeleteFacts = new LinkedList<>();

    private LinkedList<FactTO> mUndoDelete = new LinkedList<>();

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
        mDeleteFacts.add(factTO);
    }

    public void deleteFact() {
        final FactTO factTO = mDeleteFacts.poll();
        if (mUndoDelete.contains(factTO)) {
            mUndoDelete.remove();
        } else {
            mDatabase.deleteFact(factTO.fact());
        }
    }

    public void undoDeleteFact() {
        final FactTO factTO = mDeleteFacts.peek();
        mUndoDelete.add(factTO);
        mAdapter.insertFact(factTO);
        mLayoutManager.scrollToPosition(factTO.position());
    }
}
