package com.orafaaraujo.depuis.model;

import com.google.auto.value.AutoValue;

/**
 * Model of a FactModel.
 *
 * Created by rafael on 18/01/17.
 */
@AutoValue
public abstract class FactModel {

    public static Builder builder() {
        return new AutoValue_FactModel.Builder();
    }

    public abstract long id();

    public abstract long startTime();

    public abstract String title();

    public abstract String comment();

    public abstract long endTime();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setId(long id);

        public abstract Builder setStartTime(long startTime);

        public abstract Builder setTitle(String title);

        public abstract Builder setComment(String comment);

        public abstract Builder setEndTime(long endTime);

        public abstract FactModel build();
    }
}

