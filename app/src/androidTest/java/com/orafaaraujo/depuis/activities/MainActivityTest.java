package com.orafaaraujo.depuis.activities;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;

import android.support.test.filters.LargeTest;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.repository.database.FactDatabase;
import com.orafaaraujo.depuis.repository.database.SQLiteDatabase;
import com.orafaaraujo.depuis.view.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests of @{@link MainActivity}
 *
 * Created by Rafael Araujo on 04/05/17.
 */
@LargeTest
@SdkSuppress(minSdkVersion = 16)
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    private FactDatabase mDatabase;

    @Before
    public void setup() {
        if (mDatabase == null) {
            mDatabase = new SQLiteDatabase(rule.getActivity());
        }
        mDatabase.deleteTable();
    }

    @After
    public void after() {
        if (mDatabase == null) {
            mDatabase = new SQLiteDatabase(rule.getActivity());
        }
        mDatabase.deleteTable();
    }


    @Test
    public void ensureEmptyTitleIsOk() {
        onView(withId(R.id.activity_main_empty_list_title))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureEmptySubTitleIsOk() {
        onView(withId(R.id.activity_main_empty_list_subtitle))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureEmptyButtonIsOk() {
        onView(withId(R.id.activity_main_empty_list_button))
                .check(matches(isClickable()))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureListAndFabIsOk() {
        createSuccess();

        onView(withId(R.id.main_recycler_fact))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));

        onView(withId(R.id.main_fab_new_fact))
                .check(matches(isDisplayed()))
                .check(matches(is(isEnabled())))
                .check(matches(isClickable()));
    }

    public void createSuccess() {

        String title = "Lorem";

        onView(withId(R.id.activity_main_empty_list_button))
                .perform(click());

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_start_button))
                .perform(click());

    }
}
