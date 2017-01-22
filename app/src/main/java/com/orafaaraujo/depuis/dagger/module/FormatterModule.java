package com.orafaaraujo.depuis.dagger.module;

import com.orafaaraujo.depuis.helper.DateTimeHelper;
import com.orafaaraujo.depuis.helper.ElapsedDateTimeHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 22/01/17.
 */
@Module
public class FormatterModule {

    public FormatterModule() {
    }

    @Provides
    @Singleton
    public ElapsedDateTimeHelper elapsedDateTimeHelper() {
        return new ElapsedDateTimeHelper();
    }

    @Provides
    @Singleton
    public DateTimeHelper dateTimeHelper() {
        return new DateTimeHelper();
    }


}
