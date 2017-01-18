package com.orafaaraujo.depuis.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by rafael on 18/01/17.
 */

@AutoValue
public abstract class Fact implements Parcelable {

    public abstract long timestamp();

    public abstract String title();

    public abstract String comment();

    public abstract boolean count();

    public static Builder builder() {
        return new AutoValue_Fact.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setTimestamp(long timestamp);

        public abstract Builder setTitle(String title);

        public abstract Builder setComment(String comment);

        public abstract Builder setCount(boolean count);

        public abstract Fact build();
    }
}

