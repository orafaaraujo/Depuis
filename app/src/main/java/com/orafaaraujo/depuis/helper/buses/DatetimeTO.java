package com.orafaaraujo.depuis.helper.buses;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class DatetimeTO {

    public abstract long milliseconds();

    public static DatetimeTO.Builder builder() {
        return new AutoValue_DatetimeTO.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setMilliseconds(long milliseconds);

        public abstract DatetimeTO build();
    }
}
