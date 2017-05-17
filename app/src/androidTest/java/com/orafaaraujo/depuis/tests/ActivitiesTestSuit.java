package com.orafaaraujo.depuis.tests;

import com.orafaaraujo.depuis.tests.activities.MainActivityTest;
import com.orafaaraujo.depuis.tests.activities.NewFactActivityTest;
import com.orafaaraujo.depuis.tests.flows.FactFlowTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FactFlowTest.class,
        MainActivityTest.class,
        NewFactActivityTest.class
})
public class ActivitiesTestSuit {
}
