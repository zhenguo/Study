package com.quxiangtech.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;


import com.quxiangtech.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BezierCurveView extends CardView {
    private static final String TAG = "Weather40View";
    private Paint mPaint;
    private Path mPath;
    private float initX, startX, startY;
    private final RectF mInfoRect = new RectF();
    private List<RectF> mDividerCache = new ArrayList<>();
    List<Float> maxTemp = new ArrayList<>();
    private final RectF mTextBound = new RectF();
    private final String[] mDates = {"10/01", "10/11", "10/21", "11/01", "11/11"};

    public BezierCurveView(Context context) {
        super(context);

        initInternal();
    }

    public BezierCurveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initInternal();
    }

    public BezierCurveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initInternal();
    }

    private void initInternal() {
        setCardBackgroundColor(getResources().getColor(android.R.color.white));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();

        // 模拟最高温
        Random random = new Random(100);
        for (int i = 0; i < 40; i++) {
            maxTemp.add((float) random.nextInt(300));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = 0, height = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        final int pleft = getPaddingLeft();
        final int pright = getPaddingRight();
        final int ptop = getPaddingTop();
        final int pbottom = getPaddingBottom();

        if (widthMode == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        } else if (widthMode == MeasureSpec.UNSPECIFIED) {

        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {

        }

        Log.i(TAG, "onMeasure: " + width + " " + height);
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int centerX = w / 2;
        int centerY = h / 2;
        startX = centerX - 250;
        startY = centerY;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initX = event.getX();
            case MotionEvent.ACTION_MOVE:
                startX = event.getX();
                startY = event.getY();

                if (Math.abs(event.getX() - initX) > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    for (int i = 0; i < mDividerCache.size(); i++) {
                        if (mDividerCache.get(i).contains(startX, startY)) {
                            Log.i(TAG, "onTouchEvent: invalidate");
                            invalidate();
                            return true;
                        }
                    }
                }

                break;
        }

        return true; // force disallow parent intercept touch event
    }

    private void drawTitle(Canvas canvas) {
        String text = "4天降温/2天升温,20天有雨";
        mPaint.reset();
        mPaint.setDither(true);
        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.weather_title_size));
        mPaint.setColor(Color.parseColor("#333333"));
        mPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        mTextBound.set(0, 0, getRight() - getLeft(), (int) (fontMetrics.bottom - fontMetrics.top));
        canvas.drawText(text, mTextBound.centerX(), mTextBound.centerY() + dy + getResources().getDimensionPixelSize(R.dimen.dp_15), mPaint);
    }

    private void drawDayDivider(Canvas canvas) {
        // 第一天和最后一天设置成透明色
        mPaint.reset();
        mPaint.setDither(true);
        mPaint.setAlpha(204);
        mPaint.setStyle(Paint.Style.FILL);

        float startX = getResources().getDimensionPixelSize(R.dimen.dp_36);
        float startY = getResources().getDimensionPixelSize(R.dimen.dp_53);
        float dividerWidth = getResources().getDimensionPixelSize(R.dimen.dp_1);
        float dividerHeight = getResources().getDimensionPixelSize(R.dimen.dp_130);
        float dividerGap = getResources().getDimensionPixelSize(R.dimen.dp_7);

        for (int i = 0; i < 40; i++) {
            if (i == 0 || i == 39) {
                mPaint.setColor(getResources().getColor(android.R.color.transparent));
            } else {
                mPaint.setColor(Color.parseColor("#D8D8D8"));
            }

            mDividerCache.get(i).set(startX, startY, startX + dividerWidth, startY + dividerHeight);
            canvas.drawRect(startX, startY, startX + dividerWidth, startY + dividerHeight, mPaint);
            startX += dividerWidth;
            startX += dividerGap;
        }
    }

    private void drawHitDivider(Canvas canvas) {
        // 第一天和最后一天设置成透明色
        mPaint.reset();
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#3E9CC7"));

        float startX = getResources().getDimensionPixelSize(R.dimen.dp_36);
        float startY = getResources().getDimensionPixelSize(R.dimen.dp_53);
        float dividerWidth = getResources().getDimensionPixelSize(R.dimen.dp_1);
        float dividerHeight = getResources().getDimensionPixelSize(R.dimen.dp_130);
        float dividerGap = getResources().getDimensionPixelSize(R.dimen.dp_7);

        for (int i = 0; i < 40; i++) {
            if (mDividerCache.get(i).contains(this.startX, this.startY)) {
                this.startX = -1;
                this.startY = -1;

                mInfoRect.set(startX - getResources().getDimensionPixelSize(R.dimen.dp_129) / 2.0f, startY,
                        startX + getResources().getDimensionPixelSize(R.dimen.dp_129) / 2.0f,
                        startY + getResources().getDimensionPixelSize(R.dimen.dp_22));

                canvas.drawRect(startX, startY, startX + dividerWidth, startY + dividerHeight, mPaint);
                canvas.drawRoundRect(mInfoRect, getResources().getDimensionPixelSize(R.dimen.dp_11),
                        getResources().getDimensionPixelSize(R.dimen.dp_11), mPaint);

                String text = "8月20日 晴  最高24°";
                mPaint.setColor(getResources().getColor(android.R.color.white));
                mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.weather_title_size_12));
                mPaint.setTextAlign(Paint.Align.CENTER);

                Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
                float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
                canvas.drawText(text, mInfoRect.centerX(),
                        mInfoRect.centerY() + dy, mPaint);

                // 白圈
                mPaint.setColor(getResources().getColor(android.R.color.white));
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dp_2));
                canvas.drawCircle(startX, mDividerCache.get(i).bottom - maxTemp.get(i), getResources().getDimensionPixelSize(R.dimen.dp_2), mPaint);
                break;
            }

            startX += dividerWidth;
            startX += dividerGap;
        }
    }

    private void drawCurve(Canvas canvas) {
        mPaint.reset();
        mPaint.setDither(true);
        mPaint.setColor(Color.parseColor("#2E92BE"));
        mPaint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dp_4));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        for (int i = 0; i < mDividerCache.size(); i++) {
            if (i + 1 >= mDividerCache.size()) {
                break;
            }

            float startX = mDividerCache.get(i).left;
            float startY = mDividerCache.get(i).bottom - maxTemp.get(i);
            float stopX = mDividerCache.get(i + 1).left;
            float stopY = mDividerCache.get(i + 1).bottom - maxTemp.get(i + 1);
            mPath.reset();
            mPath.moveTo(startX, startY);
            mPath.quadTo(stopX, stopY, stopX, stopY);
            canvas.drawPath(mPath, mPaint);
        }
    }

    public void drawTemp(Canvas canvas) {
        mPaint.reset();
        mPaint.setDither(true);
        mPaint.setColor(Color.parseColor("#D8D8D8"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(204);
        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.weather_title_size_12));
        mPaint.setTextAlign(Paint.Align.LEFT);

        // 找到最高温
        float max = Collections.max(maxTemp, new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
                return (int) (o1 - o2);
            }
        });
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        mTextBound.set(getResources().getDimensionPixelSize(R.dimen.dp_10),
                mDividerCache.get(0).bottom - max - (fontMetrics.bottom - fontMetrics.top) / 2,
                getResources().getDimensionPixelSize(R.dimen.dp_10) + mPaint.measureText(String.valueOf(max)),
                mDividerCache.get(0).bottom - max + (fontMetrics.bottom - fontMetrics.top) / 2);
