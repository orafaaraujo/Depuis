package com.orafaaraujo.depuis.helper.bindingadapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * A simple font cache that makes a font once when it's first asked for and keeps it for the
 * life of the application.
 *
 * To use it, put your fonts in /assets/fonts.  You can access them in XML by their filename, minus
 * the extension (e.g. "Roboto-BoldItalic" or "roboto-bolditalic" for Roboto-BoldItalic.ttf).
 *
 * To set custom names for fonts other than their filenames, call addFont().
 */
@Singleton
public class FontCache {

    private static final String FONT_DIR = "fonts";

    private static Map<String, Typeface> cache = new HashMap<>();

    private static Map<String, String> fontMapping = new HashMap<>();

    Context mContext;

    @Inject
    public FontCache(Context context) {
        mContext = context;
        AssetManager am = mContext.getResources().getAssets();
        String fileList[];
        try {
            fileList = am.list(FONT_DIR);
        } catch (IOException e) {
            Timber.e("Error loading fonts from assets/fonts.", e);
            return;
        }

        for (String filename : fileList) {
            String alias = filename.substring(0, filename.lastIndexOf('.'));
            fontMapping.put(alias, filename);
            fontMapping.put(alias.toLowerCase(), filename);
        }
    }

    public void addFont(String name, String fontFilename) {
        fontMapping.put(name, fontFilename);
    }

    public Typeface get(String fontName) {
        String fontFilename = fontMapping.get(fontName);
        if (fontFilename == null) {
            Timber.e("Couldn't find font " + fontName
                    + ". Maybe you need to call addFont() first?");
            return null;
        }
        if (cache.containsKey(fontFilename)) {
            return cache.get(fontFilename);
        } else {
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(),
                    FONT_DIR + "/" + fontFilename);
            cache.put(fontFilename, typeface);
            return typeface;
        }
    }
}