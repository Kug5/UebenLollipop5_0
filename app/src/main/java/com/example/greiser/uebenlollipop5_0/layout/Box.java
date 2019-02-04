package com.example.greiser.uebenlollipop5_0.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;

/**
 * TODO: document your custom view class.
 */
public class Box extends ConstraintLayout {
    private String boxnr;
    private String counter;

    public Box(Context context) {
        super(context);
        init(null, 0);
    }

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

        String tmpboxnr = a.getString(
                R.styleable.Box_boxnr);
        String tmpcounter = a.getString(
                R.styleable.Box_counter);

        inflate(getContext(), R.layout.sample_box, this);

        if (tmpboxnr != null)
            setBoxnr(tmpboxnr);
        if (tmpcounter != null)
            setCounter(tmpcounter);

    }

    public void setBoxnr(String boxnr) {
        this.boxnr = boxnr;

        TextView box_number = findViewById(R.id.boxnr);
        if (box_number != null && boxnr != null) {
            box_number.setText(boxnr);
        }

        invalidate();
    }

    public void setCounter(String counter) {
        this.counter = counter;

        TextView counter_text = findViewById(R.id.counter);
        if (counter_text != null && counter != null) {
            counter_text.setText(counter);
        }

        invalidate();
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
