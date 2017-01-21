package com.orafaaraujo.depuis.repository;

import com.orafaaraujo.depuis.model.Fact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rafael on 20/01/17.
 */

public class FactManager {

    private static final int n = 10;

    public static List<Fact> fetchFacts() {
        List<Fact> facts = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            facts.add(makeFact());
        }
        return facts;
    }

    private static Fact makeFact() {
        return Fact.builder()
                .setTimestamp(new Date().getTime())
                .setTitle("This day")
                .setComment("The day i start the app!")
                .setCount(true)
                .build();
    }

}
