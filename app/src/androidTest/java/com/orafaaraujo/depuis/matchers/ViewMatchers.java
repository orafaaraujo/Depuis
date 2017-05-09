package com.orafaaraujo.depuis.matchers;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.internal.util.Checks;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by Rafael Araujo on 09/05/17.
 */

public class ViewMatchers {

    /**
     * Method to check the background color of a View.
     *
     * @param resource Color that must check.
     */
    public static Matcher<View> withBackgroundColor(final int resource) {

        Checks.checkNotNull(resource);

        return new BoundedMatcher<View, View>(View.class) {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean matchesSafely(View target) {
                int color = ((ColorDrawable) target.getBackground().getCurrent()).getColor();
                return color == target.getResources().getColor(resource, null);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with background color: ");
            }
        };
    }

}
