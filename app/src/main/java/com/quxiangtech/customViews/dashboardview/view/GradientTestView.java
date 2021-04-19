package com.quxiangtech.customViews.dashboardview.view;

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

public class GradientTestView extends View {
    private Paint mPaint;
    private SweepGradient mSweepGradient;
    private Matrix mMatrix;
    private float mWidth;
    private RectF rectF;
    private int lineDistance;
    /**
     * 旋转的角度
     **/
    private int degree = 135;

    public GradientTestView(Context context) {
        super(context);
    }

    public GradientTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInternal();
    }

    public GradientTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initInternal() {
        setWillNotDraw(false);

        mMatrix = new Matrix();

        rectF = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        lineDistance = (int) (mWidth / 2 / 2);
        mSweepGradient = new SweepGradient(w / 2f, h / 2f, new int[]{Color.TRANSPARENT, Color.parseColor("#41CC00")}, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        for (int i = 0; i < 2; i++) {
            rectF.top = 10 + i * lineDistance;
            rectF.left = 10 + i * lineDistance;
            rectF.bottom = (mWidth - 10) - i * lineDistance;
            rectF.right = (mWidth - 10) - +i * lineDistance;

            //先画圆圈
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(10);
            mPaint.setColor(Color.parseColor("#05E60B"));
            canvas.drawArc(rectF, 0, 360, false, mPaint);
        }

        //画点
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        canvas.drawPoint(mWidth / 2, mWidth / 2, mPaint);

        //画扫描框
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(mSweepGradient);
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2 - 5, mPaint);

        // 一定要Reset（具体原因还在调研中）
        mPaint.reset();
        //使用Matrix旋转
        mSweepGradient.setLocalMatrix(mMatrix);
        mMatrix.setRotate(degree, mWidth / 2, mWidth / 2);
        degree++;
        if (degree > 225) {
            degree = 135;
        }
        postInvalidate();
    }
}
