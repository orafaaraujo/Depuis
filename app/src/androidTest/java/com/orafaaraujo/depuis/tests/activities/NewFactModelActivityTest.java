package com.orafaaraujo.depuis.tests.activities;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;

import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.repository.database.FactDatabase;
import com.orafaaraujo.depuis.repository.database.SQLiteDatabase;
import com.orafaaraujo.depuis.view.activity.NewFactActivity;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests of @{@link NewFactActivity}
 *
 * Created by Rafael Araujo on 04/05/17.
 */
@LargeTest
@SdkSuppress(minSdkVersion = 16)
@RunWith(AndroidJUnit4.class)
public class NewFactModelActivityTest {

    @Rule
    public ActivityTestRule<NewFactActivity> rule = new ActivityTestRule<>(NewFactActivity.class);

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
    public void ensureBackButtonIsOk() {
        onView(withId(R.id.new_fact_button_back))
                .check(matches(isClickable()))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureFactTitleIsOk() {
        onView(withId(R.id.new_fact_text_title))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureSubtitleIsOk() {
        onView(withId(R.id.new_fact_text_subtitle))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureInputTitleIsOk() {
        onView(withId(R.id.new_fact_text_input_title))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureEditTitleIsOk() {
        onView(withId(R.id.new_fact_text_edittext_title))
                .check(matches(isClickable()))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureInputCommentIsOk() {
        onView(withId(R.id.new_fact_text_input_comment))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureEditCommentIsOk() {
        onView(withId(R.id.new_fact_text_edittext_comment))
                .check(matches(isClickable()))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureDateButtonIsOk() {
        onView(withId(R.id.new_fact_text_date))
                .check(matches(isClickable()))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureTimeButtonIsOk() {
        onView(withId(R.id.new_fact_text_time))
                .check(matches(isClickable()))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void ensureStartButtonIsOk() {
        onView(withId(R.id.new_fact_start_button))
                .check(matches(isClickable()))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(is(isEnabled())));
    }

    @Test
    public void createFact() {

        String title = "createFact";

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_text_edittext_comment))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_start_button))
                .perform(click());
    }

    @Test
    public void createFactWithDate() {

        String title = "createFactWithDate";

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_text_edittext_comment))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        int year = 1987;
        int month = 2;
        int day = 29;

        onView(withId(R.id.new_fact_text_date)).perform(click());
        onView(withClassName(Matchers
                .equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(year, month, day));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.new_fact_start_button)).perform(click());
    }

    @Test
    public void createFactWithTime() {

        String title = "createFactWithTime";

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_text_edittext_comment))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        int hour = 9;
        int minutes = 15;

        onView(withId(R.id.new_fact_text_time)).perform(click());
        onView(withClassName(Matchers
                .equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(hour, minutes));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.new_fact_start_button)).perform(click());
    }

    @Test
    public void createFactWithDateAndTime() {

        String title = "createFactWithDateAndTime";

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_text_edittext_comment))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        int year = 1987;
        int month = 2;
        int day = 29;

        onView(withId(R.id.new_fact_text_date)).perform(click());
        onView(withClassName(Matchers
                .equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(year, month, day));
        onView(withId(android.R.id.button1)).perform(click());

        int hour = 9;
        int minutes = 15;

        onView(withId(R.id.new_fact_text_time)).perform(click());
        onView(withClassName(Matchers
                .equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(hour, minutes));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.new_fact_start_button)).perform(click());
    }

    @Test
    public void createFactMissingTitle() {

        onView(withId(R.id.new_fact_text_edittext_comment))
                .perform(typeText("createFactMissingTitle"), closeSoftKeyboard())
                .check(matches(withText("createFactMissingTitle")));

        onView(withId(R.id.new_fact_start_button))
                .perform(click());

        onView(withText(R.string.new_fact_failure_title))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}
