package com.orafaaraujo.depuis.model;

import com.google.auto.value.AutoValue;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Persistable;

/**
 * Created by rafael on 18/01/17.
 */
@Entity
@AutoValue
public abstract class Fact implements Persistable {

    @Key
    public abstract int id();

    public abstract long startTime();

    public abstract String title();

    public abstract String comment();

    public abstract boolean count();

    public static Builder builder() {
        return new AutoValue_Fact.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setId(int id);

        public abstract Builder setStartTime(long timestamp);

        public abstract Builder setTitle(String title);

        public abstract Builder setComment(String comment);

        public abstract Builder setCount(boolean count);

        public abstract Fact build();
    }
}

