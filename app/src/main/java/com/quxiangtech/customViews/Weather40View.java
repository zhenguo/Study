package com.quxiangtech.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.quxiangtech.myapplication.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Weather40View extends CardView {
    private static final String TAG = "Weather40View";
    private Paint mPaint;
    private Path mPath;
    private float startX;
    private float startY;
    private final RectF mInfoRect = new RectF();
    private List<RectF> mDividerCache = new ArrayList<>();
    private final RectF[] mArrayBuff = new RectF[40];
    private List<Integer> mMaxTempList = new ArrayList<>();
    private int mMaxTemp, mMinTemp;
    private int mTempFactor = -1;
    private float mDividerGap;
    private final RectF mTextBound = new RectF();
    private List<FortyDailyResponse.DataBean> mData;
    private final DateFormat mDateFormat1 = new SimpleDateFormat("M月d日", Locale.CHINESE);
    private final DateFormat mDateFormat2 = new SimpleDateFormat("M/d", Locale.CHINESE);
    private String[] mDates = new String[5];
    private SpannableString mTitle;
    private Drawable mRainDrawable, mNoRainDrawable;
    private LinearGradient mInfoShader;
    private TextPaint mTextPaint;
    private DynamicLayout mDynamicLayout;
    private int mHitIndex = -1;
    private float mInfoWidth, mInfoHeight;
    private float mDividierHeight;

    public Weather40View(Context context) {
        super(context);

        initInternal();
    }

    public Weather40View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initInternal();
    }

    public Weather40View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initInternal();
    }

    public void setData(List<FortyDailyResponse.DataBean> entity) {
        if (entity != null) {
            mData = entity;
            int lowTemp = 0;
            int highTemp = 0;
            int rainDay = 0;

            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).getPrep_status() == 1) {
                    rainDay++;
                }
                if (mData.get(i).getTemp_status() == -1) {
                    lowTemp++;
                }
                if (mData.get(i).getTemp_status() == 1) {
                    highTemp++;
                }
                mMaxTempList.add(mData.get(i).getMaxTemp());
                if (i == 0) {
                    mDates[0] = mDateFormat2.format(mData.get(i).getTimestamp());
                }
                if (i == 9) {
                    mDates[1] = mDateFormat2.format(mData.get(i).getTimestamp());
                }
                if (i == 19) {
                    mDates[2] = mDateFormat2.format(mData.get(i).getTimestamp());
                }
                if (i == 29) {
                    mDates[3] = mDateFormat2.format(mData.get(i).getTimestamp());
                }
                if (i == 39) {
                    mDates[4] = mDateFormat2.format(mData.get(i).getTimestamp());
                }
            }

            mTitle = new SpannableString(lowTemp + "天降温/" + highTemp + "天升温, " + rainDay + "天有雨");
            mTitle.setSpan(new RelativeSizeSpan(1.3f), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            mTitle.setSpan(new RelativeSizeSpan(1.3f), 5, 6, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            mTitle.setSpan(new RelativeSizeSpan(1.3f), 11, 12, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            // 温度gap
            mMaxTemp = Collections.max(mMaxTempList, (o1, o2) -> (int) (o1 - o2));
            mMinTemp = Collections.min(mMaxTempList, (o1, o2) -> (int) (o1 - o2));
            float height = mDividierHeight - mInfoHeight - getResources().getDimensionPixelSize(R.dimen.dp_7);
            mTempFactor = (int) (height / (mMaxTemp));

            setVisibility(VISIBLE);
        }
    }

    private void initInternal() {
        setVisibility(GONE);
        setCardBackgroundColor(getResources().getColor(android.R.color.white));
        setPadding(getResources().getDimensionPixelSize(R.dimen.dp_10), getResources().getDimensionPixelSize(R.dimen.dp_15),
                getResources().getDimensionPixelSize(R.dimen.dp_2), getResources().getDimensionPixelSize(R.dimen.dp_15));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();

        mRainDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic___rain);
        mNoRainDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic___norain);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.weather_title_size));
        mTextPaint.setColor(Color.parseColor("#FF333333"));

        mInfoWidth = getResources().getDimensionPixelSize(R.dimen.dp_129);
        mInfoHeight = getResources().getDimensionPixelSize(R.dimen.dp_22);
        mDividierHeight = getResources().getDimensionPixelSize(R.dimen.dp_130);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
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

        float infoLeft = (getRight() - getLeft()) / 2f - mInfoWidth / 2f;
        float infoRight = (getRight() - getLeft()) / 2f + mInfoWidth / 2f;
        mInfoRect.set(infoLeft, getResources().getDimensionPixelSize(R.dimen.dp_53), infoRight, getResources().getDimensionPixelSize(R.dimen.dp_53) + mInfoHeight);

        mInfoShader = new LinearGradient(mInfoRect.left, mInfoRect.top, mInfoRect.left, mInfoRect.top + (mInfoRect.bottom - mInfoRect.top) / 2,
                Color.parseColor("#FF2E92BE"), Color.parseColor("#FF6FBDE5"), Shader.TileMode.CLAMP);
    }

    private final GestureDetector mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            getParent().requestDisallowInterceptTouchEvent(true);


            mTextBound.set(e2.getX(), e2.getY(), 0, 0);
            int index = Arrays.binarySearch(mDividerCache.toArray(mArrayBuff), mTextBound, new Comparator<RectF>() {
                @Override
                public int compare(RectF o1, RectF o2) {
                    if (o1.contains(o2.left, o2.top)) {
                        return 0;
                    }

                    return (int) (o1.left - o2.left);
                }
            });
            if (index >= 0 && index != mHitIndex) {
                mHitIndex = index;
                Log.i(TAG, "onScroll: invalidate");
                invalidate();
                return true;
            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }

    });

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                startX = event.getX();
                startY = event.getY();

                break;
        }

        return true; // force disallow parent intercept touch event
    }

    private void drawTitle(Canvas canvas) {
        canvas.save();
        canvas.translate(0, getResources().getDimensionPixelSize(R.dimen.dp_15));
        if (mDynamicLayout == null) {
            mDynamicLayout = new DynamicLayout(mTitle, mTextPaint, getWidth(), Layout.Alignment.ALIGN_CENTER, 0, 0, false);
        }
        mDynamicLayout.draw(canvas);
        canvas.restore();
    }

    private void drawDayDivider(Canvas canvas) {
        // 第一天和最后一天设置成透明色
        mPaint.reset();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAlpha(204);
        mPaint.setStyle(Paint.Style.FILL);

        float startX = getResources().getDimensionPixelSize(R.dimen.dp_28);
        float startY = getResources().getDimensionPixelSize(R.dimen.dp_53);
        float dividerWidth = getResources().getDimensionPixelSize(R.dimen.dp_1);
        float dividerHeight = mDividierHeight;
        mDividerGap = (getRight() - getLeft() - getResources().getDimensionPixelSize(R.dimen.dp_28)
                - getResources().getDimensionPixelSize(R.dimen.dp_2) - (dividerWidth * 40)) / 39f;

        for (int i = 0; i < 40; i++) {
            if (i == 0 || i == 39) {
                mPaint.setColor(getResources().getColor(android.R.color.transparent));
            } else {
                mPaint.setColor(Color.parseColor("#33D8D8D8"));
            }

            mDividerCache.get(i).set(startX, startY, startX + dividerWidth, startY + dividerHeight);
            canvas.drawRect(startX, startY, startX + dividerWidth, startY + dividerHeight, mPaint);
            startX += dividerWidth;
            startX += mDividerGap;
        }
    }

    private void drawHitDivider(Canvas canvas) {
        if (mHitIndex == -1) {
            return;
        }
        // 第一天和最后一天设置成透明色
        mPaint.reset();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        float startX = getResources().getDimensionPixelSize(R.dimen.dp_28);
        float startY = getResources().getDimensionPixelSize(R.dimen.dp_53);
        float dividerWidth = getResources().getDimensionPixelSize(R.dimen.dp_1);
        float dividerHeight = mDividierHeight;

        StringBuilder info = new StringBuilder();
        info.setLength(0);
        info.append(mDateFormat1.format(new Date(mData.get(mHitIndex).getTimestamp())))
                .append(" ").append(mData.get(mHitIndex).getDayConditionstext())
                .append(" ").append(mData.get(mHitIndex).getMaxTemp()).append("°");

        startX += dividerWidth * mHitIndex;
        startX += mDividerGap * mHitIndex;

        mPaint.setColor(Color.parseColor("#FF3E9CC7"));
        canvas.drawRect(startX, startY, startX + dividerWidth, startY + dividerHeight, mPaint);

        mPaint.setShader(mInfoShader);
        canvas.drawRoundRect(mInfoRect, getResources().getDimensionPixelSize(R.dimen.dp_11),
                getResources().getDimensionPixelSize(R.dimen.dp_11), mPaint);

        mPaint.setShader(null);
        mPaint.setColor(getResources().getColor(android.R.color.white));
        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.weather_title_size_12));
        mPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        canvas.drawText(info.toString(), mInfoRect.centerX(),
                mInfoRect.centerY() + dy, mPaint);

        // 白圈
        mPaint.setColor(getResources().getColor(android.R.color.white));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dp_2));
        canvas.drawCircle(startX, mDividerCache.get(mHitIndex).bottom - mMaxTempList.get(mHitIndex) * mTempFactor, getResources().getDimensionPixelSize(R.dimen.dp_2), mPaint);
    }

    private void drawCurve(Canvas canvas) {
        mPaint.reset();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#2E92BE"));
        mPaint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dp_4));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        for (int i = 0; i < mDividerCache.size(); i++) {
            if (i + 1 >= mDividerCache.size()) {
                break;
            }

            float startX = mDividerCache.get(i).left;
            if (i == 0) {
                startX += mDividerGap / 2;
            }
            float startY = mDividerCache.get(i).bottom - mMaxTempList.get(i) * mTempFactor;
            float stopX = mDividerCache.get(i + 1).left;
            float stopY = mDividerCache.get(i + 1).bottom - mMaxTempList.get(i + 1) * mTempFactor;
            mPath.reset();
            mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
            mPath.moveTo(startX, startY);
            mPath.quadTo(stopX, stopY, stopX, stopY);
            canvas.drawPath(mPath, mPaint);
        }
    }

    public void drawTemp(Canvas canvas) {
        mPaint.reset();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#FF999999"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(204);
        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.weather_title_size_12));
        mPaint.setFakeBoldText(true);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dp_1));

        String maxText = mMaxTemp + "°";
        int max = mMaxTemp;
        max *= mTempFactor;
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        mTextBound.set(getResources().getDimensionPixelSize(R.dimen.dp_10),
                mDividerCache.get(0).bottom - max - (fontMetrics.bottom - fontMetrics.top) / 2,
                getResources().getDimensionPixelSize(R.dimen.dp_10) + mPaint.measureText(String.valueOf(max)),
                mDividerCache.get(0).bottom - max + (fontMetrics.bottom - fontMetrics.top) / 2);
