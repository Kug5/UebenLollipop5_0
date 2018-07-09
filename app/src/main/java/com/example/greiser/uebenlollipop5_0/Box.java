package com.example.greiser.uebenlollipop5_0;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class Box extends FrameLayout {
    private String boxnr;
    private String counter;

    public Box(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Box(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }


    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Box, defStyle, 0);

        boxnr = a.getString(
                R.styleable.Box_boxnr);
        counter = a.getString(
                R.styleable.Box_counter);

        inflate(getContext(), R.layout.sample_box, this);

        TextView box_number = findViewById(R.id.boxnr);
        if (box_number != null)
            box_number.setText(boxnr);

        TextView counter_text = findViewById(R.id.counter);
        if (counter_text != null)
            counter_text.setText(counter);

//        a.recycle();


        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
//        mTextPaint.setTextSize(mExampleDimension);
//        mTextPaint.setColor(mExampleColor);
//        mTextWidth = mTextPaint.measureText(mExampleString);
//
//        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
//        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        // TODO: consider storing these as member variables to reduce
//        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;
    }
}
