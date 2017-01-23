package com.orafaaraujo.depuis.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orafaaraujo.depuis.BR;
import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.dagger.Injector;
import com.orafaaraujo.depuis.helper.DateTimeHelper;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class NewFactActivity extends AppCompatActivity {

    @Inject
    DateTimeHelper mDateTimeHelper;

    @BindView(R.id.new_fact_text_edittext_title)
    TextInputEditText mTitle;

    @BindView(R.id.new_fact_text_edittext_comment)
    TextInputEditText mComment;

    private ViewDataBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_fact);

        Injector.getApplicationComponent().inject(this);

        ButterKnife.bind(this);
        Timber.tag("NewFact");
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewDataBinding.setVariable(BR.dateTime, mDateTimeHelper.getTime(new Date().getTime()));
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
        onBackButton(view);
    }

    public void onDateTimeClick(View view) {

    }
}
