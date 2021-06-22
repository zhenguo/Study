package com.qihoo.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";
    private int mLineWidth;
    private int mLineHeight;
    private final static int HOR_DIVIDER = 10;
    private final static int VER_DIVIDER = 10;
    private List<List<View>> mLayout = new ArrayList<>();
    private List<Integer> mRows = new ArrayList<>();

    private List<List<View>> mChildArray = new ArrayList<>();
    private List<Integer> mRowHeight = new ArrayList<>();

    // new
    public FlowLayout(Context context) {
        super(context);
    }

    // 反射 xml
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 主题style
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 度量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 获取父容器传过来的大小
        int parentWidthsize = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeightsize = MeasureSpec.getSize(heightMeasureSpec);

        // 获取父容器padding
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        // 先度量孩子
        List<View> row = new ArrayList<>();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = child.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, layoutParams.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, layoutParams.width);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            int childMeasuredWidth = child.getMeasuredWidth();
            int childMeasuredHeight = child.getMeasuredHeight();

            mLineWidth += childMeasuredWidth;
            Log.d(TAG, "mLineWidth: " + mLineWidth);
            if (mLineWidth >= parentHeightsize) {
                // 应该换行了
                mLineHeight += childMeasuredHeight;
                mLineWidth = 0;

                mLayout.add(row);
                mRows.add(mLineHeight);
                Log.d(TAG, "mLineHeight: " + mLineHeight);

            } else {
                row.add(child);
            }

            // 处理最后一行
            if (i == getChildCount() - 1) {
                mLayout.add(row);
            }
        }

        // 再度量自己
        int realWidth = Math.max(parentWidthsize, mLineWidth);
        int realHeight = Math.max(parentHeightsize, mLineHeight);

        Log.d(TAG, "realWidth: " + realWidth + " realHeight: " + realHeight);
        setMeasuredDimension(realWidth, realHeight);
    }

    // 布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // layout children
        mLineWidth = 0;
        int indexOnRow = 0;
        int row = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            int top;
            if (mLineWidth > getMeasuredWidth()) {
                indexOnRow = 0;
                row++;
                top = getPaddingTop() + row * child.getMeasuredHeight() + VER_DIVIDER;
                mLineWidth = 0;
                Log.d(TAG, "reset lineWidth: " + mLineWidth);
            } else {
                top = row > 0 ? getPaddingTop() + row * child.getMeasuredHeight() : getPaddingTop();
            }
            int left = getPaddingLeft() + indexOnRow * child.getMeasuredWidth() + HOR_DIVIDER;
            int right = child.getMeasuredWidth() * (indexOnRow + 1);
            int bottom = child.getMeasuredHeight() * (indexOnRow + 1);

            child.layout(left, top, right, bottom);

            mLineWidth += child.getRight();
            indexOnRow++;
            Log.d(TAG, "l： " + left + " t: " + top + " r: " + right + " b: " + bottom + " lineWidth: " + mLineWidth);
        }
    }
}
