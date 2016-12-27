package com.jpp.moviespreview.core.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.jpp.moviespreview.R;

/**
 * Created by jpperetti on 26/12/16.
 */

public class SuperLoadingView extends View {

    public static final int FULL_CIRCLE = 360;
    public static final int QUARTER_CIRCLE = 90;

    private Paint primaryColor;
    private Paint secondaryColor;

    private int sweepAngle;
    private int startAngle;

    private int strokeSize;

    private RectF viewBounds;
    private Paint currentColor;

    private ValueAnimator sweepAnim;
    private ValueAnimator startAnim;
    private ValueAnimator finalAnim;

    public SuperLoadingView(Context context) {
        super(context);
        init(null, 0);
    }

    public SuperLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SuperLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MPSuperloadingView, defStyleAttr, 0);

        int primaryColorInt = loadColor(a, R.styleable.MPSuperloadingView_primaryColor, R.color.colorPrimary);
        int secondaryColorInt = loadColor(a, R.styleable.MPSuperloadingView_secondaryColor, R.color.colorAccent);
        strokeSize = a.getDimensionPixelSize(R.styleable.MPSuperloadingView_stroke, getResources().getDimensionPixelSize(R.dimen.loading_stroke_width));

        primaryColor = createPaint(Paint.Style.STROKE, strokeSize, primaryColorInt);
        secondaryColor = createPaint(Paint.Style.STROKE, strokeSize, secondaryColorInt);

        updateColor();

        a.recycle();
    }

    /**
     * Load the color from xml attributes or fallback to the default if not found
     *
     * @param a            attributes typed array
     * @param index        the styleable index
     * @param defaultColor the default color resource id
     * @return the color to use
     */
    @ColorInt
    private int loadColor(TypedArray a, @StyleableRes int index, @ColorRes int defaultColor) {
        int loadedColor = getResources().getColor(defaultColor);
        ColorStateList colorList = a.getColorStateList(index);

        if (colorList != null) {
            loadedColor = colorList.getDefaultColor();
        }

        return loadedColor;
    }

    /**
     * Configure the animations that interpolate the arc values to achieve the loading effect
     */
    private void setupAnimations() {
        int duration = getResources().getInteger(R.integer.loading_spinning_time);

        // - Head and tail start together, when the head finishes the full spin the tail catches up - //
        sweepAnim = createAnimator(0, FULL_CIRCLE, duration);
        sweepAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (int) animation.getAnimatedValue();
            }
        });

        startAnim = createAnimator(0, QUARTER_CIRCLE, duration);
        startAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startAngle = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        finalAnim = createAnimator(QUARTER_CIRCLE, FULL_CIRCLE, duration);
        finalAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startAngle = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        finalAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                updateColor();
                sweepAnim.start();
                startAnim.start();
            }
        });

        sweepAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finalAnim.start();
            }
        });

        sweepAnim.start();
        startAnim.start();
    }

    /**
     * Update the color to use in this round.
     * The color changes each round between the primary and secondary color
     */
    @SuppressWarnings("PMD.CompareObjectsWithEquals")
    private void updateColor() {
        currentColor = currentColor == primaryColor ? secondaryColor : primaryColor;
    }

    /**
     * Create an animator that will interpolate the angles of the circle
     *
     * @param startAngle the start value of the angle in degrees. Eg: 0
     * @param endAngle   the end value of the angle in degrees. Eg: 270
     * @param duration   the duration of the animation
     * @return an animator that will interpolate the angles between startAngle and endAngle
     */
    private ValueAnimator createAnimator(int startAngle, int endAngle, int duration) {
        ValueAnimator animator = ValueAnimator.ofInt(startAngle, endAngle);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(duration);
        return animator;
    }

    /**
     * Create the paint that will be used to draw the view
     *
     * @param style       the paint style
     * @param strokeWidth the stroke width
     * @param hex         the color to paint
     * @return the paint to apply
     */
    private Paint createPaint(Paint.Style style, int strokeWidth, @ColorInt int hex) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(hex);

        return paint;
    }

    /**
     * When the view size changes, calculate its new size and start rotating from the center
     * {@inheritDoc}
     */
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        viewBounds = new RectF(strokeSize, strokeSize, width - strokeSize, height - strokeSize);

        if (this.getAnimation() != null) {
            this.getAnimation().cancel();
        }

        RotateAnimation rotateAnimation = new RotateAnimation(0f, FULL_CIRCLE, viewBounds.centerX(), viewBounds.centerY());
        rotateAnimation.setDuration(getResources().getInteger(R.integer.loading_rotation_time));
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        startAnimation(rotateAnimation);
    }

    /**
     * Draw the arc of the loading progress
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (getVisibility() == VISIBLE) {
            canvas.drawArc(viewBounds, startAngle, sweepAngle - startAngle, false, currentColor);
        }
    }

    /**
     * Call this on activity start to begin animations
     */
    public void onStart() {
        if (startAnim == null || !startAnim.isRunning()) {
            setupAnimations();
        }
    }

    /**
     * Call this on activity stop to finish animations
     */
    public void onStop() {
        cleanAnimator(startAnim);
        cleanAnimator(sweepAnim);
        cleanAnimator(finalAnim);
    }

    /**
     * Clean the animators so as not to leak memory
     *
     * @param animator the animator to clean
     */
    private void cleanAnimator(ValueAnimator animator) {
        if (animator != null) {
            animator.cancel();
            animator.removeAllListeners();
            animator.removeAllUpdateListeners();
        }
    }

    /**
     * Set the primary color of the loading wheel. Default is meli blue
     *
     * @param colorId the color resource id
     */
    public void setPrimaryColor(@ColorRes int colorId) {
        primaryColor = createPaint(Paint.Style.STROKE, strokeSize, getResources().getColor(colorId));
    }

    /**
     * Set the secondary color of the loading wheel. Default is meli yellow
     *
     * @param colorId the color resource id
     */
    public void setSecondaryColor(@ColorRes int colorId) {
        secondaryColor = createPaint(Paint.Style.STROKE, strokeSize, getResources().getColor(colorId));
    }

}
