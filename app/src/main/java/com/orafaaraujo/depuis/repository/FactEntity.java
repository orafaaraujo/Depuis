package com.orafaaraujo.depuis.repository;

import com.google.auto.value.AutoValue;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;

/**
 * Created by rafael on 31/01/17.
 */
@Entity
@AutoValue
public abstract class FactEntity {

    @Key
    @Generated
    public abstract int id();

    public abstract long startTime();

    public abstract String title();

    public abstract String comment();

    public abstract boolean count();

    public static Builder builder() {
        return new AutoValue_FactEntity.Builder().setId(-1);
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setId(int id);

        public abstract Builder setStartTime(long timestamp);

        public abstract Builder setTitle(String title);

        public abstract Builder setComment(String comment);

        public abstract Builder setCount(boolean count);

        public abstract FactEntity build();
    }
}
