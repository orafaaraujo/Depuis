package com.orafaaraujo.depuis.activities;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import android.support.design.widget.FloatingActionButton;
import android.support.test.filters.SdkSuppress;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.interfaces.ViewsInterfaceTests;
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
@SmallTest
@SdkSuppress(minSdkVersion = 16)
@RunWith(AndroidJUnit4.class)
public class MainActivityViewTest implements ViewsInterfaceTests {

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
    public void ensureViewsArePresent() throws Exception {

        MainActivity activity = rule.getActivity();

        View emptyTitle = activity.findViewById(R.id.activity_main_empty_list_title);
        assertThat(emptyTitle, notNullValue());
        assertThat(emptyTitle, instanceOf(TextView.class));

        View emptySubtitle = activity.findViewById(R.id.activity_main_empty_list_subtitle);
        assertThat(emptySubtitle, notNullValue());
        assertThat(emptySubtitle, instanceOf(TextView.class));

        View emptyButton = activity.findViewById(R.id.activity_main_empty_list_button);
        assertThat(emptyButton, notNullValue());
        assertThat(emptyButton, instanceOf(Button.class));
    }

    @Test
    public void ensureListViewsArePresent() throws Exception {

        MainActivity activity = rule.getActivity();

        createSuccess();

        View newFactView = activity.findViewById(R.id.main_fab_new_fact);
        assertThat(newFactView, notNullValue());
        assertThat(newFactView, instanceOf(FloatingActionButton.class));

        View recyclerView = activity.findViewById(R.id.main_recycler_fact);
        assertThat(recyclerView, notNullValue());
        assertThat(recyclerView, instanceOf(RecyclerView.class));
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