//        canvas.drawRect(mTextBound, mPaint);
        canvas.drawText(String.valueOf(max), mTextBound.left, mTextBound.centerY() + dy, mPaint);
        canvas.drawLine(mDividerCache.get(1).left, mDividerCache.get(0).bottom - max, mDividerCache.get(mDividerCache.size() - 2).right,
                mDividerCache.get(0).bottom - max, mPaint);

        // 找到最低温
        float min = Collections.min(maxTemp, new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
                return (int) (o1 - o2);
            }
        });
        mTextBound.set(getResources().getDimensionPixelSize(R.dimen.dp_10),
                mDividerCache.get(0).bottom - min - (fontMetrics.bottom - fontMetrics.top) / 2,
                getResources().getDimensionPixelSize(R.dimen.dp_10) + mPaint.measureText(String.valueOf(max)),
                mDividerCache.get(0).bottom - min + (fontMetrics.bottom - fontMetrics.top) / 2);
//        canvas.drawRect(mTextBound, mPaint);
        canvas.drawText(String.valueOf(min), mTextBound.left, mTextBound.centerY() + dy, mPaint);
        canvas.drawLine(mDividerCache.get(1).left, mDividerCache.get(0).bottom - min, mDividerCache.get(mDividerCache.size() - 2).right,
                mDividerCache.get(0).bottom - min, mPaint);
    }

    public void drawDate(Canvas canvas) {
        mPaint.reset();
        mPaint.setDither(true);
        mPaint.setColor(Color.parseColor("#FF999999"));
        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.weather_title_size_12));
        mPaint.setTextAlign(Paint.Align.LEFT);

        float startX = mDividerCache.get(1).left;
        float startY = mDividerCache.get(1).bottom + getResources().getDimensionPixelSize(R.dimen.dp_36);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;

        float textWidthTotal = 0;
        for (String mDate : mDates) {
            textWidthTotal += mPaint.measureText(mDate);
        }
        float textGap = (mDividerCache.get(mDividerCache.size() - 2).right - mDividerCache.get(1).left - textWidthTotal) / (mDates.length - 1);
        for (String mDate : mDates) {
            canvas.drawText(mDate, startX, startY + dy, mPaint);
            startX += (mPaint.measureText(mDate) + textGap);
        }
    }

    public void drawRainValue(Canvas canvas) {
        mPaint.reset();
        mPaint.setDither(true);
        mPaint.setColor(Color.parseColor("#D8D8D8"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.weather_title_size_12));
        mPaint.setTextAlign(Paint.Align.LEFT);

        int startX = getResources().getDimensionPixelSize(R.dimen.dp_10);
        int startY = getResources().getDimensionPixelSize(R.dimen.dp_185);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float textHeight = fontMetrics.bottom - fontMetrics.top;
        startY += (fontMetrics.bottom - fontMetrics.top) / 2;

        mTextBound.set(startX, startY, startX + mPaint.measureText("降水"), startY + textHeight / 2);
//        canvas.drawRect(mTextBound, mPaint);
        canvas.drawText("降水", mTextBound.left, mTextBound.centerY() + dy, mPaint);
        Drawable drawable;
        for (int i = 1; i < mDividerCache.size() - 1; i++) {
            if (i % 2 == 0) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic___rain);
            } else {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic___norain);
            }

            if (drawable == null) {
                break;
            }

            int left = (int) (mDividerCache.get(i).left - drawable.getIntrinsicWidth() / 2);
            int top = 0;
            if (i % 2 == 0) {
                top = (int) (mDividerCache.get(i).bottom + getResources().getDimensionPixelSize(R.dimen.dp_8));
            } else {
                top = (int) (mDividerCache.get(i).bottom + getResources().getDimensionPixelSize(R.dimen.dp_10));
            }

            int right = (int) (mDividerCache.get(i).left + drawable.getIntrinsicWidth() / 2);
            int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawTitle(canvas);
        drawDayDivider(canvas);
        drawCurve(canvas);
        drawHitDivider(canvas);
        drawTemp(canvas);
        drawRainValue(canvas);
        drawDate(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        for (int i = 0; i < 40; i++) {
            mDividerCache.add(new RectF());
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        // force GC
        mDividerCache.clear();
        mDividerCache = null;
    }
}
