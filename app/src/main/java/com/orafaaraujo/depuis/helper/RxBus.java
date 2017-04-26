package com.orafaaraujo.depuis.helper;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Class with {@link PublishSubject} to used like a EventBus.
 *
 * Created by rafael on 25/04/17.
 */
@Reusable
public class RxBus {

    @Inject
    RxBus() {
    }

    private PublishSubject<Object> subject = PublishSubject.create();

    /**
     * Method to send a event via {@link PublishSubject}
     */
    public void sendEvent(Object vo) {
        subject.onNext(vo);
    }

    /**
     * Method that return a {@link Observable} to implement a {@link io.reactivex.subjects.Subject}
     * to receive the events.
     */
    public Observable<Object> getEvents() {
        return subject;
    }

    public void complete() {
        subject.onComplete();
    }
}
