package com.orafaaraujo.depuis.activities;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.test.filters.SdkSuppress;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.interfaces.ViewsInterfaceTests;
import com.orafaaraujo.depuis.view.activity.NewFactActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests of views @{@link com.orafaaraujo.depuis.view.activity.NewFactActivity}
 *
 * Created by Rafael Araujo on 04/05/17.
 */
@SmallTest
@SdkSuppress(minSdkVersion = 16)
@RunWith(AndroidJUnit4.class)
public class NewFactActivityViewsTest implements ViewsInterfaceTests {

    @Rule
    public ActivityTestRule<NewFactActivity> rule = new ActivityTestRule<>(NewFactActivity.class);

    @Test
    public void ensureViewsArePresent() throws Exception {

        NewFactActivity activity = rule.getActivity();

        View backButton = activity.findViewById(R.id.new_fact_button_back);
        assertThat(backButton, notNullValue());
        assertThat(backButton, instanceOf(ImageButton.class));

        View title = activity.findViewById(R.id.new_fact_text_title);
        assertThat(title, notNullValue());
        assertThat(title, instanceOf(TextView.class));

        View subtitle = activity.findViewById(R.id.new_fact_text_subtitle);
        assertThat(subtitle, notNullValue());
        assertThat(subtitle, instanceOf(TextView.class));

        View inputTitle = activity.findViewById(R.id.new_fact_text_input_title);
        assertThat(inputTitle, notNullValue());
        assertThat(inputTitle, instanceOf(TextInputLayout.class));

        View editTitle = activity.findViewById(R.id.new_fact_text_edittext_title);
        assertThat(editTitle, notNullValue());
        assertThat(editTitle, instanceOf(TextInputEditText.class));

        View inputComment = activity.findViewById(R.id.new_fact_text_input_comment);
        assertThat(inputComment, notNullValue());
        assertThat(inputComment, instanceOf(TextInputLayout.class));

        View editComment = activity.findViewById(R.id.new_fact_text_edittext_comment);
        assertThat(editComment, notNullValue());
        assertThat(editComment, instanceOf(TextInputEditText.class));

        View date = activity.findViewById(R.id.new_fact_text_date);
        assertThat(date, notNullValue());
        assertThat(date, instanceOf(TextView.class));

        View time = activity.findViewById(R.id.new_fact_text_time);
        assertThat(time, notNullValue());
        assertThat(time, instanceOf(TextView.class));

        View startFact = activity.findViewById(R.id.new_fact_start_button);
        assertThat(startFact, notNullValue());
        assertThat(startFact, instanceOf(Button.class));
    }

}
