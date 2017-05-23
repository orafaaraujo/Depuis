package com.orafaaraujo.depuis.helper;

import android.content.Context;
import android.text.TextUtils;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.model.FactModel;

import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Class used to calculate the elapsed datetime since the @{@link FactModel}
 * was created.
 *
 * Created by rafael on 21/01/17.
 */
@Reusable
public class ElapsedDateTimeHelper {

    @Inject
    Context mContext;

    @Inject
    ElapsedDateTimeHelper() {
    }

    /**
     * Method that return the formatted String of elapsed date and time.
     *
     * @param startTime The start time in milliseconds
     * @param endTime   The end time in milliseconds
     * @return The string formatted by the maximo time passed. For example, if was 15 minutes
     *     will return "15m", but if is 20 days will return "20 days" and go on.
     */
    public String getTime(long startTime, long endTime) {

        final LocalDateTime beginDateTime = LocalDateTime
                .ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault());

        // if the endtime is -1 that means that theres no end time, so count from now.
        final LocalDateTime endDateTime = endTime == -1
                ? LocalDateTime.now()
                : LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime), ZoneId.systemDefault());

        return calculate(beginDateTime, endDateTime);
    }

    private String calculate(LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        String elapsed = calculeDate(beginDateTime.toLocalDate(), endDateTime.toLocalDate());
        if (TextUtils.isEmpty(elapsed)) {
            elapsed = calculeTime(beginDateTime.toLocalTime(), endDateTime.toLocalTime());
        }
        return elapsed;
    }

    private String calculeDate(LocalDate dateBegin, LocalDate dateEnd) {
        Period period = Period.between(dateBegin, dateEnd);
        if (period.getYears() > 0) {
            return mContext.getResources().getQuantityString(R.plurals.elapsed_years,
                    period.getYears(), period.getYears(), period.getMonths(), period.getDays());
        } else if (period.getMonths() > 0) {
            return mContext.getResources().getQuantityString(R.plurals.elapsed_months,
                    period.getMonths(), period.getMonths(), period.getDays());
        } else if (period.getDays() > 0) {
            return mContext.getResources().getQuantityString(R.plurals.elapsed_days,
                    period.getDays(), period.getDays());
        } else {
            return null;
        }
    }

    private String calculeTime(LocalTime timeBegin, LocalTime timeEnd) {
        Duration duration = Duration.between(timeBegin, timeEnd);
        if (duration.toHours() > 0) {
            return mContext.getString(R.string.elapsed_hours, duration.toHours());
        } else if (duration.toMinutes() > 0) {
            return mContext.getString(R.string.elapsed_minutes, duration.toMinutes());
        } else {
            return mContext.getString(R.string.elapsed_seconds, duration.getSeconds());
        }
    }
}
