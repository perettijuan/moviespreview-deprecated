package com.jpp.moviespreview.core.util;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;

/**
 * Interface definition for a callback to be invoked when an item in this RecyclerView has been clicked.
 * <p>
 * Created by jpperetti on 6/12/16.
 */
public class RecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener {

    private boolean disallowIntercept;
    private final OnRecyclerViewItemClickListener listener;
    private final GestureDetector gestureDetector;

    public RecyclerViewItemClickListener(@NonNull Context context, @NonNull OnRecyclerViewItemClickListener listener) {
        disallowIntercept = false;
        this.listener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(final RecyclerView view, MotionEvent e) {
        final View childView = view.findChildViewUnder(e.getX(), e.getY());

        if (view.isEnabled() && childView != null && gestureDetector.onTouchEvent(e) && !disallowIntercept) {
            final int position = view.getChildAdapterPosition(childView);
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    // touch events don't play sounds.. play the sound manually
                    view.playSoundEffect(SoundEffectConstants.CLICK);
                    // run listener on the next cycle to allow ui effects before taking an action
                    listener.onRecyclerViewItemClick(view, childView, position, view.getAdapter().getItemId(position));
                }
            });
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        // We don't do anything
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        this.disallowIntercept = disallowIntercept;
    }

    public interface OnRecyclerViewItemClickListener {
        /**
         * Callback method to be invoked when an item in this AdapterView has been clicked.
         *
         * @param parent          The RecyclerView where the click happened
         * @param view            The view within the RecyclerView that was clicked (this will be a view provided by the adapter)
         * @param adapterPosition The position of the view in the adapter.
         * @param id              The row id of the item that was clicked.
         */
        void onRecyclerViewItemClick(@NonNull RecyclerView parent, @NonNull View view, int adapterPosition, long id);
    }
}