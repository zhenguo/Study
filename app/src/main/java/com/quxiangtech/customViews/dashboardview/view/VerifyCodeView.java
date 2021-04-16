package com.quxiangtech.customViews.dashboardview.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.quxiangtech.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class VerifyCodeView extends FrameLayout {
    private static final String TAG = "VerifyCodeView";
    private static final int CODE_NUM = 6;
    private List<TextView> mTextViewList = new ArrayList<>();
    private EditText mEditText;
    private int mPrevCount;

    private ViewTreeObserver.OnDrawListener mOnDrawListener = new ViewTreeObserver.OnDrawListener() {
        @Override
        public void onDraw() {
            int[] location = new int[2];
            mEditText.getLocationInWindow(location);
            float x = location[0];
            float y = location[1];
            long downTime = System.currentTimeMillis();
            MotionEvent downEvent = MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, x, y, 0);
            MotionEvent upEvent = MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_UP, x, y, 0);
            mEditText.onTouchEvent(downEvent);
            mEditText.onTouchEvent(upEvent);
            downEvent.recycle();
            upEvent.recycle();
        }
    };

    public VerifyCodeView(Context context) {
        super(context);
    }

    public VerifyCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInternal();
    }

    public VerifyCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static int dpToPx(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int spToPx(int sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    private void initInternal() {
        setWillNotDraw(false);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams textViewParam = new LinearLayout.LayoutParams(dpToPx(44, getContext()),
                dpToPx(44, getContext()));
        // 添加6个TextView
        for (int i = 0; i < CODE_NUM; i++) {

            TextView textView = new TextView(getContext());
            if (i == 0) {
                textView.setBackgroundResource(R.drawable.bg_verify_code_with_left_corner);
            } else if (i == CODE_NUM - 1) {
                textView.setBackgroundResource(R.drawable.bg_verify_code_with_right_corner);
            } else {
                textView.setBackgroundResource(R.drawable.bg_verify_code);
            }

            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(20);
            textView.getPaint().setFakeBoldText(true);
            textView.setLayoutParams(textViewParam);
            textView.setInputType(InputType.TYPE_CLASS_NUMBER);
            textView.setTextColor(Color.BLACK);
            mTextViewList.add(textView);
            linearLayout.addView(textView);
        }

        addView(linearLayout);

        mEditText = new EditText(getContext());
        mEditText.setFocusable(true);
        mEditText.setFocusableInTouchMode(true);
        mEditText.setBackgroundColor(Color.TRANSPARENT);
        mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(CODE_NUM)});
        mEditText.setTextSize(0);
        mEditText.setCursorVisible(false);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: ");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged: " + s + " " + s.length() + " " + mPrevCount);

                // 过滤
                if (!TextUtils.isDigitsOnly(s)) {
                    return;
                }

                if (s.length() < mPrevCount) {
                    // 删除动作
                    mTextViewList.get(mPrevCount - 1).setText("");
                    mPrevCount--;
                } else if (s.length() == 6) {
                    // 粘贴
                    for (int i = 0; i < s.length(); i++) {
                        Log.d(TAG, "afterTextChanged: " + i);
                        mTextViewList.get(i).setText(String.valueOf(s.charAt(i)));
                    }
                    mPrevCount = s.length();
                } else {
                    // 输入
                    mPrevCount++;
                    mTextViewList.get(mEditText.getText().length() - 1).setText(s.subSequence(s.length() - 1, s.length()));
                }

                if (s.length() == CODE_NUM) {
                    // 输入完成
                    if (mOnInputCompleteListener != null) {
                        mOnInputCompleteListener.onInput(s.toString());
                    }
                }
            }
        });
        addView(mEditText);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mEditText.setLayoutParams(new LayoutParams(w, h));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnDrawListener(mOnDrawListener);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        getViewTreeObserver().removeOnDrawListener(mOnDrawListener);
        mOnDrawListener = null;
        mTextViewList.clear();
        mTextViewList = null;
        removeAllViews();
    }

    public void setInputCompleteListener(OnInputCompleteListener listener) {
        mOnInputCompleteListener = listener;
    }

    private OnInputCompleteListener mOnInputCompleteListener;

    public interface OnInputCompleteListener {
        void onInput(String s);
    }
}
