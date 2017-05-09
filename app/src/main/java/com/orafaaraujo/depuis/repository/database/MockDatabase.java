package com.orafaaraujo.depuis.repository.database;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orafaaraujo.depuis.model.Fact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.Single;

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

    public MockDatabase(boolean dbStarted) {
        if (dbStarted) {
            fetchFacts();
        }
    }

    private void fetchFacts() {
        Observable
                .just(3)
                .forEach(i -> mFacts.add(makeFact(i)));
    }

    private static Fact makeFact(int i) {
        return Fact.builder()
                .setId((int) new Date().getTime())
                .setStartTime(new Date().getTime())
                .setTitle(String.format(Locale.getDefault(), "%d %s", i, LOREM_TITLE))
                .setComment(LOREM_COMMENT)
                .setEndTime(-1)
                .build();
    }

    @Override
    public void saveFact(Fact fact) {
        mFacts.add(fact);
    }

    @Override
    public void updateFact(Fact fact) {

        Single<Integer> integerSingle = Observable
                .range(0, mFacts.size())
                .filter(i -> mFacts.get(i).id() == fact.id())
                .firstOrError();

        integerSingle.blockingGet();

        int index = 0;
        for (int i = 0; i < mFacts.size(); i++) {
            if (mFacts.get(i).id() == fact.id()) {
                index = i;
                break;
            }
        }

        mFacts.remove(index);
        mFacts.add(index, fact);
    }

    @Nullable
    @Override
    public Fact findFact(Fact fact) {

        Optional<Fact> first = mFacts
                .stream()
                .filter(fact1 -> fact1.id() == fact.id())
                .findFirst();

        if (first.isPresent()) {
            return first.get();
        } else {
            return null;
        }
    }

    @NonNull
    @Override
    public List<Fact> fetchAll() {
        return mFacts;
    }

    @Override
    public void deleteFact(Fact fact) {
        mFacts.remove(fact);
    }

    @Override
    public void deleteTable() {
        mFacts.clear();
        mFacts = new ArrayList<>();
    }
}
