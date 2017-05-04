package com.orafaaraujo.depuis.elapsedTime;

import static org.junit.Assert.assertEquals;

import android.app.Application;
import android.text.TextUtils;

import com.orafaaraujo.depuis.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;

import java.util.Calendar;
import java.util.Locale;

/**
 * Test with elapsed time formatted to human.
 *
 * Created by rafael on 03/05/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "src/main/AndroidManifest.xml", sdk = 25,
        application = FormattedElapsedTimeTest.class)
public class FormattedElapsedTimeTest extends Application {

    private LocalDateTime mBeginDateTime;

    private LocalDateTime mEndDateTime;

    @SuppressWarnings("FieldCanBeLocal")
    private final String MAX_SECONDS = "5s";

    @SuppressWarnings("FieldCanBeLocal")
    private final String FORMAT_SECONDS = "%ds";

    @SuppressWarnings("FieldCanBeLocal")
    private final String MAX_MINUTES = "5m";

    @SuppressWarnings("FieldCanBeLocal")
    private final String FORMAT_MINUTES = "%dm";

    @SuppressWarnings("FieldCanBeLocal")
    private final String MAX_HOURS = "5h";

    @SuppressWarnings("FieldCanBeLocal")
    private final String FORMAT_HOURS = "%dh";

    @SuppressWarnings("FieldCanBeLocal")
    private final String MAX_DAYS = "5 days";

    @SuppressWarnings("FieldCanBeLocal")
    private final String FORMAT_DAYS = "%d days";

    @SuppressWarnings("FieldCanBeLocal")
    private final String MAX_MONTHS = "4 months and 5 days";

    @SuppressWarnings("FieldCanBeLocal")
    private final String FORMAT_MONTH = "%d months and %d days";

    @SuppressWarnings("FieldCanBeLocal")
    private final String MAX_YEARS = "3 years and 4 months and 5 days";

    @SuppressWarnings("FieldCanBeLocal")
    private final String FORMAT_YEARS = "%d years and %d months and %d days";

    @Before
    public void setup() {

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
                mEndDateTime.toLocalDate().minusDays(5).minusMonths(4),
                mEndDateTime.toLocalTime());

        String time = calculate(mBeginDateTime, mEndDateTime);
        assertEquals(MAX_MONTHS, time);
    }

    @Test
    public void maxIsYears() {

        mBeginDateTime = LocalDateTime.of(
                mEndDateTime.toLocalDate().minusDays(5).minusMonths(4).minusYears(3),
                mEndDateTime.toLocalTime());

        String time = calculate(mBeginDateTime, mEndDateTime);
        assertEquals(MAX_YEARS, time);
    }

    @Test
    public void myBirthday() {

        final Calendar cal = Calendar.getInstance();
        cal.set(1987, 2, 29, 9, 15, 0);

        final LocalDateTime beginDateTime = LocalDateTime
                .ofInstant(Instant.ofEpochMilli(cal.getTimeInMillis()), ZoneId.systemDefault());

        final String calculate = calculate(beginDateTime, LocalDateTime.now());

        System.out.printf(calculate);

    }

    private String calculate(LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        String elapsed = calculeDate(beginDateTime.toLocalDate(), endDateTime.toLocalDate());
        if (TextUtils.isEmpty(elapsed)) {
            elapsed = calculeTime(beginDateTime.toLocalTime(), endDateTime.toLocalTime());
        }
        return elapsed;
    }

    private String calculeDate(LocalDate dateBegin, LocalDate dateEnd) {
        Period p = Period.between(dateBegin, dateEnd);
        if (p.getYears() > 0) {
            return String.format(Locale.getDefault(),FORMAT_YEARS, p.getYears(), p.getMonths(),
                    p.getDays());
        } else if (p.getMonths() > 0) {
            return String.format(Locale.getDefault(),FORMAT_MONTH, p.getMonths(), p.getDays());
        } else if (p.getDays() > 0) {
            return String.format(Locale.getDefault(),FORMAT_DAYS, p.getDays());
        } else {
            return null;
        }
    }

    private String calculeTime(LocalTime timeBegin, LocalTime timeEnd) {
        Duration d = Duration.between(timeBegin, timeEnd);
        if (d.toHours() > 0) {
            return String.format(Locale.getDefault(),FORMAT_HOURS, d.toHours());
        } else if (d.toMinutes() > 0) {
            return String.format(Locale.getDefault(),FORMAT_MINUTES, d.toMinutes());
        } else {
            return String.format(Locale.getDefault(),FORMAT_SECONDS, d.getSeconds());
        }
    }
}