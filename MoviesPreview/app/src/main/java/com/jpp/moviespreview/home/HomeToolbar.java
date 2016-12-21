package com.jpp.moviespreview.home;

import android.content.Context;
import android.util.AttributeSet;

import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.toolbar.TransformingToolbar;

/**
 * {@link TransformingToolbar} implementation for the home screen. Will
 * show a white background with the search icon.
 * <p>
 * Created by jpperetti on 17/12/16.
 */
public class HomeToolbar extends TransformingToolbar {


    public HomeToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(R.drawable.search_box_background);
        setNavigationIcon(R.drawable.ic_action_search);
    }
}
