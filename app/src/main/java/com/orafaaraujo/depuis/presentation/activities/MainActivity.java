package com.orafaaraujo.depuis.presentation.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.model.Fact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.main_fab_new_fact)
    FloatingActionButton mFab;

    @BindView(R.id.main_recycler_fact)
    RecyclerView mRecyclerView;

    private List<Fact> mFacts;

    @Override
    protected void onResume() {
        super.onResume();
        mFacts = new ArrayList<>();
        Fact fact = Fact.builder()
                .setTimestamp(new Date().getTime())
                .setTitle("This day")
                .setComment("The day i start the app!")
                .setCount(true)
                .build();
        mFacts.add(fact);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
    }

}
