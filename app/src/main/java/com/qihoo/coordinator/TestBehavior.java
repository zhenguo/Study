package com.qihoo.coordinator;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.quxiangtech.myapplication.R;

public class TestBehavior extends CoordinatorLayout.Behavior<NestedScrollView> {
    private static final String TAG = "TestBehavior";

    public TestBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull NestedScrollView child, int layoutDirection) {
        Log.d(TAG, "onLayoutChild: ");
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT) {
            layoutParams.height = parent.getHeight();
            child.setLayoutParams(layoutParams);
            return true;
        }

        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull NestedScrollView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.d(TAG, "onStartNestedScroll: " + directTargetChild.getClass().getSimpleName());
        return true;
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull NestedScrollView child, @NonNull View dependency) {
        Log.d(TAG, "layoutDependsOn: ");
        return dependency.getId() == R.id.vpn_area;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull NestedScrollView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

        Log.d(TAG, "onNestedPreScroll: dy: " + dy + " " + child.getTranslationY() + " " + child.getTop());
        if ((child.getTranslationY() < 0 || (child.getTranslationY() == 0 && dy > 0)) && child.canScrollVertically(-1)) {
            if (child.getTranslationY() > -1200) { // 此处判断吸顶
                child.setTranslationY(child.getTranslationY() - dy);
                consumed[1] = dy;
            }
        }
        if (child.getTranslationY() > 0) {
            child.setTranslationY(0);
            consumed[1] = dy;
        }
    }
}
