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
import com.orafaaraujo.depuis.repository.entity.FactEntity;
import com.orafaaraujo.depuis.view.activity.NewFactActivity;
import com.orafaaraujo.depuis.view.adapter.FactAdapter;
import com.orafaaraujo.depuis.view.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
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

    private FactTO mFactTO;

    private boolean mFactIsOverwrite;

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
        List<FactEntity> factEntities = mDatabase.fetchAll();
        List<Fact> facts = new ArrayList<>(factEntities.size());

        factEntities
                .stream()
                .map(this::entityToModel)
                .forEach(facts::add);

        mAdapter.updateFacts(facts);
    }

    private Fact entityToModel(FactEntity fe) {
        return Fact.builder()
                .setTitle(fe.title())
                .setComment(fe.comment())
                .setCount(fe.count())
                .setStartTime(fe.startTime())
                .build();
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
        mFactIsOverwrite = mFactTO != null;
        mFactTO = factTO;
    }

    public void undoDeleteFact() {
        mAdapter.insertFact(mFactTO);
        mLayoutManager.scrollToPosition(mFactTO.position());
    }

    public void deleteFact() {
        //TODO delete from Database
        if (!mFactIsOverwrite) {
            mFactTO = null;
        }
    }

}
