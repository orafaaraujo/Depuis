package com.orafaaraujo.depuis.dagger.module;

import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.helper.ElapsedDateTimeHelper;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/**
 * Module to hold all object used to format content.
 *
 * Created by rafael on 22/01/17.
 */
@Module
public class FormatterModule {

    @Provides
    @Reusable
    ElapsedDateTimeHelper elapsedDateTimeHelper() {
        return new ElapsedDateTimeHelper();
    }

    @Provides
    @Reusable
    DateTimeHelper dateTimeHelper() {
        return new DateTimeHelper();
    }


}
