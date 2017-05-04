package com.orafaaraujo.depuis.activities;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import android.support.design.widget.FloatingActionButton;
import android.support.test.filters.MediumTest;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.interfaces.ViewsInterfaceTests;
import com.orafaaraujo.depuis.view.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests of @{@link MainActivity}
 *
 * Created by Rafael Araujo on 04/05/17.
 */
@MediumTest
@SdkSuppress(minSdkVersion = 16)
@RunWith(AndroidJUnit4.class)
public class MainActivityTest implements ViewsInterfaceTests {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureViewsArePresent() throws Exception {

        MainActivity activity = rule.getActivity();

        View newFactView = activity.findViewById(R.id.main_fab_new_fact);
        assertThat(newFactView, notNullValue());
        assertThat(newFactView, instanceOf(FloatingActionButton.class));

        View recyclerView = activity.findViewById(R.id.main_recycler_fact);
        assertThat(recyclerView, notNullValue());
        assertThat(recyclerView, instanceOf(RecyclerView.class));
    }

    @Test
    public void ensureNewFactButtonIsOk() {
        onView(withId(R.id.main_fab_new_fact))
                .check(matches(isClickable()))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureRecyclerViewIsOk() {
        onView(withId(R.id.main_recycler_fact))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }
}
