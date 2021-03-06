package com.quxiangtech.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.quxiangtech.myapplication.R;
import com.quxiangtech.myapplication.TestApplication;

public class BottomDragView extends LinearLayout {
    private static final String TAG = "BottomDragView";
    private Paint mPaint;
    private Rect mBound;

    public BottomDragView(Context context) {
        super(context);
    }

    public BottomDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getContext().getColor(android.R.color.holo_red_dark));

        mBound = new Rect();
    }

    public BottomDragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "width: " + getWidth());

        mBound.set(10, 10, getWidth() - 10, getHeight() - 10);
        canvas.drawRect(mBound, mPaint);
    }
}
