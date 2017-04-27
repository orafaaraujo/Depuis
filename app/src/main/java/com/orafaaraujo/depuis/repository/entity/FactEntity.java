package com.orafaaraujo.depuis.repository.entity;

import com.google.auto.value.AutoValue;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Persistable;

/**
 * Created by rafael on 31/01/17.
 */
@Entity
@AutoValue
public abstract class FactEntity implements Persistable {

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setId(int id);

        public abstract Builder setStartTime(long timestamp);

        public abstract Builder setTitle(String title);

        public abstract Builder setComment(String comment);

        public abstract Builder setCount(boolean count);

        public abstract FactEntity build();
    }

    public static Builder builder() {
        return new AutoValue_FactEntity.Builder().setId(-1);
    }

    @Key
    public abstract int id();

    public abstract long startTime();

    public abstract String title();

    public abstract String comment();

    public abstract boolean count();
}
