package com.orafaaraujo.depuis;

import com.orafaaraujo.depuis.activities.MainActivityTest;
import com.orafaaraujo.depuis.activities.NewFactActivityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainActivityTest.class,
        NewFactActivityTest.class,
})
public class ActivitiesTestSuit {
}
