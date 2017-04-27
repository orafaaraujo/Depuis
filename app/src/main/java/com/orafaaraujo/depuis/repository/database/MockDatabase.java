package com.orafaaraujo.depuis.repository.database;

import com.orafaaraujo.depuis.model.Fact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rafael on 20/01/17.
 */

public class MockDatabase {

    private static final int N = 10;

    private static final String LOREM_TITLE = "Lorem ipsum dolor sit amet";
    private static final String LOREM_COMMENT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla vehicula egestas libero, sit amet elementum turpis. Pellentesque mattis nibh at urna euismod malesuada. Integer feugiat blandit ligula, vel finibus diam pellentesque vitae. Suspendisse bibendum eget justo in imperdiet. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nullam sodales risus eros, at volutpat orci tincidunt scelerisque. Aliquam dapibus commodo augue eget fringilla. Cras eget sodales libero. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. In vel sodales lacus";

    public static List<Fact> fetchFacts() {
        List<Fact> facts = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            facts.add(makeFact());
        }
        return facts;
    }

    private static Fact makeFact() {
        return Fact.builder()
                .setStartTime(new Date().getTime())
                .setTitle(LOREM_TITLE)
                .setComment(LOREM_COMMENT)
                .setCount(true)
                .build();
    }

}
