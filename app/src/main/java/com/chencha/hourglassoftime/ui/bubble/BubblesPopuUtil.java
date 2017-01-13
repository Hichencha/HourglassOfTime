package com.chencha.hourglassoftime.ui.bubble;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.chencha.hourglassoftime.R;

/**
 * Desc:   气泡
 * Author: chencha
 * Date: 17/1/12
 */

public class BubblesPopuUtil extends LinearLayout {

    private BubbleDrawable mBubbleDrawable;
    private float mArrowWidth;
    private float mAngle;
    private float mArrowHeight;
    private float mArrowPosition;
    private BubbleDrawable.ArrowLocation mArrowLocation;
    private int mBubbleColor;
    private boolean mArrowCenter;


    public BubblesPopuUtil(Context context) {
        super(context);
        initView(null);
    }

    public BubblesPopuUtil(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }


    private void initView(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.BubbleView);
            mArrowWidth = array.getDimension(R.styleable.BubbleView_arrowWidth,
                    BubbleDrawable.Builder.DEFAULT_ARROW_WITH);
            mArrowHeight = array.getDimension(R.styleable.BubbleView_arrowHeight,
                    BubbleDrawable.Builder.DEFAULT_ARROW_HEIGHT);
            mAngle = array.getDimension(R.styleable.BubbleView_angle,
                    BubbleDrawable.Builder.DEFAULT_ANGLE);
            mArrowPosition = array.getDimension(R.styleable.BubbleView_arrowPosition,
                    BubbleDrawable.Builder.DEFAULT_ARROW_POSITION);
            mBubbleColor = array.getColor(R.styleable.BubbleView_bubbleColor,
                    BubbleDrawable.Builder.DEFAULT_BUBBLE_COLOR);
            int location = array.getInt(R.styleable.BubbleView_arrowLocation, 0);
            mArrowLocation = BubbleDrawable.ArrowLocation.mapIntToValue(location);
            mArrowCenter = array.getBoolean(R.styleable.BubbleView_arrowCenter, false);
            array.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            setUp(w, h);
        }
    }

    private void setUp(int left, int right, int top, int bottom) {
        if (right < left || bottom < top)
            return;
        RectF rectF = new RectF(left, top, right, bottom);
        mBubbleDrawable = new BubbleDrawable.Builder()
                .rect(rectF)
                .arrowLocation(mArrowLocation)
                .bubbleType(BubbleDrawable.BubbleType.COLOR)
                .angle(mAngle)
                .arrowHeight(mArrowHeight)
                .arrowWidth(mArrowWidth)
                .arrowPosition(mArrowPosition)
                .bubbleColor(mBubbleColor)
                .arrowCenter(mArrowCenter)
                .build();
    }

    private void setUp(int width, int height) {
        setUp(getPaddingLeft(), width - getPaddingRight(),
                getPaddingTop(), height - getPaddingBottom());
        setBackgroundDrawable(mBubbleDrawable);
    }

    public void setUpBubbleDrawable() {
        setBackgroundDrawable(null);
        post(new Runnable() {
            @Override
            public void run() {
                setUp(getWidth(), getHeight());
            }
        });
    }

}

