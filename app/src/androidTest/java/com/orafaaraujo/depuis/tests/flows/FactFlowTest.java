package com.orafaaraujo.depuis.tests.flows;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static com.orafaaraujo.depuis.helpers.matchers.ViewMatchers.withBackgroundColor;

import static org.hamcrest.Matchers.is;

import android.content.Context;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.helpers.matchers.RecyclerViewMatcher;
import com.orafaaraujo.depuis.repository.database.FactDatabase;
import com.orafaaraujo.depuis.repository.database.SQLiteDatabase;
import com.orafaaraujo.depuis.view.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Test of a flow of Fact (create, list, delete, etc).
 *
 * Created by Rafael Araujo on 05/05/17.
 */
@LargeTest
@SdkSuppress(minSdkVersion = 16)
@RunWith(AndroidJUnit4.class)
public class FactFlowTest {

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
    public void createSuccess() {

        String title = "createSuccess";

        onView(ViewMatchers.withId(R.id.activity_main_empty_list_button))
                .perform(click());

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_start_button))
                .perform(click());

        onView(withId(R.id.main_fab_new_fact))
                .check(matches(isClickable()))
                .check(matches(isDisplayed()))
                .check(matches(is(isEnabled())));

        onView(withId(R.id.main_recycler_fact))
                .check(matches(hasDescendant(withText(title))));
    }

    @Test
    public void createButNotSaving() {

        String title = "createButNotSaving";

        onView(withId(R.id.activity_main_empty_list_button))
                .perform(click());

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_button_back))
                .perform(click());

        onView(withId(R.id.activity_main_empty_list_title))
                .check(matches(isDisplayed()))
                .check(matches(is(isEnabled())));

        onView(withId(R.id.activity_main_empty_list_subtitle))
                .check(matches(isDisplayed()))
                .check(matches(is(isEnabled())));

        onView(withId(R.id.activity_main_empty_list_button))
                .check(matches(isClickable()))
                .check(matches(isDisplayed()))
                .check(matches(is(isEnabled())));

    }

    @Test
    public void createAndDeleting() {

        Context context = InstrumentationRegistry.getTargetContext();
        String title = "createAndDeleting";

        onView(ViewMatchers.withId(R.id.activity_main_empty_list_button))
                .perform(click());

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_start_button))
                .perform(click());

        onView(withId(R.id.main_fab_new_fact))
                .check(matches(isClickable()))
                .check(matches(isDisplayed()))
                .check(matches(is(isEnabled())));

        onView(withId(R.id.main_recycler_fact))
                .check(matches(hasDescendant(withText(title))));

        onView(withId(R.id.main_recycler_fact))
                .check(matches(hasDescendant(withText(title))));

        onView(withId(R.id.main_recycler_fact))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, swipeLeft()));

        onView(withText(context.getString(R.string.main_deleted, title)))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void createAndDeletingAndUndo() {

        Context context = InstrumentationRegistry.getTargetContext();
        String title = "createAndDeletingAndUndo";

        onView(ViewMatchers.withId(R.id.activity_main_empty_list_button))
                .perform(click());

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_start_button))
                .perform(click());

        onView(withId(R.id.main_fab_new_fact))
                .check(matches(isClickable()))
                .check(matches(isDisplayed()))
                .check(matches(is(isEnabled())));

        onView(withId(R.id.main_recycler_fact))
                .check(matches(hasDescendant(withText(title))));

        onView(withId(R.id.main_recycler_fact))
                .check(matches(hasDescendant(withText(title))));

        onView(withId(R.id.main_recycler_fact))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, swipeLeft()));

        onView(withText(context.getString(R.string.main_deleted, title)))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withText(R.string.main_deleted_undo))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                .perform(click());

        onView(withId(R.id.main_recycler_fact))
                .check(matches(hasDescendant(withText(title))));
    }

    @Test
    public void createAndCloseFactSuccess() {

        String title = "createAndCloseFactSuccess";

        onView(ViewMatchers.withId(R.id.activity_main_empty_list_button))
                .check(matches(isClickable()))
                .check(matches(isDisplayed()))
                .check(matches(is(isEnabled())))
                .perform(click());

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_start_button))
                .perform(click());

        onView(withId(R.id.main_fab_new_fact))
                .check(matches(isClickable()))
                .check(matches(isDisplayed()))
                .check(matches(is(isEnabled())));

        onView(withId(R.id.main_recycler_fact))
                .check(matches(hasDescendant(withText(title))));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            onView(RecyclerViewMatcher.withRecyclerView(R.id.main_recycler_fact)
                    .atPosition(0))
                    .check(matches(withBackgroundColor(android.R.color.white)));
        }

        onView(withId(R.id.main_recycler_fact))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(0, com.orafaaraujo.depuis.helpers.matchers
                                .ViewMatchers.clickChildViewWithId(R.id.item_fact_close)));

        onView(withText(R.string.fact_close_title))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));

        onView(withText(R.string.fact_close_message))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));

        onView(withText(android.R.string.no))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));

        onView(withText(android.R.string.yes))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            onView(RecyclerViewMatcher.withRecyclerView(R.id.main_recycler_fact)
                    .atPosition(0))
                    .check(matches(withBackgroundColor(R.color.main_close_fact_background)));
        }
    }

    @Test
    public void createAndCloseFactFailure() {

        String title = "createAndCloseFactFailure";

        onView(ViewMatchers.withId(R.id.activity_main_empty_list_button))
                .perform(click());

        onView(withId(R.id.new_fact_text_edittext_title))
                .perform(typeText(title), closeSoftKeyboard())
                .check(matches(withText(title)));

        onView(withId(R.id.new_fact_start_button))
                .perform(click());

        onView(withId(R.id.main_fab_new_fact))
                .check(matches(isClickable()))
                .check(matches(isDisplayed()))
                .check(matches(is(isEnabled())));

        onView(withId(R.id.main_recycler_fact))
                .check(matches(hasDescendant(withText(title))));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            onView(RecyclerViewMatcher.withRecyclerView(R.id.main_recycler_fact)
                    .atPosition(0))
                    .check(matches(withBackgroundColor(android.R.color.white)));
        }

        onView(withId(R.id.main_recycler_fact)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, com.orafaaraujo.depuis.helpers
                        .matchers
                        .ViewMatchers.clickChildViewWithId(R.id.item_fact_close)));

        onView(withText(R.string.fact_close_title))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));

        onView(withText(R.string.fact_close_message))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));

        onView(withText(android.R.string.yes))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));

        onView(withText(android.R.string.no))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            onView(RecyclerViewMatcher.withRecyclerView(R.id.main_recycler_fact)
                    .atPosition(0))
                    .check(matches(withBackgroundColor(android.R.color.white)));
        }
    }

    @Test
    public void createAndTryToCloseAClosed() {

        createAndCloseFactSuccess();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            onView(RecyclerViewMatcher.withRecyclerView(R.id.main_recycler_fact)
                    .atPosition(0))
                    .check(matches(withBackgroundColor(R.color.main_close_fact_background)));
        }

        onView(withId(R.id.main_recycler_fact))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(0, com.orafaaraujo.depuis.helpers.matchers
                                .ViewMatchers.clickChildViewWithId(R.id.item_fact_close)));

        onView(withText(R.string.fact_close_snack_bar_message))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }


}
