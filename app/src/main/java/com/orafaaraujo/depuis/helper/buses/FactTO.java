package com.orafaaraujo.depuis.helper.buses;

import com.google.auto.value.AutoValue;
import com.orafaaraujo.depuis.model.Fact;

@AutoValue
public abstract class FactTO {

    public abstract Fact fact();

    public abstract int position();

    public abstract boolean delete();

    public abstract boolean close();

    public static FactTO.Builder builder() {
        return new AutoValue_FactTO.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setFact(Fact fact);

        public abstract Builder setPosition(int position);

        public abstract Builder setDelete(boolean delete);

        public abstract Builder setClose(boolean close);

        public abstract FactTO build();
    }
}
