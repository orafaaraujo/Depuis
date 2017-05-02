package com.orafaaraujo.depuis.helper;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by IgorEscodro on 18/03/17.
 *
 * @see
 * <a href="https://medium.com/google-developer-experts/persist-your-data-elegantly-u2020-way-c50be19acf9#.p53f7kt4u">Persist
 * your data elegantly: U2020 way - Medium</a>
 * @see <a href="https://github.com/pomopomo/WearPomodoro/">WearPomodoro - GitHub</a>
 */
public class IntPreference {

    private final SharedPreferences mPreferences;

    private final String mKey;

    private final int mDefaultValue;


    public IntPreference(@NonNull SharedPreferences preferences, @NonNull String key) {
        this(preferences, key, 0);
    }

    public IntPreference(@NonNull SharedPreferences preferences, @NonNull String key,
            @Nullable int defaultValue) {
        mPreferences = preferences;
        mKey = key;
        mDefaultValue = defaultValue;
    }

    @Nullable
    public int get() {
        return mPreferences.getInt(mKey, mDefaultValue);
    }

    public boolean isSet() {
        return mPreferences.contains(mKey);
    }

    public void set(@Nullable int value) {
        mPreferences.edit().putInt(mKey, value).apply();
    }

    public void delete() {
        mPreferences.edit().remove(mKey).apply();
    }
}