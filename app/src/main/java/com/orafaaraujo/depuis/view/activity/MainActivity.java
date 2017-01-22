package com.orafaaraujo.depuis.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.repository.FactManager;
import com.orafaaraujo.depuis.view.adapter.FactAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.main_fab_new_fact)
    FloatingActionButton mFab;

    @BindView(R.id.main_recycler_fact)
    RecyclerView mRecyclerView;

    private FactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();
        setupRecyclerView();
        populateRecyclerView();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void setupRecyclerView() {

        mAdapter = new FactAdapter();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    mFab.hide();
                } else if (dy < 0) {
                    mFab.show();
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void populateRecyclerView() {
        mAdapter.setFacts(FactManager.fetchFacts());
    }

    public void onNewItem(View view) {
        startActivity(new Intent(this, NewFactActivity.class));
    }
}
