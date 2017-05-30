package com.orafaaraujo.depuis.tests;

import com.orafaaraujo.depuis.tests.activities.MainActivityTest;
import com.orafaaraujo.depuis.tests.activities.NewFactModelActivityTest;
import com.orafaaraujo.depuis.tests.flows.FactModelFlowTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FactModelFlowTest.class,
        MainActivityTest.class,
        NewFactModelActivityTest.class
})
public class ActivitiesTestSuit {
}
