package com.orafaaraujo.depuis.helper.buses;

import com.google.auto.value.AutoValue;
import com.orafaaraujo.depuis.model.FactModel;

@AutoValue
public abstract class FactTO {

    public static FactTO.Builder builder() {
        return new AutoValue_FactTO.Builder();
    }

    public abstract FactModel fact();

    public abstract int position();

    public abstract boolean newFact();

    public abstract boolean delete();

    public abstract boolean close();

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setFact(FactModel factModel);

        public abstract Builder setPosition(int position);

        public abstract Builder setNewFact(boolean newFact);

        public abstract Builder setDelete(boolean delete);

        public abstract Builder setClose(boolean close);

        public abstract FactTO build();
    }
}
