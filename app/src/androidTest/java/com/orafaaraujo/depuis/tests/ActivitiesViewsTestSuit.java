package com.orafaaraujo.depuis.tests;

import com.orafaaraujo.depuis.tests.activities.MainActivityViewTest;
import com.orafaaraujo.depuis.tests.activities.NewFactActivityViewsTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityViewTest.class,
        NewFactActivityViewsTest.class
})
public class ActivitiesViewsTestSuit {
}
