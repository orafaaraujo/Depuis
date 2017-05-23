package com.orafaaraujo.depuis.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.orafaaraujo.depuis.helper.buses.FactTO;
import com.orafaaraujo.depuis.model.FactModel;
import com.orafaaraujo.depuis.repository.database.FactDatabase;
import com.orafaaraujo.depuis.view.activity.AboutActivity;
import com.orafaaraujo.depuis.view.activity.NewFactActivity;
import com.orafaaraujo.depuis.view.adapter.FactAdapter;
import com.orafaaraujo.depuis.view.helper.SimpleItemTouchHelperCallback;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

public class MainViewModel extends BaseObservable {

    public ObservableField<Boolean> mShow = new ObservableField<>(true);
    public ObservableField<Boolean> mListVisibility = new ObservableField<>(true);
    public ObservableField<Boolean> mEmptyVisibility = new ObservableField<>(true);
    @Inject
    Context mContext;
    @Inject
    FactAdapter mAdapter;
    @Inject
    Lazy<RecyclerView.LayoutManager> mLayoutManagerProvider;
    @Inject
    DividerItemDecoration mDividerItemDecoration;
    @Inject
    SimpleItemTouchHelperCallback mTouchHelperCallback;
    @Inject
    FactDatabase mDatabase;
    private LinkedList<FactTO> mDeleteFacts = new LinkedList<>();

    private LinkedList<FactTO> mUndoDelete = new LinkedList<>();

    @Inject
    MainViewModel() {
    }

    public FactAdapter getAdapter() {
        return mAdapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mLayoutManagerProvider.get();
    }

    public DividerItemDecoration getDividerItemDecoration() {
        return mDividerItemDecoration;
    }

    public void fetchFacts() {
        List<FactModel> factModels = mDatabase.fetchAll();
        mAdapter.updateFacts(factModels);
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
        Intent intent = new Intent(mContext, NewFactActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return v -> mContext.startActivity(intent);
    }

    public void openSettingView() {
        Intent intent = new Intent(mContext, AboutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }


    public void setFactToDelete(FactTO factTO) {
        mDeleteFacts.add(factTO);
    }

    public void deleteFact() {
        final FactTO factTO = mDeleteFacts.poll();
        if (mUndoDelete.contains(factTO)) {
            mUndoDelete.remove();
        } else {
            if (factTO != null) {
                mDatabase.deleteFact(factTO.fact());
                updateFields();
            }
        }
    }

    public void undoDeleteFact() {
        final FactTO factTO = mDeleteFacts.peek();
        mUndoDelete.add(factTO);
        mAdapter.insertFact(factTO.position(), factTO.fact());
        mLayoutManagerProvider.get().scrollToPosition(factTO.position());
    }

    /**
     * Update on database a endtime of a FactModel.
     *
     * @param factTO FactModel to be copied to a new one (because is immutable) and save on
     *               database.
     */
    public void closeFact(FactTO factTO) {

        FactModel closedFactModel = FactModel.builder()
                .setId(factTO.fact().id())
                .setStartTime(factTO.fact().startTime())
                .setTitle(factTO.fact().title())
                .setComment(factTO.fact().comment())
                .setEndTime(new Date().getTime())
                .build();

        mDatabase.updateFact(closedFactModel);
        mAdapter.notifyClosedFact(factTO.position(), closedFactModel);
    }

    public void newFactAdded(FactTO factTO) {
        FactModel foundFactModel = mDatabase.findFact(factTO.fact().id());
        mAdapter.insertFact(0, foundFactModel);
        mLayoutManagerProvider.get().scrollToPosition(0);
    }

    public void updateFields() {
        mShow.set(mAdapter.getItemCount() > 0);
        mListVisibility.set(mAdapter.getItemCount() > 0);
        mEmptyVisibility.set(mAdapter.getItemCount() <= 0);
    }
}