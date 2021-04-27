package com.quxiangtech.customViews.dashboardview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.quxiangtech.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class InterestsView extends View {
    private static final String TAG = "InterestsView";
    private Drawable mRectDrawable;
    private Drawable mOpenDrawable;
    private TextPaint mTextPaint;
    private Paint mNormalPaint;
    private Paint mCirclePaint;
    private RectF mTextRectF;
    private RectF mTitleRectF;
    private RectF mOutLineRectF;
    private RectF mTRectF;
    private Path mMainPath;
    private List<Drawable> mInterestsArray;
    private boolean mOpenDetail;
    private RectF mOpenDetailRectF;
    private RectF mCloseDetailRectF;
    private InterestsBean mData;

    String json = "{\n" +
            "    \"success\": true,\n" +
            "    \"code\": 20000,\n" +
            "    \"message\": \"获取数据成功\",\n" +
            "    \"value\": {\n" +
            "        \"rightPackage\": {\n" +
            "            \"id\": 1,\n" +
            "            \"packageCode\": \"formal\",\n" +
            "            \"packageName\": \"用户权益\",\n" +
            "            \"createTime\": null,\n" +
            "            \"updateTime\": null,\n" +
            "            \"createUser\": null,\n" +
            "            \"updateUser\": null\n" +
            "        },\n" +
            "        \"list\": [\n" +
            "            {\n" +
            "                \"id\": 1,\n" +
            "                \"rightTypeCode\": \"CCZB\",\n" +
            "                \"rightTypeName\": \"超长质保\",\n" +
            "                \"rightTypeImage\": \"http://seres-dev-public.oss-cn-shanghai.aliyuncs.com/mall/images/rights/1ad047058c0f4b5e89d7d3120f500794.png\",\n" +
            "                \"rights\": [\n" +
            "                    {\n" +
            "                        \"id\": 1,\n" +
            "                        \"rightCode\": null,\n" +
            "                        \"rightName\": \"4年不限公里整车质保\",\n" +
            "                        \"rightDesc\": \"4年不限公里整车质保\",\n" +
            "                        \"rightDescExd\": null,\n" +
            "                        \"rightImage\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"id\": 2,\n" +
            "                        \"rightCode\": null,\n" +
            "                        \"rightName\": \"三电系统8年/16万公里质保\",\n" +
            "                        \"rightDesc\": \"三电系统8年/16万公里质保\",\n" +
            "                        \"rightDescExd\": null,\n" +
            "                        \"rightImage\": null\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 2,\n" +
            "                \"rightTypeCode\": \"ZSCT\",\n" +
            "                \"rightTypeName\": \"专属充电\",\n" +
            "                \"rightTypeImage\": \"http://seres-dev-public.oss-cn-shanghai.aliyuncs.com/mall/images/rights/8c5ec520fde8403091cae9ab23c31f1f.png\",\n" +
            "                \"rights\": [\n" +
            "                    {\n" +
            "                        \"id\": 3,\n" +
            "                        \"rightCode\": null,\n" +
            "                        \"rightName\": \"专属设计充电桩\",\n" +
            "                        \"rightDesc\": \"专属设计充电桩\",\n" +
            "                        \"rightDescExd\": null,\n" +
            "                        \"rightImage\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"id\": 4,\n" +
            "                        \"rightCode\": null,\n" +
            "                        \"rightName\": \"免费安装服务（30米内免费基础安装服务）\",\n" +
            "                        \"rightDesc\": \"免费安装服务\",\n" +
            "                        \"rightDescExd\": \"\",\n" +
            "                        \"rightImage\": null\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 3,\n" +
            "                \"rightTypeCode\": \"BJFW\",\n" +
            "                \"rightTypeName\": \"便捷服务\",\n" +
            "                \"rightTypeImage\": \"http://seres-dev-public.oss-cn-shanghai.aliyuncs.com/mall/images/rights/9a7f437b35e144ce937595ca13389d34.png\",\n" +
            "                \"rights\": [\n" +
            "                    {\n" +
            "                        \"id\": 5,\n" +
            "                        \"rightCode\": null,\n" +
            "                        \"rightName\": \"免费首保（新车首次保养免费）\",\n" +
            "                        \"rightDesc\": \"免费首保\",\n" +
            "                        \"rightDescExd\": \"\",\n" +
            "                        \"rightImage\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"id\": 6,\n" +
            "                        \"rightCode\": null,\n" +
            "                        \"rightName\": \"全方位免费道路救援（首任车主享有不限次数道路救援）\",\n" +
            "                        \"rightDesc\": \"全方位免费道路救援\",\n" +
            "                        \"rightDescExd\": \"\",\n" +
            "                        \"rightImage\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"id\": 7,\n" +
            "                        \"rightCode\": null,\n" +
            "                        \"rightName\": \"免费代步车服务\",\n" +
            "                        \"rightDesc\": \"免费代步车服务\",\n" +
            "                        \"rightDescExd\": null,\n" +
            "                        \"rightImage\": null\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4,\n" +
            "                \"rightTypeCode\": \"ZNKJ\",\n" +
            "                \"rightTypeName\": \"智能科技\",\n" +
            "                \"rightTypeImage\": \"http://seres-dev-public.oss-cn-shanghai.aliyuncs.com/mall/images/rights/2f52393468ef4ce782edd353ab29ed98.png\",\n" +
            "                \"rights\": [\n" +
            "                    {\n" +
            "                        \"id\": 8,\n" +
            "                        \"rightCode\": null,\n" +
            "                        \"rightName\": \"整车OTA空中升级\",\n" +
            "                        \"rightDesc\": \"整车OTA空中升级\",\n" +
            "                        \"rightDescExd\": null,\n" +
            "                        \"rightImage\": null\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"id\": 9,\n" +
            "                        \"rightCode\": null,\n" +
            "                        \"rightName\": \"免费车联网流量\",\n" +
            "                        \"rightDesc\": \"免费车联网流量\",\n" +
            "                        \"rightDescExd\": null,\n" +
            "                        \"rightImage\": null\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 5,\n" +
            "                \"rightTypeCode\": \"CZQY\",\n" +
            "                \"rightTypeName\": \"车主权益\",\n" +
            "                \"rightTypeImage\": null,\n" +
            "                \"rights\": [\n" +
            "                    {\n" +
            "                        \"id\": 10,\n" +
            "                        \"rightCode\": null,\n" +
            "                        \"rightName\": \"优先锁定权益 优先享受交付\",\n" +
            "                        \"rightDesc\": \"优先锁定权益 优先享受交付\",\n" +
            "                        \"rightDescExd\": null,\n" +
            "                        \"rightImage\": null\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";
    public InterestsView(Context context) {
        super(context);
    }

    public InterestsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInternal();
    }

    public InterestsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initInternal() {
        setWillNotDraw(false);

        mInterestsArray = new ArrayList<>();
        mRectDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_rect);
        mOpenDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_zhankai);

        mNormalPaint = new Paint();
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setDither(true);
        mNormalPaint.setStyle(Paint.Style.STROKE);
        mNormalPaint.setColor(Color.RED);
        mNormalPaint.setStrokeWidth(dpToPx(1, getContext()));
        mNormalPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);
        mCirclePaint.setColor(Color.parseColor("#CDCDCD"));
        mCirclePaint.setStyle(Paint.Style.FILL);
        mOpenDetail = false; // 默认不展示详情
    }

    public void setData(InterestsBean data) {
        mData = data;

        for (int i = mData.value.list.size() - 1; i >= 0; --i) {
            if (TextUtils.isEmpty(mData.value.list.get(i).rightTypeImage)) {
                mData.value.list.remove(i);
            }
        }

        for (int i = 0; i < mData.value.list.size(); i++) {
            int finalI = i;
            Log.d(TAG, "setData: " + mData.value.list.get(i).rightTypeImage);
            Glide.with(this).load(mData.value.list.get(i).rightTypeImage).into(new CustomTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    mInterestsArray.add(resource);
                    Log.d(TAG, "onResourceReady: " + finalI);
                    if (finalI == mData.value.list.size() - 1) {
                        Log.d(TAG, "invalidate: ");
                        invalidate();
                    }
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                    Log.d(TAG, "onLoadCleared: ");
                }
            });
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mTextRectF == null) {
            mTextRectF = new RectF();
            mOutLineRectF = new RectF();
            mMainPath = new Path();
            mTRectF = new RectF();
            mTitleRectF = new RectF();
            mOpenDetailRectF = new RectF();
            mCloseDetailRectF = new RectF();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // handle padding
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec) - dpToPx(20, getContext()) * 2;
        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        setBackgroundColor(Color.BLACK);

        if (mData != null && mInterestsArray.size() > 0) {
            drawMain(canvas);
        }
    }

    private void drawMain(Canvas canvas) {
        String textToDraw = mData.value.rightPackage.packageName;
        String textToDraw2 = null;
        mTextPaint.setFakeBoldText(true);
        mTextPaint.setTextSize(spToPx(14, getContext()));
        mTextPaint.setColor(Color.parseColor("#444444"));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        float textWidth = mTextPaint.measureText(textToDraw);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float left = getWidth() / 2f - textWidth / 2f;
        float top = 0;
        float right = getWidth() / 2f + textWidth / 2f;
        float bottom = top + (fontMetrics.bottom - fontMetrics.top);
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        mTextRectF.set(left, top, right, bottom);

        // 画外框
        mNormalPaint.setColor(Color.parseColor("#FF8929"));
        mOutLineRectF.set(0 + 2, 0 + mTextRectF.height() / 2, getWidth() - 2, dpToPx(118, getContext()) - 2); // 2需要确认
        canvas.drawRoundRect(mOutLineRectF, dpToPx(5, getContext()), dpToPx(5, getContext()), mNormalPaint);

        int dLeft = (int) (mTextRectF.left - dpToPx(3, getContext()) - mRectDrawable.getIntrinsicWidth());
        int dTop = (int) (top + mTextRectF.height() / 2 - mRectDrawable.getIntrinsicHeight() / 2);
        int dRight = (int) (mTextRectF.left - dpToPx(3, getContext()));
        int dBottom = (int) (dTop + mRectDrawable.getIntrinsicHeight());

        int d1Left = (int) (mTextRectF.right + dpToPx(3, getContext()));
        int d1Top = (int) (top + mTextRectF.height() / 2 - mRectDrawable.getIntrinsicHeight() / 2);
        int d1Right = (int) (d1Left + mRectDrawable.getIntrinsicWidth());
        int d1Bottom = (int) (dTop + mRectDrawable.getIntrinsicHeight());

        // 画底框，屏蔽Rect
        float tLeft = dLeft - dpToPx(5, getContext());
        float tTop = 0;
        float tRight = d1Right + dpToPx(5, getContext());
        float tBottom = (tTop + mTextRectF.bottom);
        mNormalPaint.setStyle(Paint.Style.FILL);
        mNormalPaint.setColor(Color.WHITE);
        mTRectF.set(tLeft, tTop, tRight, tBottom);
        canvas.drawRect(mTRectF, mNormalPaint);

        // 文字
        mNormalPaint.setStyle(Paint.Style.STROKE);
        mNormalPaint.setColor(Color.parseColor("#FF8929"));
//        canvas.drawRect(mTextRectF, mNormalPaint);
        canvas.drawText(textToDraw, mTextRectF.centerX(), top + mTextRectF.centerY() + dy, mTextPaint);
        // 星星
        mRectDrawable.setBounds(dLeft, dTop, dRight, dBottom);
        mRectDrawable.draw(canvas);
        // 星星
        mRectDrawable.setBounds(d1Left, d1Top, d1Right, d1Bottom);
        mRectDrawable.draw(canvas);

        for (int i = 0; i < mInterestsArray.size(); i++) {
            mTextPaint.setColor(Color.parseColor("#888888"));
            textToDraw = mData.value.list.get(i).rightTypeName;
            textWidth = mTextPaint.measureText(textToDraw);
            fontMetrics = mTextPaint.getFontMetrics();

            float drawableTop = dpToPx(118, getContext()) - (fontMetrics.bottom - fontMetrics.top) -
                    mInterestsArray.get(0).getIntrinsicHeight() - dpToPx(5, getContext());
            drawableTop /= 2;
            float drawableGap = (getWidth() - mInterestsArray.get(0).getIntrinsicWidth() * 4) / 5f;
            float drawableBottom = drawableTop + mInterestsArray.get(0).getIntrinsicHeight();
            float drawableRight;

            float textGap = (getWidth() - textWidth * 4) / 5;
            top = drawableBottom + dpToPx(5, getContext());
            bottom = top + (fontMetrics.bottom - fontMetrics.top);

            Drawable drawable = mInterestsArray.get(i);
            int l = (int) (drawableGap * (i + 1) + drawable.getIntrinsicWidth() * i);
            drawableRight = l + drawable.getIntrinsicWidth();
            drawable.setBounds(l, (int) drawableTop, (int) (drawableRight), (int) drawableBottom);
            drawable.draw(canvas);

            l = (int) (textGap * (i + 1) + textWidth * i);
            right = l + textWidth;
            mTextRectF.set(l, top, right, bottom);
            dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
            canvas.drawText(textToDraw, mTextRectF.centerX(), mTextRectF.centerY() + dy, mTextPaint);
        }

        if (!mOpenDetail) {
            textToDraw = "展开查看权益明细";
            mTextPaint.setTextSize(spToPx(12, getContext()));
            mTextPaint.setColor(Color.parseColor("#888888"));
            textWidth = mTextPaint.measureText(textToDraw);
            fontMetrics = mTextPaint.getFontMetrics();
            float drawableWidth = mOpenDrawable.getIntrinsicWidth();
            left = (getWidth() - textWidth - drawableWidth - dpToPx(3, getContext())) / 2;
            right = left + textWidth;
            top = mOutLineRectF.bottom + dpToPx(16, getContext());
            bottom = top + (fontMetrics.bottom - fontMetrics.top);
            mTextRectF.set(left, top, right, bottom);
            dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
            canvas.drawText(textToDraw, mTextRectF.centerX(), mTextRectF.centerY() + dy, mTextPaint);

            d1Left = (int) (mTextRectF.right + dpToPx(3, getContext()));
            d1Top = (int) (mOutLineRectF.bottom + (mTextRectF.height() / 2 - (fontMetrics.bottom - fontMetrics.top) / 2) + dpToPx(16, getContext()));
            d1Right = d1Left + mOpenDrawable.getIntrinsicWidth();
            d1Bottom = d1Top + mOpenDrawable.getIntrinsicHeight();
            mOpenDrawable.setBounds(d1Left, d1Top, d1Right, d1Bottom);
            mOpenDrawable.draw(canvas);

            mOpenDetailRectF.set(mTextRectF.left, mTextRectF.top, d1Right, mTextRectF.bottom); // 设定点击区域
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.height = (int) (mTextRectF.bottom + dpToPx(28, getContext()));
            setLayoutParams(layoutParams);
        }

        float maxBottom = 0;

        if (mOpenDetail) {
            // 详情
            float maxWidth = getWidth() / 2f;
            float maxHeight;
            float circleRadius = dpToPx(2, getContext());
            float nextRowBottom = mOutLineRectF.bottom;
            float rowHeadWidth = 0;
            float column2Left = 0;

            for (int i = 0; i < mInterestsArray.size(); i++) {
                // 标题
                textToDraw = mData.value.list.get(i).rightTypeName;
                mTextPaint.setTextSize(spToPx(14, getContext()));
                mTextPaint.setFakeBoldText(true);
                if (i % 2 == 0) { // 行首
                    rowHeadWidth = mTextPaint.measureText(textToDraw);
                    column2Left = dpToPx(131, getContext()) + rowHeadWidth;
                }
                left = i % 2 != 0 ? column2Left : 0;
                top = nextRowBottom + dpToPx(14, getContext());
                right = left + mTextPaint.measureText(textToDraw);
                fontMetrics = mTextPaint.getFontMetrics();
                bottom = top + (fontMetrics.bottom - fontMetrics.top);
                mTitleRectF.set(left, top, right, bottom);
                dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
                canvas.drawText(textToDraw, mTitleRectF.centerX(), mTitleRectF.centerY() + dy, mTextPaint);

                // N条介绍
                // mData.value.list.get(i).rights.size()
                textToDraw2 = null;

                float cy = -1;
                for (int j = 0; j < mData.value.list.get(i).rights.size(); j++) {
                    // 介绍
                    float cx = mTitleRectF.left + circleRadius;
                    if (cy == -1) {
                        cy += mTitleRectF.bottom + dpToPx(12, getContext()) + circleRadius; // 初始化cy
                    }
                    cy += j > 0 ? dpToPx(18, getContext()) : 0; // 增加实心圆之间的距离

                    if (!TextUtils.isEmpty(textToDraw2)) {
                        cy += (fontMetrics.bottom - fontMetrics.top);
                        textToDraw2 = null;
                    }
                    canvas.drawCircle(cx, cy, circleRadius, mCirclePaint);

                    textToDraw = mData.value.list.get(i).rights.get(j).rightName;

                    textToDraw = textToDraw.replaceAll("（", "(");
                    textToDraw = textToDraw.replaceAll("）", ")");
                    if (textToDraw.contains("(")) {
                        textToDraw2 = textToDraw.substring(textToDraw.indexOf('('), textToDraw.indexOf(')') + 1);
                        textToDraw = textToDraw.substring(0, textToDraw.indexOf('('));
                    } else if (textToDraw.contains("（")) {
                        textToDraw2 = textToDraw.substring(textToDraw.indexOf('（'), textToDraw.indexOf('）') + 1);
                        textToDraw = textToDraw.substring(0, textToDraw.indexOf('（'));
                    }
                    mTextPaint.setTextSize(spToPx(12, getContext()));
                    mTextPaint.setFakeBoldText(false);
                    fontMetrics = mTextPaint.getFontMetrics();
                    textWidth = mTextPaint.measureText(textToDraw);
                    left = cx + circleRadius + dpToPx(3, getContext());
                    right = left + textWidth;
                    top = cy - (fontMetrics.bottom - fontMetrics.top) / 2;
                    bottom = top + (fontMetrics.bottom - fontMetrics.top);
                    dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
                    mTextRectF.set(left, top, right, bottom);
                    canvas.drawText(textToDraw, mTextRectF.centerX(), mTextRectF.centerY() + dy, mTextPaint);

                    if (!TextUtils.isEmpty(textToDraw2)) {
                        // 换行
                        left = cx + circleRadius;
                        textWidth = mTextPaint.measureText(textToDraw2);
                        fontMetrics = mTextPaint.getFontMetrics();
                        right = left + textWidth;
                        top = mTextRectF.bottom;
                        bottom = top + (fontMetrics.bottom - fontMetrics.top);
                        dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
                        mTextRectF.set(left, top, right, bottom);

                        float singleTextWidth = textWidth / textToDraw2.length();
                        int count = 0;
                        float moreWidth = right - getWidth();

                        if (moreWidth <= 0) {
                            canvas.drawText(textToDraw2.substring(0, textToDraw2.length() - count), mTextRectF.centerX(), mTextRectF.centerY() + dy, mTextPaint);
                        } else {
                            // 需要换行的情况，未测试超过2行的情况
                            while (moreWidth > 0) {
                                count = (int) ((right - getWidth()) / singleTextWidth + 2); // 多出来几个字
                                Log.d(TAG, "drawMain: " + count);
                                // 修正RectF
                                right = getWidth();
                                moreWidth = right - getWidth();
                                mTextRectF.set(left, top, right, bottom);
                                mTextPaint.setTextAlign(Paint.Align.LEFT);
                                canvas.drawText(textToDraw2.substring(0, textToDraw2.length() - count), mTextRectF.left, mTextRectF.centerY() + dy, mTextPaint);
                                top = mTextRectF.bottom;
                                bottom = top + (fontMetrics.bottom - fontMetrics.top);
                                mTextRectF.set(left, top, right, bottom);
                                canvas.drawText(textToDraw2.substring(textToDraw2.length() - count), mTextRectF.left, mTextRectF.centerY() + dy, mTextPaint);
                                mTextPaint.setTextAlign(Paint.Align.CENTER);
                            }
                        }
                    }
                    if (i % 2 != 0) {
                        nextRowBottom = mTextRectF.bottom;
                    }

                    if (maxBottom < mTextRectF.bottom) {
                        maxBottom = mTextRectF.bottom;
                    }
                }
            }

            textToDraw = "- 优先锁定权益 优先享受交付 -";
            mTextPaint.setTextSize(spToPx(12, getContext()));
            mTextPaint.setFakeBoldText(true);
            mTextPaint.setColor(Color.parseColor("#444444"));
            fontMetrics = mTextPaint.getFontMetrics();
            textWidth = mTextPaint.measureText(textToDraw);
            left = getWidth() / 2f - textWidth / 2f;
            top = maxBottom + dpToPx(19, getContext());
            right = left + textWidth;
            bottom = top + (fontMetrics.bottom - fontMetrics.top);
            dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
            mTextRectF.set(left, top, right, bottom);
            // draw sl
            canvas.drawText(textToDraw, mTextRectF.centerX(), mTextRectF.centerY(), mTextPaint);
            maxBottom = bottom;
        }

        if (mOpenDetail) {
            textToDraw = "收起权益明细";
            mTextPaint.setTextSize(spToPx(12, getContext()));
            mTextPaint.setColor(Color.parseColor("#888888"));
            textWidth = mTextPaint.measureText(textToDraw);
            fontMetrics = mTextPaint.getFontMetrics();
            float drawableWidth = mOpenDrawable.getIntrinsicWidth();
            left = (getWidth() - textWidth - drawableWidth - dpToPx(3, getContext())) / 2;
            right = left + textWidth;
            top = maxBottom + dpToPx(16, getContext());
            bottom = top + (fontMetrics.bottom - fontMetrics.top);
            mTextRectF.set(left, top, right, bottom);
            dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
            canvas.drawText(textToDraw, mTextRectF.centerX(), mTextRectF.centerY() + dy, mTextPaint);

            d1Left = (int) (mTextRectF.right + dpToPx(3, getContext()));
            d1Top = (int) (mTextRectF.top + (mTextRectF.height() / 2 - (fontMetrics.bottom - fontMetrics.top) / 2));
            d1Right = d1Left + mOpenDrawable.getIntrinsicWidth();
            d1Bottom = d1Top + mOpenDrawable.getIntrinsicHeight();
            mOpenDrawable.setBounds(d1Left, d1Top, d1Right, d1Bottom);
            mOpenDrawable.draw(canvas);

            mCloseDetailRectF.set(mTextRectF.left, mTextRectF.top, d1Right, mTextRectF.bottom); // 设定点击区域
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = (int) mTextRectF.bottom + dpToPx(28, getContext());
            setLayoutParams(params);
        }
    }

    private final GestureDetector mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            if (mOpenDetailRectF.contains(e.getX(), e.getY())) {
                // 展开详情
                mOpenDetail = true;
                invalidate();
                return true;
            } else if (mCloseDetailRectF.contains(e.getX(), e.getY())) {
                // 关闭详情
                mOpenDetail = false;
                invalidate();
                return true;
            }
            return super.onDown(e);
        }
    });

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    public static int dpToPx(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int spToPx(int sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
