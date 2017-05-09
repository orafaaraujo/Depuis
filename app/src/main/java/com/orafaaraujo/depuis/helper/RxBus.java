package com.orafaaraujo.depuis.helper;

import com.orafaaraujo.depuis.helper.buses.DatetimeTO;
import com.orafaaraujo.depuis.helper.buses.FactTO;
import com.orafaaraujo.depuis.helper.buses.NewFactFeedbackTO;

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
     * to receive the {@link DatetimeTO} events.
     */
    public Observable<DatetimeTO> getDatetimeEvents() {
        return subject
                .filter(o -> o instanceof DatetimeTO)
                .map(o -> (DatetimeTO) o);
    }

    /**
     * Method that return a {@link Observable} to implement a {@link io.reactivex.subjects.Subject}
     * to receive the {@link NewFactFeedbackTO} events.
     */
    public Observable<NewFactFeedbackTO> getNewFactFeedbackEvents() {
        return subject
                .filter(o -> o instanceof NewFactFeedbackTO)
                .map(o -> (NewFactFeedbackTO) o);
    }

    /**
     * Method that return a {@link Observable} to implement a {@link io.reactivex.subjects.Subject}
     * to receive the {@link FactTO} delete events.
     */
    public Observable<FactTO> getFactEventsToDelete() {
        return subject
                .filter(o -> o instanceof FactTO)
                .map(o -> (FactTO) o)
                .filter(FactTO::delete);
    }

    /**
     * Method that return a {@link Observable} to implement a {@link io.reactivex.subjects.Subject}
     * to receive the @{@link FactTO} close events.
     */
    public Observable<FactTO> getFactEventsToClose() {
        return subject
                .filter(o -> o instanceof FactTO)
                .map(o -> (FactTO) o)
                .filter(FactTO::close);
    }

}
