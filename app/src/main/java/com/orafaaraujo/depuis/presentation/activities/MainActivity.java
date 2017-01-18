package com.orafaaraujo.depuis.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.model.Fact;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fact fact = Fact.builder()
                .setTimestamp(new Date().getTime())
                .setTitle("This day")
                .setComment("The day i start the app!")
                .setCount(true)
                .build();

    }

}
