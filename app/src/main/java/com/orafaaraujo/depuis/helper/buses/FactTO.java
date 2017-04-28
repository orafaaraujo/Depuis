package com.orafaaraujo.depuis.helper.buses;

import com.google.auto.value.AutoValue;
import com.orafaaraujo.depuis.model.Fact;

@AutoValue
public abstract class FactTO {

    public abstract Fact fact();

    public abstract int position();

    public static FactTO.Builder builder() {
        return new AutoValue_FactTO.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setFact(Fact fact);

        public abstract Builder setPosition(int position);

        public abstract FactTO build();
    }
}
