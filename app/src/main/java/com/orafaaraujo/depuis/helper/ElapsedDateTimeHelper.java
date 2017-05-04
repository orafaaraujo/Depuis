package com.orafaaraujo.depuis.helper;

import android.content.Context;
import android.text.TextUtils;

import com.orafaaraujo.depuis.R;

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
 * Class used to calculate the elapsed datetime since the @{@link com.orafaaraujo.depuis.model.Fact}
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
     * @param timestamp The elapsed time in milliseconds
     * @return The string formatted by the maximo time passed. For example, if was 15 minutes will
     * return "15m", but if is 20 days will return "20 days" and go on.
     */
    public String getTime(long timestamp) {

        final LocalDateTime beginDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());

        return calculate(beginDateTime, LocalDateTime.now());
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
            return mContext.getString(R.string.elapsed_years, p.getYears(), p.getMonths(),
                    p.getDays());
        } else if (p.getMonths() > 0) {
            return mContext.getString(R.string.elapsed_months, p.getMonths(), p.getDays());
        } else if (p.getDays() > 0) {
            return mContext.getString(R.string.elapsed_days, p.getDays());
        } else {
            return null;
        }
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
}