//        canvas.drawRect(mTextBound, mPaint);
        canvas.drawText(maxText, mTextBound.left, mTextBound.centerY() + dy, mPaint);
        mPaint.setColor(Color.parseColor("#33D8D8D8"));
        canvas.drawLine(mDividerCache.get(1).left, mDividerCache.get(0).bottom - max, mDividerCache.get(mDividerCache.size() - 2).right,
                mDividerCache.get(0).bottom - max, mPaint);

        // 找到最低温
        String minText = mMinTemp + "°";
        int min = mMinTemp;
        min *= mTempFactor;
        mTextBound.set(getResources().getDimensionPixelSize(R.dimen.dp_10),
                mDividerCache.get(0).bottom - min - (fontMetrics.bottom - fontMetrics.top) / 2,
                getResources().getDimensionPixelSize(R.dimen.dp_10) + mPaint.measureText(String.valueOf(max)),
                mDividerCache.get(0).bottom - min + (fontMetrics.bottom - fontMetrics.top) / 2);
//        canvas.drawRect(mTextBound, mPaint);
        mPaint.setColor(Color.parseColor("#FF999999"));
        canvas.drawText(minText, mTextBound.left, mTextBound.centerY() + dy, mPaint);
        mPaint.setColor(Color.parseColor("#33D8D8D8"));
        canvas.drawLine(mDividerCache.get(1).left, mDividerCache.get(0).bottom - min, mDividerCache.get(mDividerCache.size() - 2).right,
                mDividerCache.get(0).bottom - min, mPaint);
    }

    public void drawDate(Canvas canvas) {
        mPaint.reset();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#66999999"));
        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.weather_title_size_12));
        mPaint.setTextAlign(Paint.Align.LEFT);

        float startX = mDividerCache.get(1).left;
        float startY = mDividerCache.get(1).bottom + getResources().getDimensionPixelSize(R.dimen.dp_28);
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
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#FF999999"));
        mPaint.setFakeBoldText(true);
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
        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_10));
        canvas.drawText("降水", mTextBound.left, mTextBound.centerY() + dy, mPaint);
        Drawable drawable;
        for (int i = 1; i < mDividerCache.size() - 1; i++) {
            if (mData.get(i).getPrep_status() == 1) {
                drawable = mRainDrawable;
            } else {
                drawable = mNoRainDrawable;
            }

            if (drawable == null) {
                break;
            }

            int left = (int) (mDividerCache.get(i).left + getResources().getDimensionPixelSize(R.dimen.dp_05) - drawable.getIntrinsicWidth() / 2);
            int top;
            if (mData.get(i).getPrep_status() == 1) {
                top = (int) (mDividerCache.get(i).bottom + getResources().getDimensionPixelSize(R.dimen.dp_8));
            } else {
                top = (int) (mDividerCache.get(i).bottom + getResources().getDimensionPixelSize(R.dimen.dp_10));
            }

            int right = (int) (mDividerCache.get(i).left + getResources().getDimensionPixelSize(R.dimen.dp_05) + drawable.getIntrinsicWidth() / 2);
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

        mMaxTempList.clear();
        mMaxTempList = null;

        mRainDrawable = null;
        mNoRainDrawable = null;

        mDates = null;
    }
}
