package com.orafaaraujo.depuis.helper.buses;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class NewFactFeedbackTO {

    public static NewFactFeedbackTO.Builder builder() {
        return new AutoValue_NewFactFeedbackTO.Builder();
    }

    public abstract boolean success();

    public abstract String message();

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setMessage(String message);

        public abstract Builder setSuccess(boolean success);

        public abstract NewFactFeedbackTO build();
    }
}
