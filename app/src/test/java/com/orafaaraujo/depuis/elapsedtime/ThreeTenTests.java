package com.orafaaraujo.depuis.elapsedtime;

import static org.junit.Assert.assertEquals;

import com.orafaaraujo.depuis.BuildConfig;

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
 * Testes to see how ThreeTen works.
 *
 * Created by Rafael on 03/05/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25, application = TimeElapsedTest.class)
public class ThreeTenTests {

    private LocalDateTime mEndDateTime;

    private LocalTime mTimeBegin;

    private LocalDate mDateBegin;

    @Before
    public void setup() {
        LocalTime localTime = LocalTime.of(14, 30, 30);
        LocalDate localDate = LocalDate.of(2017, 3, 29);
        mEndDateTime = LocalDateTime.of(localDate, localTime);
    }

    @Test
    public void seconds() throws Exception {
        mTimeBegin = LocalTime.of(14, 30, 15);
        Duration duration = Duration.between(mTimeBegin, mEndDateTime.toLocalTime());
        assertEquals(15, duration.getSeconds());
    }

    @Test
    public void minutes() throws Exception {
        mTimeBegin = LocalTime.of(14, 0, 30);
        Duration duration = Duration.between(mTimeBegin, mEndDateTime.toLocalTime());
        assertEquals(30, duration.toMinutes());
    }

    @Test
    public void hours() throws Exception {
        mTimeBegin = LocalTime.of(12, 30, 30);
        Duration duration = Duration.between(mTimeBegin, mEndDateTime.toLocalTime());
        assertEquals(2, duration.toHours());
    }

    @Test
    public void days() throws Exception {
        mDateBegin = LocalDate.of(2017, 3, 20);
        Period between = Period.between(mDateBegin, mEndDateTime.toLocalDate());
        assertEquals(9, between.getDays());
    }

    @Test
    public void months() throws Exception {
        mDateBegin = LocalDate.of(2016, 2, 29);
        Period between = Period.between(mDateBegin, mEndDateTime.toLocalDate());
        assertEquals(1, between.getMonths());
    }

    @Test
    public void years() throws Exception {
        mDateBegin = LocalDate.of(1987, 3, 29);
        Period between = Period.between(mDateBegin, mEndDateTime.toLocalDate());
        assertEquals(30, between.getYears());
    }
}

