package com.orafaaraujo.depuis;

import static org.junit.Assert.assertEquals;

import android.app.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;

/**
 * Created by venturus on 03/05/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TimeElapsedTest.class)
public class TimeElapsedTest extends Application {

    private LocalDate mDateBegin;

    private LocalDate mDateEnd;

    private LocalTime mTimeBegin;

    private LocalTime mTimeEnd;

    @Before
    public void setup() {
        mDateEnd = LocalDate.of(2017, 3, 29);
        mTimeEnd = LocalTime.of(14, 30, 30);
    }

    @Test
    public void seconds() throws Exception {
        mTimeBegin = LocalTime.of(14, 30, 0);
        Duration duration = Duration.between(mTimeBegin, mTimeEnd);
        assertEquals(30, duration.getSeconds());
    }

    @Test
    public void minutes() throws Exception {
        mTimeBegin = LocalTime.of(14, 0, 30);
        Duration duration = Duration.between(mTimeBegin, mTimeEnd);
        assertEquals(30, duration.toMinutes());
    }
    @Test
    public void hours() throws Exception {
        mTimeBegin = LocalTime.of(12, 30, 30);
        Duration duration = Duration.between(mTimeBegin, mTimeEnd);
        assertEquals(2, duration.toHours());
    }

    @Test
    public void days() throws Exception {
        mDateBegin = LocalDate.of(2017, 3, 20);
        Period between = Period.between(mDateBegin, mDateEnd);
        assertEquals(9, between.getDays());
    }

    @Test
    public void month() throws Exception {
        mDateBegin = LocalDate.of(2016, 2, 29);
        Period between = Period.between(mDateBegin, mDateEnd);
        assertEquals(1, between.getMonths());
    }

    @Test
    public void year() throws Exception {
        mDateBegin = LocalDate.of(1987, 3, 29);
        Period between = Period.between(mDateBegin, mDateEnd);
        assertEquals(30, between.getYears());
    }

}
