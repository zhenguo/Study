package com.quxiangtech.customViews;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.Nullable;

import com.quxiangtech.myapplication.R;

public class CircleDragView extends View {
    private static final String TAG = "TestDrag";
    private Paint mPaint;
    private Rect mRect;
    private double mInitY = -1;
    private double mInitX = -1;
    private double mViewInitY = -1;
    private double mViewInitX = -1;
    private ViewConfiguration mViewConfiguration = ViewConfiguration.get(getContext());

    public CircleDragView(Context context) {
        super(context);
    }

    public CircleDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(getContext().getColor(android.R.color.holo_blue_dark));
    }

    public CircleDragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void processY(MotionEvent event) {
        double y = event.getRawY();

        double distance = Math.abs(mInitY - y);
        if (distance >= mViewConfiguration.getScaledTouchSlop()) {
            getParent().requestDisallowInterceptTouchEvent(true);

            if (mInitY < y) {
                // 下移
                setY((float) (mViewInitY + distance));
            } else {
                // 上移
                setY((float) (mViewInitY - distance));
            }
        } else {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
    }

    private void processX(MotionEvent event) {
        double x = event.getRawX();

        double distance = Math.abs(mInitX - x);

        if (distance >= mViewConfiguration.getScaledTouchSlop()) {
            getParent().requestDisallowInterceptTouchEvent(true);

            if (mInitX < x) {
                // 右移
                setX((float) (mViewInitX + distance));
            } else {
                // 左移
                setX((float) (mViewInitX - distance));
            }
        } else {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInitX = event.getRawX();
                mInitY = event.getRawY();
                mViewInitY = getY();
                mViewInitX = getX();
                break;
            case MotionEvent.ACTION_MOVE:
                processX(event);
                processY(event);
                break;
            default:
                break;
        }
        return true; // 消费事件，否则接收不到MOVE
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mRect == null) {
            mRect = new Rect();
        }
        mRect.set(0 + 10, 0 + 10, getWidth() - 10, getHeight() - 10);

        canvas.drawCircle(getPivotX(), getPivotY(), getWidth() / 2, mPaint);
    }
}
