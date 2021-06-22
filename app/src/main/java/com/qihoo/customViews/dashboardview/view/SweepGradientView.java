package com.qihoo.customViews.dashboardview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SweepGradientView extends View {
    private Paint mNormalPaint;
    private Paint mSweepPaint;
    private SweepGradient mSweepGradient;
    private Matrix mMatrix;
    private RectF mRectF;
    private float mStartAngle = 0f;
    private float mEndAngle = 90f;
    private boolean mShowAnim;

    public SweepGradientView(Context context) {
        super(context);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInternal();
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initInternal() {
        mNormalPaint = new Paint();
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setDither(true);
        mNormalPaint.setStyle(Paint.Style.STROKE);
        mNormalPaint.setColor(Color.BLACK);
        mNormalPaint.setStrokeWidth(2);

        mMatrix = new Matrix();

        mShowAnim = true;
        mEndAngle = 1f;
    }

    private void initSweepPaint() {
        if (mSweepPaint == null) {
            mSweepPaint = new Paint();
        }
        mSweepPaint.reset();
        mSweepPaint.setAntiAlias(true);
        mSweepPaint.setDither(true);
        mSweepPaint.setStrokeCap(Paint.Cap.ROUND);
        mSweepPaint.setStyle(Paint.Style.FILL);
        mSweepPaint.setStrokeWidth(30);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mRectF = new RectF(0, 0, w, h);

        mSweepGradient = new SweepGradient(0, 0,
                new int[]{
//                        Color.parseColor("#F4F5F6"),
//                        Color.parseColor("#FAAC7C"),
//                        Color.parseColor("#FAAC7C"),
//                        Color.parseColor("#FF7828"),
//                        Color.parseColor("#FF7828"),
                        Color.WHITE, Color.RED
                },
                null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(mRectF, mNormalPaint);

        initSweepPaint();

        if (mShowAnim) {
            mSweepGradient.setLocalMatrix(mMatrix);
            mMatrix.setRotate(++mEndAngle, mRectF.height() / 2, mRectF.height() / 2);
            mSweepPaint.setShader(mSweepGradient);
            canvas.drawArc(mRectF, mStartAngle, mEndAngle, true, mSweepPaint);
            if (mEndAngle < 360) {
                postInvalidate();
            }
        } else {
            mMatrix.setRotate(mEndAngle, 0, 0);
            mSweepGradient.setLocalMatrix(mMatrix);
            mSweepPaint.setShader(mSweepGradient);
            canvas.drawArc(mRectF, mStartAngle, mEndAngle, false, mSweepPaint);
        }
    }
}
