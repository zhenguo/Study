package com.quxiangtech.customViews.dashboardview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.quxiangtech.myapplication.R;

public class SeresDashBordView extends View {
    private static final String TAG = "SeresDashBordView";
    private final static float DEFAULT_OIL_BG_WIDTH_DP = 16;
    private final static float DEFAULT_PERCENT_WIDTH_DP = 8;
    private final static float DEFAULT_DASHBORD_WIDTH_DP = 178;
    private final static float DEFAULT_SCALE_PADDING_DP = 8;

    private RectF mOutLineRectF;
    private RectF mBgRectF;
    private RectF mScaleRectF;
    private RectF mJourneyRectF;
    private RectF mStatusRectF;
    private Paint mJourneyPaint;
    private Paint normalPaint;
    private Paint mOilBgPaint;
    private Paint mOilStrokePaint;
    private Paint mScalePaint;
    private Paint mScaleBgPaint;
    private Paint mOilPercentPaint, mQoePercentPaint;
    private float mOilBgWidthDP = dpToPx((int) DEFAULT_OIL_BG_WIDTH_DP, getContext());
    private float mPercentWidthDP = dpToPx((int) DEFAULT_PERCENT_WIDTH_DP, getContext());
    private float mDashbordWidthDP = dpToPx((int) DEFAULT_DASHBORD_WIDTH_DP, getContext());
    private float mScalePaddingDP = dpToPx((int) DEFAULT_SCALE_PADDING_DP, getContext());
    private final static int DURING_ARC = 240;
    private final static int SCALE_COUNT = 40;
    private int mOilPercent, mQoePercent;
    private final static float TOTAL_ARC = 225f;
    private int mKilometre;
    private Drawable mOilDrawable;
    private Drawable mQoeDrawable;

    public SeresDashBordView(Context context) {
        super(context);
    }

    public SeresDashBordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SeresDashBordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        setWillNotDraw(false);
        float rotate;
        Matrix gradientMatrix = new Matrix();

        normalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        normalPaint.setStrokeWidth(2);
        normalPaint.setColor(Color.WHITE);
        normalPaint.setStyle(Paint.Style.STROKE);

        mOilStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOilStrokePaint.setStyle(Paint.Style.STROKE);
        mOilStrokePaint.setStrokeWidth(mOilBgWidthDP + dpToPx(2, getContext()));
        mOilStrokePaint.setStrokeCap(Paint.Cap.ROUND);
        mOilStrokePaint.setDither(true);
        SweepGradient shader = new SweepGradient(0, 0, new int[]{Color.parseColor("#FFF0F1F2"), Color.parseColor("#FFDADADB")}, null);
        rotate = 90f;
        gradientMatrix.preRotate(rotate, 0, 0);
        shader.setLocalMatrix(gradientMatrix);
//        mOilStrokePaint.setShader(shader);
        mOilStrokePaint.setColor(Color.BLACK);

        mOilBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOilBgPaint.setStyle(Paint.Style.STROKE);
        mOilBgPaint.setDither(true);
        mOilBgPaint.setStrokeWidth(mOilBgWidthDP);
        mOilBgPaint.setStrokeCap(Paint.Cap.ROUND);
        shader = new SweepGradient(0, 0, new int[]{Color.parseColor("#FFEAEAEA"),
                Color.parseColor("#FFF1F4F5")}, null);
        rotate = 90f;
        gradientMatrix.reset();
        gradientMatrix.preRotate(rotate, 0, 0);
        shader.setLocalMatrix(gradientMatrix);
        mOilBgPaint.setShader(shader);

        mScaleBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScaleBgPaint.setStyle(Paint.Style.FILL);
        mScaleBgPaint.setColor(Color.WHITE);

        mScalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScalePaint.setStyle(Paint.Style.STROKE);
        mScalePaint.setColor(Color.parseColor("#FFC4C4C4"));
        mScalePaint.setStrokeWidth(8);

        // 电量进度
        mQoePercentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mQoePercentPaint.setStyle(Paint.Style.STROKE);
        mQoePercentPaint.setStrokeCap(Paint.Cap.ROUND);
        mQoePercentPaint.setStrokeWidth(mPercentWidthDP);
        shader = new SweepGradient(0, 0, new int[]{
                Color.parseColor("#FF7828"),
                Color.parseColor("#F4F5F6"),

        }, null);
        gradientMatrix.reset();
        gradientMatrix.preRotate(225, 0, 0);
        shader.setLocalMatrix(gradientMatrix);
        mQoePercentPaint.setShader(shader);

