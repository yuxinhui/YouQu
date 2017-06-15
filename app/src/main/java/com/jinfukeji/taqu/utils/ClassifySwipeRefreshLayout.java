package com.jinfukeji.taqu.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by "于志渊"
 * 时间:"16:44"
 * 包名:com.jinfukeji.taqu.utils
 * 描述:重写刷新控件
 */

public class ClassifySwipeRefreshLayout extends SwipeRefreshLayout{
    private int mTouchSlop;
    // 上一次触摸时的X坐标
    private float mPrevX;

    public ClassifySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 触发移动事件的最短距离，如果小于这个距离就不触发移动控件
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPrevX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                final float eventX = event.getX();
                float xDiff = Math.abs(eventX - mPrevX);
                // 增加60的容差，让下拉刷新在竖直滑动时就可以触发
                if (xDiff > mTouchSlop + 60) {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(event);
    }
}
