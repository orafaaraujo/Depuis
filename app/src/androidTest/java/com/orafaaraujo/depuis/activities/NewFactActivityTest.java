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

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.view.activity.NewFactActivity;

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
public class NewFactActivityTest {

    @Rule
    public ActivityTestRule<NewFactActivity> rule = new ActivityTestRule<>(NewFactActivity.class);

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

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText("Lorem"), closeSoftKeyboard())
                .check(matches(withText("Lorem")));

        onView(withId(R.id.new_fact_text_edittext_comment))
                .perform(typeText("Lorem"), closeSoftKeyboard())
                .check(matches(withText("Lorem")));

        onView(withId(R.id.new_fact_start_button))
                .perform(click());
    }

    @Test
    public void createFactMissingTitle() {

        Context context = InstrumentationRegistry.getTargetContext();

        onView(withId(R.id.new_fact_text_edittext_comment))
                .perform(typeText("A"), closeSoftKeyboard())
                .check(matches(withText("A")));

        onView(withId(R.id.new_fact_start_button))
                .perform(click());

        onView(allOf(
                withId(android.support.design.R.id.snackbar_text),
                withText(context.getString(R.string.new_fact_failure_title))))
                .check(matches(isDisplayed()));
    }
}
