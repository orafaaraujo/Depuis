package com.orafaaraujo.depuis.elapsedTime;

import static org.junit.Assert.assertEquals;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.orafaaraujo.depuis.BuildConfig;
import com.orafaaraujo.depuis.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;

/**
 * Created by venturus on 03/05/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "src/main/AndroidManifest.xml", sdk = 25,
        application = FormattedElapsedTime.class)
public class FormattedElapsedTime extends Application {

    private Context mContext;

    private LocalDateTime mBeginDateTime;
    private LocalDateTime mEndDateTime;

    private final String MAX_SECONDS = "5s";
    private final String MAX_MINUTES = "5m";
    private final String MAX_HOURS = "5h";
    private final String MAX_DAYS = "5 days";
    private final String MAX_MONTHS = "5 months and 5 days";
    private final String MAX_YEARS = "5 years and 5 months and 5 days";

    @Before
    public void setup() {

        mContext = RuntimeEnvironment.application;

        LocalTime localTime = LocalTime.of(15, 5, 5);
        LocalDate localDate = LocalDate.of(2017, 8, 8);
        mEndDateTime = LocalDateTime.of(localDate, localTime);
        mBeginDateTime = LocalDateTime.of(localDate, localTime);
    }

    @Test
    public void maxIsSeconds() {

        mBeginDateTime = LocalDateTime.of(
                mEndDateTime.toLocalDate(),
                mEndDateTime.toLocalTime().minusSeconds(5));

        String time = calculate(mBeginDateTime, mEndDateTime);
        assertEquals(MAX_SECONDS, time);
    }

    @Test
    public void maxIsMinutes() {

        mBeginDateTime = LocalDateTime.of(
                mEndDateTime.toLocalDate(),
                mEndDateTime.toLocalTime().minusMinutes(5));

        String time = calculate(mBeginDateTime, mEndDateTime);
        assertEquals(MAX_MINUTES, time);
    }

    @Test
    public void maxIsHour() {

        mBeginDateTime = LocalDateTime.of(
                mEndDateTime.toLocalDate(),
                mEndDateTime.toLocalTime().minusHours(5));

        String time = calculate(mBeginDateTime, mEndDateTime);
        assertEquals(MAX_HOURS, time);
    }

    @Test
    public void maxIsDays() {

        mBeginDateTime = LocalDateTime.of(
                mEndDateTime.toLocalDate().minusDays(5),
                mEndDateTime.toLocalTime());

        String time = calculate(mBeginDateTime, mEndDateTime);
        assertEquals(MAX_DAYS, time);
    }

    @Test
    public void maxIsMonths() {

        mBeginDateTime = LocalDateTime.of(
                mEndDateTime.toLocalDate().minusDays(5).minusMonths(5),
                mEndDateTime.toLocalTime());

        String time = calculate(mBeginDateTime, mEndDateTime);
        assertEquals(MAX_MONTHS, time);
    }

    @Test
    public void maxIsYears() {

        mBeginDateTime = LocalDateTime.of(
                mEndDateTime.toLocalDate().minusDays(5).minusMonths(5).minusYears(5),
                mEndDateTime.toLocalTime());

        String time = calculate(mBeginDateTime, mEndDateTime);
        assertEquals(MAX_YEARS, time);
    }

    private String calculate(LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        String elapsed = calculeDate(beginDateTime.toLocalDate(), endDateTime.toLocalDate());
        if (TextUtils.isEmpty(elapsed)) {
            elapsed = calculeTime(beginDateTime.toLocalTime(), endDateTime.toLocalTime());
        }
        return elapsed;
    }

    private String calculeTime(LocalTime timeBegin, LocalTime timeEnd) {
        Duration d = Duration.between(timeBegin, timeEnd);
        if (d.toHours() > 0) {
            return mContext.getString(R.string.elapsed_hours, d.toHours());
        } else if (d.toMinutes() > 0) {
            return mContext.getString(R.string.elapsed_minutes, d.toMinutes());
        } else {
            return mContext.getString(R.string.elapsed_seconds, d.getSeconds());
        }
    }

    private String calculeDate(LocalDate dateBegin, LocalDate dateEnd) {
        Period p = Period.between(dateBegin, dateEnd);
        if (p.getYears() > 0) {
            return mContext.getString(R.string.elapsed_years, p.getDays(), p.getMonths(),
                    p.getDays());
        } else if (p.getMonths() > 0) {
            return mContext.getString(R.string.elapsed_months, p.getDays(), p.getMonths());
        } else if (p.getDays() > 0) {
            return mContext.getString(R.string.elapsed_days, p.getDays());
        } else {
            return null;
        }
    }

}
