package com.orafaaraujo.depuis.view.bindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class BindingAdapterHelper {

    FontCache mFontCache;

    public BindingAdapterHelper(FontCache fontCache) {
        mFontCache = fontCache;
    }

    @BindingAdapter({"bind:watcher"})
    public void setWatcher(final TextInputEditText text, final TextWatcher watcher) {
        text.addTextChangedListener(watcher);
    }

    @BindingAdapter({"bind:adapter"})
    public void setAdapter(final RecyclerView view, final RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:layout_manager"})
    public void setLayoutManager(final RecyclerView rv, final RecyclerView.LayoutManager lm) {
        rv.setLayoutManager(lm);
    }

    @BindingAdapter({"bind:divider_item"})
    public void setDividerItem(final RecyclerView view, final DividerItemDecoration id) {
        view.addItemDecoration(id);
    }

    @BindingAdapter({"bind:on_scroll_listener"})
    public void setScrollListener(final RecyclerView view, RecyclerView.OnScrollListener l) {
        view.addOnScrollListener(l);
    }

    @BindingAdapter({"bind:item_touch"})
    public void setItemTouchHelper(final RecyclerView view, final ItemTouchHelper ith) {
        ith.attachToRecyclerView(view);
    }

    @BindingAdapter({"bind:fab_animation"})
    public void setFabAnimation(final FloatingActionButton fab, final boolean show) {
        if (show) {
            fab.show();
        } else {
            fab.hide();
        }
    }

    @BindingAdapter({"bind:background"})
    public void setViewGroupBackground(final ViewGroup viewGroup, @ColorRes final int resource) {
        viewGroup.setBackgroundColor(ContextCompat.getColor(viewGroup.getContext(), resource));
    }

    @BindingAdapter({"bind:source"})
    public void setImageButtonSource(final ImageButton imageButton,
            @DrawableRes final int resource) {
        imageButton.setImageResource(resource);
    }

    @BindingAdapter({"bind:text_icon"})
    public void setSvgTextView(final TextView textView, @DrawableRes final int resource) {
        Drawable drawableLeft = AppCompatResources.getDrawable(textView.getContext(), resource);
        textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
    }

    @BindingAdapter({"bind:text_font"})
    public void setFont(final View view, final String fontName) {
        if (view instanceof TextView) {
            ((TextView) view).setTypeface(mFontCache.get(fontName));
        }
        if (view instanceof Button) {
            ((Button) view).setTypeface(mFontCache.get(fontName));
        }
        if (view instanceof TextView) {
            ((TextView) view).setTypeface(mFontCache.get(fontName));
        }
        if (view instanceof TextInputLayout) {
            ((TextInputLayout) view).setTypeface(mFontCache.get(fontName));

        }
    }
}