        // 油量进度
        mOilPercentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOilPercentPaint.setStyle(Paint.Style.STROKE);
        mOilPercentPaint.setStrokeCap(Paint.Cap.ROUND);
        mOilPercentPaint.setStrokeWidth(mPercentWidthDP);
        shader = new SweepGradient(0, 0, new int[]{
                Color.parseColor("#33B5E5"),
                Color.parseColor("#9ADAF0"),
                Color.parseColor("#F4F5F6"),

        }, null);
        gradientMatrix.reset();
        gradientMatrix.postRotate(-45, 0, 0);
        shader.setLocalMatrix(gradientMatrix);
        mOilPercentPaint.setShader(shader);

        mJourneyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mJourneyPaint.setDither(true);
        mJourneyPaint.setStyle(Paint.Style.FILL);

        mOilDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_oil);
        mQoeDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_qoe);
        startOilPercentAnim();
        startQoePercentAnim();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float left = getWidth() / 2f - mDashbordWidthDP / 2;
        float top = dpToPx(15, getContext());
        float right, bottom;
        mOutLineRectF = new RectF(left, top, left + mDashbordWidthDP, top + mDashbordWidthDP); // 仪表盘外圈

        left = mOutLineRectF.left + mOilBgWidthDP / 2;
        top = mOutLineRectF.top + mOilBgWidthDP / 2;
        right = mOutLineRectF.right - mOilBgWidthDP / 2;
        bottom = mOutLineRectF.bottom - mOilBgWidthDP / 2;
        mBgRectF = new RectF(left, top, right, bottom);

        left = mBgRectF.left + mOilBgWidthDP / 2;
        top = mBgRectF.top + mOilBgWidthDP / 2;
        right = mBgRectF.right - mOilBgWidthDP / 2;
        bottom = mBgRectF.bottom - mOilBgWidthDP / 2;
        mScaleRectF = new RectF(left, top, right, bottom);

        Log.d(TAG, "mBgRectF: " + mBgRectF.toShortString());
        Log.d(TAG, "mScaleRectF: " + mScaleRectF.toShortString());
        mJourneyRectF = new RectF();
        mStatusRectF = new RectF();
        mStartExamRectF = new RectF();
        mStopExamRectF = new RectF();
        mStopChargeRectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getDefaultSize(MeasureSpec.getSize(widthMeasureSpec), widthMeasureSpec);
        int height = getDefaultSize(MeasureSpec.getSize(heightMeasureSpec), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        normalPaint.setColor(Color.WHITE);
        canvas.drawRect(mOutLineRectF, normalPaint);

        drawOilBgStroke(canvas);
        drawOilBg(canvas);
        drawOilPercent(canvas);

        drawQoeBgStroke(canvas);
        drawQoeBg(canvas);
        drawQoePercent(canvas);

        drawScaleBg(canvas);
        drawScale(canvas);

        drawJourneyInfo(canvas);

        drawInfo(canvas, false, "65%");
        drawInfo(canvas, true, "35%");
        drawButton(canvas);
    }

    // 油量
    private void drawOilBgStroke(Canvas canvas) {
        canvas.drawArc(mBgRectF, -40, 45 + 40, false, mOilStrokePaint);
    }

    private void drawOilBg(Canvas canvas) {
        canvas.drawArc(mBgRectF, -40, 45 + 40, false, mOilBgPaint);
    }

    private void drawOilPercent(Canvas canvas) {
        canvas.drawArc(mBgRectF, 45, -mOilPercent * 0.85f, false, mOilPercentPaint);
    }

    private void startOilPercentAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOilPercent = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setStartDelay(1000);
        valueAnimator.start();
    }

    // 电量
    private void drawQoeBgStroke(Canvas canvas) {
        mOilBgPaint.setColor(Color.BLACK);
        canvas.drawArc(mBgRectF, -60, -165, false, mOilStrokePaint);
    }

    private void drawQoeBg(Canvas canvas) {
        mOilBgPaint.setColor(Color.parseColor("#FFEAEAEA"));
        canvas.drawArc(mBgRectF, -60, -165, false, mOilBgPaint);
    }

    private void drawQoePercent(Canvas canvas) {
        canvas.drawArc(mBgRectF, -225, mQoePercent * 1.65f, false, mQoePercentPaint);
    }

    private void startQoePercentAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mQoePercent = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setStartDelay(1000);
        valueAnimator.start();
    }

    // 刻度
    private void drawScaleBg(Canvas canvas) {
        canvas.drawOval(mScaleRectF, mScaleBgPaint);
    }

    private void drawScale(Canvas canvas) {
        canvas.save();
        canvas.translate(mScaleRectF.centerX(), mScaleRectF.centerY());
        canvas.rotate(-120, 0, 0);
        int startY = (int) (-mScaleRectF.height() / 2 + mScalePaddingDP);
        float rAngle = DURING_ARC / ((SCALE_COUNT - 1) * 1.0f);
        for (int i = 0; i < SCALE_COUNT; i++) {
            canvas.save();
            canvas.rotate(i * rAngle, 0, 0);
            canvas.drawLine(0, startY, 0, startY + 20, mScalePaint);
            canvas.restore();
        }

        canvas.restore();
    }

    private void drawJourneyInfo(Canvas canvas) {
        mKilometre = 820;
        String textToDraw = mKilometre + "km";
        mJourneyPaint.setTextSize(spToPx(28, getContext()));
        Paint.FontMetrics fontMetrics = mJourneyPaint.getFontMetrics();

        float height = fontMetrics.bottom - fontMetrics.top;
        float width = mJourneyPaint.measureText(textToDraw);

        float left = mScaleRectF.left + mScaleRectF.width() / 2f - width / 2f;
        float top = mScaleRectF.top + mScaleRectF.height() / 2f - height / 2f;
        float right = left + width;
        float baseBottom = top + height;
        mJourneyRectF.set(left, top, right, baseBottom);
        mJourneyPaint.setTextAlign(Paint.Align.CENTER);
        mJourneyPaint.setColor(Color.parseColor("#FF333330"));
        mJourneyPaint.setFakeBoldText(true);

        // draw 820km
//        canvas.drawRect(mJourneyRectF, mJourneyPaint);
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        canvas.drawText(textToDraw, mJourneyRectF.centerX(), mJourneyRectF.centerY() + dy, mJourneyPaint);
        // draw 续航
        textToDraw = "续航";
        mJourneyPaint.setTextSize(spToPx(12, getContext()));
        fontMetrics = mJourneyPaint.getFontMetrics();
        height = fontMetrics.bottom - fontMetrics.top;
        width = mJourneyPaint.measureText(textToDraw);

        left = mScaleRectF.left + mScaleRectF.width() / 2f - width / 2f;
        top = top - dpToPx(4, getContext()) - height;
        right = left + width;
        float bottom = top + height;
        mJourneyRectF.set(left, top, right, bottom);
        mJourneyPaint.setTextAlign(Paint.Align.CENTER);
        mJourneyPaint.setColor(Color.parseColor("#FF333330"));
//        canvas.drawRect(mJourneyRectF, mJourneyPaint);
        dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        canvas.drawText(textToDraw, mJourneyRectF.centerX(), mJourneyRectF.centerY() + dy, mJourneyPaint);

        // draw 充电中
        textToDraw = "充电中";
        width = mJourneyPaint.measureText(textToDraw);
        left = mScaleRectF.left + mScaleRectF.width() / 2f - width / 2f;
        top = baseBottom + dpToPx(6, getContext());
        right = left + width;
        bottom = top + height;
        left -= dpToPx(13, getContext());
        right += dpToPx(13, getContext());
        mStatusRectF.set(left, top, right, bottom);
        mJourneyPaint.setColor(Color.parseColor("#FFF06E23"));
        canvas.drawRoundRect(mStatusRectF, left, top, mJourneyPaint);
        mJourneyPaint.setColor(Color.WHITE);
        dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        canvas.drawText(textToDraw, mStatusRectF.centerX(), mStatusRectF.centerY() + dy, mJourneyPaint);
    }

    private void drawInfo(Canvas canvas, boolean isOil, String textToDraw) {
        float drawableWidth = dpToPx(16, getContext());
        int left, top, right, bottom;
        left = isOil ? (int) (mOutLineRectF.right + dpToPx(4, getContext())) :
                (int) (mOutLineRectF.left - dpToPx(4, getContext()) - drawableWidth);
        top = (int) (mOutLineRectF.height() / 2 - drawableWidth / 2);
        right = (int) (left + drawableWidth);
        bottom = (int) (top + drawableWidth);

        if (isOil) {
            mOilDrawable.setBounds(left, top, right, bottom);
            mOilDrawable.draw(canvas);
        } else {
            mQoeDrawable.setBounds(left, top, right, bottom);
            mQoeDrawable.draw(canvas);
        }

        mJourneyPaint.setTextSize(spToPx(14, getContext()));
        mJourneyPaint.setColor(isOil ? Color.parseColor("#FF33B5E5") : Color.parseColor("#FFF06E23"));
        float textWidth = mJourneyPaint.measureText(textToDraw);
        Paint.FontMetrics fontMetrics = mJourneyPaint.getFontMetrics();
        float textHeight = fontMetrics.bottom - fontMetrics.top;
        left = isOil ? right : (int) (left - textWidth);
        top = (int) (mOutLineRectF.height() / 2 - textHeight / 2);
        mJourneyRectF.set(left, top, left + textWidth, top + textHeight);
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        canvas.drawText(textToDraw, mJourneyRectF.centerX(), mJourneyRectF.centerY() + dy, mJourneyPaint);
    }

    private RectF mStartExamRectF;
    private RectF mStopExamRectF;
    private RectF mStopChargeRectF;

    private void drawButton(Canvas canvas) {
        String textToDraw = "车辆体检";
        float left, top, right, bottom;
        mJourneyPaint.setStyle(Paint.Style.FILL);
        mJourneyPaint.setTextSize(spToPx(12, getContext()));
        mJourneyPaint.setColor(Color.parseColor("#FF666666"));
        Paint.FontMetrics fontMetrics = mJourneyPaint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float textWidth = mJourneyPaint.measureText(textToDraw);
        right = mOutLineRectF.left - dpToPx(5, getContext());
        left = right - dpToPx(26, getContext()) - textWidth;
        top = mOutLineRectF.top + dpToPx(32, getContext());
        bottom = top + (fontMetrics.bottom - fontMetrics.top) + dpToPx(10, getContext());
        mStartExamRectF.set(left, top, right, bottom);
        mJourneyPaint.setColor(Color.WHITE);
        canvas.drawRoundRect(mStartExamRectF, dpToPx(11, getContext()), dpToPx(11, getContext()), mJourneyPaint);
        // draw text
        mJourneyPaint.setColor(Color.parseColor("#FF666666"));
        canvas.drawText(textToDraw, mStartExamRectF.centerX(), mStartExamRectF.centerY() + dy, mJourneyPaint);

        textToDraw = "退出体检";
        textWidth = mJourneyPaint.measureText(textToDraw);
        left = mOutLineRectF.right + dpToPx(6, getContext());
        right = left + textWidth + dpToPx(26, getContext());
        top = mOutLineRectF.top;
        bottom = top + fontMetrics.bottom - fontMetrics.top + dpToPx(10, getContext());
        mStopExamRectF.set(left, top, right, bottom);
        mJourneyPaint.setColor(Color.WHITE);
        canvas.drawRoundRect(mStopExamRectF, dpToPx(11, getContext()), dpToPx(11, getContext()), mJourneyPaint);
        // draw text
        mJourneyPaint.setColor(Color.parseColor("#FF666666"));
        canvas.drawText(textToDraw, mStopExamRectF.centerX(), mStopExamRectF.centerY() + dy, mJourneyPaint);

        textToDraw = "停止充电";
        textWidth = mJourneyPaint.measureText(textToDraw);
        left = mOutLineRectF.right + dpToPx(6, getContext());
        right = left + textWidth + dpToPx(26, getContext());
        top = bottom + dpToPx(10, getContext());
        bottom = top + fontMetrics.bottom - fontMetrics.top + dpToPx(10, getContext());
        mStopChargeRectF.set(left, top, right, bottom);
        mJourneyPaint.setColor(Color.parseColor("#FFFF7828"));
        mJourneyPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(mStopChargeRectF, dpToPx(11, getContext()), dpToPx(11, getContext()), mJourneyPaint);
        // draw text
        mJourneyPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(textToDraw, mStopChargeRectF.centerX(), mStopChargeRectF.centerY() + dy, mJourneyPaint);
    }

    private final GestureDetector mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            if (mStartExamRectF.contains(e.getX(), e.getY())) {
                Log.d(TAG, "mStartExamRectF: 车辆体检");
                return true;
            } else if (mStopExamRectF.contains(e.getX(), e.getY())) {
                Log.d(TAG, "mStopExamRectF: 退出体验");
                return true;
            } else if (mStopChargeRectF.contains(e.getX(), e.getY())) {
                Log.d(TAG, "mStopChargeRectF: 停止充电");
                return true;
            }
            return super.onDown(e);
        }
    });

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        return mGestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    public static int dpToPx(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int spToPx(int sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
