package com.orafaaraujo.depuis.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orafaaraujo.depuis.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class NewFactActivity extends AppCompatActivity {

    @BindView(R.id.new_fact_text_edittext_title)
    TextInputEditText mTitle;

    @BindView(R.id.new_fact_text_edittext_comment)
    TextInputEditText mComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fact);
        ButterKnife.bind(this);
        Timber.tag("NewFact");
    }

    public void onBackButton(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        } else {
            finish();
        }
    }

    public void onStartNewFact(View view) {
        Timber.i("Title: %s - Comment: %s", mTitle.getText(), mComment.getText());
    }
}
