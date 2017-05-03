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
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;

/**
 * Tests to calculate dates.
 *
 * Created by rafael on 03/05/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TimeElapsedTest.class)
public class TimeElapsedTest extends Application {

    private LocalDateTime mEndDateBind;

    private LocalTime mTimeBegin;

    private LocalDate mDateBegin;

    @Before
    public void setup() {
        LocalTime localTime = LocalTime.of(14, 30, 30);
        LocalDate localDate = LocalDate.of(2017, 3, 20);
        mEndDateBind = LocalDateTime.of(localDate, localTime);
    }

    @Test
    public void maxIsSeconds() {
        mTimeBegin = LocalTime.of(14, 30, 15);
        long time = calculeTime(mTimeBegin, mEndDateBind.toLocalTime());
        assertEquals(15, time);
    }

    @Test
    public void maxIsMinutes() {
        mTimeBegin = LocalTime.of(14, 0, 30);
        long time = calculeTime(mTimeBegin, mEndDateBind.toLocalTime());
        assertEquals(30, time);
    }

    @Test
    public void maxIsHours() {
        mTimeBegin = LocalTime.of(12, 30, 30);
        long time = calculeTime(mTimeBegin, mEndDateBind.toLocalTime());
        assertEquals(2, time);
    }

    @Test
    public void maxIsDay() {
        mDateBegin = LocalDate.of(2017, 3, 11);
        long date = calculeDate(mDateBegin, mEndDateBind.toLocalDate());
        assertEquals(9, date);
    }

    @Test
    public void maxIsMonth() {
        mDateBegin = LocalDate.of(2017, 2, 20);
        long date = calculeDate(mDateBegin, mEndDateBind.toLocalDate());
        assertEquals(1, date);
    }

    @Test
    public void maxIsYear() {
        mDateBegin = LocalDate.of(1987, 3, 20);
        long date = calculeDate(mDateBegin, mEndDateBind.toLocalDate());
        assertEquals(30, date);
    }

    @Test
    public void maxIsTime() {
        mDateBegin = LocalDate.of(2017, 3, 20);
        mTimeBegin = LocalTime.of(14, 20, 30);
        LocalDateTime beginDateTime = LocalDateTime.of(mDateBegin, mTimeBegin);
        final long elapsed = calculate(beginDateTime, mEndDateBind);
        assertEquals(10, elapsed);
    }

    @Test
    public void maxIsDate() {
        mDateBegin = LocalDate.of(2017, 3, 10);
        mTimeBegin = LocalTime.of(14, 30, 30);
        LocalDateTime beginDateTime = LocalDateTime.of(mDateBegin, mTimeBegin);
        final long elapsed = calculate(beginDateTime, mEndDateBind);
        assertEquals(10, elapsed);
    }

    private long calculate(LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        long elapsed = calculeDate(beginDateTime.toLocalDate(), endDateTime.toLocalDate());
        if (elapsed == 0) {
            elapsed = calculeTime(beginDateTime.toLocalTime(), endDateTime.toLocalTime());
        }
        return elapsed;
    }

    private long calculeTime(LocalTime timeBegin, LocalTime timeEnd) {
        Duration duration = Duration.between(timeBegin, timeEnd);
        if (duration.toHours() > 0) {
            return duration.toHours();
        } else if (duration.toMinutes() > 0) {
            return duration.toMinutes();
        } else {
            return duration.getSeconds();
        }
    }

    private long calculeDate(LocalDate dateBegin, LocalDate dateEnd) {
        Period between = Period.between(dateBegin, dateEnd);
        if (between.getYears() > 0) {
            return between.getYears();
        } else if (between.getMonths() > 0) {
            return between.getMonths();
        } else {
            return between.getDays();
        }
    }

}
