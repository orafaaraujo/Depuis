package com.orafaaraujo.depuis.repository.database;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orafaaraujo.depuis.model.FactModel;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by rafael on 20/01/17.
 */
public class MockDatabase implements FactDatabase {

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
    private List<FactModel> mFactModels = new ArrayList<>();

    public MockDatabase(boolean dbStarted) {
        if (dbStarted) {
            fetchFacts();
        }
    }

    private static FactModel makeFact(int i, long time) {

        return FactModel.builder()
                .setId((int) new Date().getTime())
                .setStartTime(time)
                .setTitle(String.format(Locale.getDefault(), "%d %s", i, LOREM_TITLE))
                .setComment(LOREM_COMMENT)
                .setEndTime(-1)
                .build();
    }

    private void fetchFacts() {

        LocalDate localDate = LocalDate.of(1987, 3, 20);
        LocalTime localTime = LocalTime.of(15, 5, 5);
        LocalDateTime of = LocalDateTime.of(localDate, localTime);
        long time = of.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        Observable
                .range(0, 3)
                .forEach(i -> mFactModels.add(makeFact(i, time)));
    }

    @Override
    public long saveFact(FactModel factModel) {
        mFactModels.add(factModel);
        return factModel.id();
    }

    @Override
    public void updateFact(FactModel factModel) {

        Single<Integer> integerSingle = Observable
                .range(0, mFactModels.size())
                .filter(i -> mFactModels.get(i).id() == factModel.id())
                .firstOrError();

        integerSingle.blockingGet();

        int index = 0;
        for (int i = 0; i < mFactModels.size(); i++) {
            if (mFactModels.get(i).id() == factModel.id()) {
                index = i;
                break;
            }
        }

        mFactModels.remove(index);
        mFactModels.add(index, factModel);
    }

    @Nullable
    @Override
    public FactModel findFact(long factId) {

        Observable<FactModel> map = Observable
                .fromIterable(mFactModels)
                .filter(factModel -> factModel.id() == factId)
                .map(factModel -> factModel);

        return map.firstElement().blockingGet();
    }

    @NonNull
    @Override
    public List<FactModel> fetchAll() {
        return mFactModels;
    }

    @Override
    public void deleteFact(FactModel factModel) {
        mFactModels.remove(factModel);
    }

    @Override
    public void deleteTable() {
        mFactModels.clear();
        mFactModels = new ArrayList<>();
    }
}
