package com.jpp.moviespreview.preview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jpp.moviespreview.R;

/**
 * Based on https://github.com/saulmm/CoordinatorBehaviorExample
 * Created by jpperetti on 3/1/17.
 */

public class PosterImageBehavior extends CoordinatorLayout.Behavior<SimpleDraweeView> {

    private float mCustomFinalHeight;

    private int mStartYPosition;
    private int mFinalYPosition;
    private int mStartHeight;

    private int mStartXPosition;
    private int mFinalXPosition;

    private float mStartToolbarPosition;
    private float mChangeBehaviorPoint;

    public PosterImageBehavior(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MPMoviewPosterBehavior);
            mCustomFinalHeight = a.getDimension(R.styleable.MPMoviewPosterBehavior_finalHeight, 0);
            a.recycle();
        }
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, SimpleDraweeView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, SimpleDraweeView child, View dependency) {
        // init all properties remaining
        initProperties(child, dependency);

        final int maxScrollDistance = (int) (mStartToolbarPosition);
        float expandedPercentageFactor = dependency.getY() / maxScrollDistance;
        if (expandedPercentageFactor < mChangeBehaviorPoint) {

            float heightFactor = (mChangeBehaviorPoint - expandedPercentageFactor) / mChangeBehaviorPoint;

            float distanceXToSubtract = ((mStartXPosition - mFinalXPosition)
                    * heightFactor) + (child.getHeight() / 2);
            float distanceYToSubtract = ((mStartYPosition - mFinalYPosition)
                    * (1f - expandedPercentageFactor)) + (child.getHeight() / 2);

            child.setX(mStartXPosition - distanceXToSubtract);
            child.setY(mStartYPosition - distanceYToSubtract);

            float heightToSubtract = ((mStartHeight - mCustomFinalHeight) * heightFactor);

            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            lp.width = (int) (mStartHeight - heightToSubtract);
            lp.height = (int) (mStartHeight - heightToSubtract);
            child.setLayoutParams(lp);

        } else {
            float distanceYToSubtract = ((mStartYPosition - mFinalYPosition)
                    * (1f - expandedPercentageFactor)) + (mStartHeight / 2);

            child.setX(mStartXPosition - child.getWidth() / 2);
            child.setY(mStartYPosition - distanceYToSubtract);

            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            lp.width = (mStartHeight);
            lp.height = (mStartHeight);
            child.setLayoutParams(lp);
        }

        return true;
    }


    private void initProperties(SimpleDraweeView child, View dependency) {
        if (mStartYPosition == 0) {
            mStartYPosition = (int) (dependency.getY());
        }

        if (mFinalYPosition == 0) {
            mFinalYPosition = (dependency.getHeight() / 2);
        }

        if (mStartHeight == 0) {
            mStartHeight = child.getHeight();
        }

        if (mStartXPosition == 0) {
            mStartXPosition = (int) (child.getX() + (child.getWidth() / 2));
        }

        if (mFinalXPosition == 0) {
            mFinalXPosition = dependency.getResources().getDimensionPixelOffset(R.dimen.abc_action_bar_content_inset_material) + ((int) mCustomFinalHeight / 2);
        }

        if (mStartToolbarPosition == 0) {
            mStartToolbarPosition = dependency.getY();
        }

        if (mChangeBehaviorPoint == 0) {
            mChangeBehaviorPoint = (child.getHeight() - mCustomFinalHeight) / (2f * (mStartYPosition - mFinalYPosition));
        }
    }
}
