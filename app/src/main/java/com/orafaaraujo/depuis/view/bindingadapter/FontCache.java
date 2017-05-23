package com.orafaaraujo.depuis.view.bindingadapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
public class FontCache {

    private static final String FONT_DIR = "fonts";

    private static Map<String, Typeface> sCache = new HashMap<>();

    private static Map<String, String> sFontMapping = new HashMap<>();

    Context mContext;

    public FontCache(Context context) {
        mContext = context;
        AssetManager am = mContext.getResources().getAssets();
        String[] fileList;
        try {
            fileList = am.list(FONT_DIR);
        } catch (IOException e) {
            Timber.e(e, "Error loading fonts from assets/fonts.");
            return;
        }

        for (String filename : fileList) {
            String alias = filename.substring(0, filename.lastIndexOf('.'));
            sFontMapping.put(alias, filename);
            sFontMapping.put(alias.toLowerCase(Locale.getDefault()), filename);
        }
    }

    public void addFont(String name, String fontFilename) {
        sFontMapping.put(name, fontFilename);
    }

    public Typeface get(String fontName) {
        String fontFilename = sFontMapping.get(fontName);
        if (fontFilename == null) {
            Timber.e("Couldn't find font %s. Maybe you need to call addFont() first?", fontName);
            return null;
        }
        if (sCache.containsKey(fontFilename)) {
            return sCache.get(fontFilename);
        } else {
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(),
                    FONT_DIR + "/" + fontFilename);
            sCache.put(fontFilename, typeface);
            return typeface;
        }
    }
}