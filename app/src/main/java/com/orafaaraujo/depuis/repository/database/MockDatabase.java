package com.orafaaraujo.depuis.repository.database;

import com.orafaaraujo.depuis.model.Fact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

/**
 * Created by rafael on 20/01/17.
 */
public class MockDatabase implements FactDatabase {

    List<Fact> mFacts = new ArrayList<>();

    private static final String LOREM_TITLE = "Lorem ipsum dolor sit amet";

    private static final String LOREM_COMMENT =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla vehicula egestas "
                    + "libero, sit amet elementum turpis. Pellentesque mattis nibh at urna "
                    + "euismod malesuada. Integer feugiat blandit ligula, vel finibus diam "
                    + "pellentesque vitae. Suspendisse bibendum eget justo in imperdiet. Interdum"
                    + " et malesuada fames ac ante ipsum primis in faucibus. Nullam sodales risus"
                    + " eros, at volutpat orci tincidunt scelerisque. Aliquam dapibus commodo "
                    + "augue eget fringilla. Cras eget sodales libero. Class aptent taciti "
                    + "sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. In"
                    + " vel sodales lacus";

    public MockDatabase() {
        fetchFacts();
    }

    private void fetchFacts() {
        for (int i = 0; i < 3; i++) {
            mFacts.add(makeFact(i));
        }
    }

    private static Fact makeFact(int i) {
        return Fact.builder()
                .setId((int) new Date().getTime())
                .setStartTime(new Date().getTime())
                .setTitle(String.format(Locale.getDefault(), "%d %s", i, LOREM_TITLE))
                .setComment(LOREM_COMMENT)
                .setCount(true)
                .build();
    }

    @Override
    public void saveFact(Fact fact) {
        mFacts.add(fact);
    }

    @Override
    public List<Fact> fetchAll() {
        return mFacts;
    }

    @Override
    public Observable<Fact> getObservable() {
        return Observable.just((Fact) mFacts);
    }

    @Override
    public Fact fetchFact(int id) {
        return mFacts.get(id);
    }

    @Override
    public void deleteFact(Fact fact) {
        mFacts.remove(fact);
    }
}
