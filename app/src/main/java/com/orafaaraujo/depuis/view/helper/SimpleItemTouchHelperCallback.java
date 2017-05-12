package com.orafaaraujo.depuis.view.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.orafaaraujo.depuis.R;
import com.orafaaraujo.depuis.view.adapter.FactAdapter;

/***
 * Callback of slide Card to left.
 */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private FactAdapter mAdapter;

    private Context mContext;

    public SimpleItemTouchHelperCallback(Context context) {
        mContext = context;
    }

    public void setAdapter(FactAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.START);
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
            RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
            float dX, float dY, int actionState, boolean isCurrentlyActive) {

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            if (dX < 0) {

                View itemView = viewHolder.itemView;
                final Paint paint = new Paint();
                paint.setColor(ResourcesCompat.getColor(mContext.getResources(),
                        R.color.main_primary, mContext.getTheme()));
                paint.setAlpha(40);

                final Bitmap bitmap = getBitmap(R.drawable.ic_delete_sweep_36dp);
                float height = (itemView.getHeight() / 2) - (bitmap.getHeight() / 2);
                float bitmapWidth = bitmap.getWidth();

                c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                        (float) itemView.getRight(), (float) itemView.getBottom(), paint);
                c.drawBitmap(bitmap, ((float) itemView.getRight() - bitmapWidth) - 96f,
                        (float) itemView.getTop() + height, null);
            }

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    private Bitmap getBitmap(@DrawableRes int drawable) {
        final Drawable vectorDrawable = AppCompatResources
                .getDrawable(mContext, drawable);
        final Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

}
