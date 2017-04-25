package com.orafaaraujo.depuis.helper.buses;

import com.google.auto.value.AutoValue;

/**
 * Created by venturus on 25/04/17.
 */

@AutoValue
public abstract class NewFactFeedbackVO {

    public abstract boolean success();

    public abstract String message();

    public static NewFactFeedbackVO.Builder builder() {
        return new AutoValue_NewFactFeedbackVO.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setMessage(String message);

        public abstract Builder setSuccess(boolean success);

        public abstract NewFactFeedbackVO build();
    }
}
