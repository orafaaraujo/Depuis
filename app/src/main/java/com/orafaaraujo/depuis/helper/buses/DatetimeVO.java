package com.orafaaraujo.depuis.helper.buses;

import com.google.auto.value.AutoValue;

/**
 * Created by venturus on 25/04/17.
 */

@AutoValue
public abstract class DatetimeVO {

    public abstract long milliseconds();

    public static DatetimeVO.Builder builder() {
        return new AutoValue_DatetimeVO.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setMilliseconds(long milliseconds);

        public abstract DatetimeVO build();
    }
}
