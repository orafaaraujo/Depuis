package com.orafaaraujo.depuis.viewModel.helper;

import android.databinding.BindingAdapter;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class BindingAdapterHelper {

    @BindingAdapter({"bind:watcher"})
    public static void setWatcher(final TextInputEditText text, final TextWatcher watcher) {
        text.addTextChangedListener(watcher);
    }

    @BindingAdapter({"bind:adapter"})
    public static void setAdapter(final RecyclerView view, final RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:layout_manager"})
    public static void setLayoutManager(final RecyclerView rv,
            final RecyclerView.LayoutManager lm) {
        rv.setLayoutManager(lm);
    }

    @BindingAdapter({"bind:divider_item"})
    public static void setDividerItem(final RecyclerView view, final DividerItemDecoration id) {
        view.addItemDecoration(id);
    }

    @BindingAdapter({"bind:on_scroll_listener"})
    public static void setScrollListener(final RecyclerView view, RecyclerView.OnScrollListener l) {
        view.addOnScrollListener(l);
    }

    @BindingAdapter({"bind:item_touch"})
    public static void setItemTouchHelper(final RecyclerView view, final ItemTouchHelper ith) {
        ith.attachToRecyclerView(view);
    }

    @BindingAdapter({"bind:fab_animation"})
    public static void setFabAnimation(final FloatingActionButton fab, final boolean show) {
        if (show) {
            fab.show();
        } else {
            fab.hide();
        }
    }

    @BindingAdapter({"bind:background"})
    public static void setViewGroupBackground(ViewGroup viewGroup, int resource) {
        viewGroup.setBackgroundColor(resource);
    }

    @BindingAdapter({"bind:source"})
    public static void setImateButtonSource(ImageButton imageButton, int resource) {
        imageButton.setImageResource(resource);
    }

}
