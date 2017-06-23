package com.jinfukeji.taqu.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.jinfukeji.taqu.R;

/**
 * Created by "于志渊"
 * 时间:"15:52"
 * 包名:com.jinfukeji.taqu.utils
 * 描述:自定义viewgroup来实现流布局
 */

public class FlowLayout extends ViewGroup{
    private static final int DEFAULT_HORIZONTAL_SPACING = 5;
    private static final int DEFAULT_VERTICAL_SPACING = 5;

    private int mVerticalSpacing;
    private int mHorizontalSpacing;
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.FlowLayout);
        try {
            mHorizontalSpacing=a.getDimensionPixelSize(R.styleable.FlowLayout_horizontal_spacing,DEFAULT_HORIZONTAL_SPACING);
            mVerticalSpacing=a.getDimensionPixelSize(R.styleable.FlowLayout_vertical_spacing,DEFAULT_VERTICAL_SPACING);
        } finally {
            a.recycle();
        }
    }

    public void setmVerticalSpacing(int mVerticalSpacing) {
        this.mVerticalSpacing = mVerticalSpacing;
    }

    public void setmHorizontalSpacing(int mHorizontalSpacing) {
        this.mHorizontalSpacing = mHorizontalSpacing;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int myWidth = resolveSize(0, widthMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int childLeft = paddingLeft;
        int childTop = paddingTop;

        int lineHeight = 0;
        int lineWidth = 0;

        int height = 0;
        int width = 0;

        // Measure each child and put the child to the right of previous child
        // if there's enough room for it, otherwise, wrap the line and put the child to next line.
        for (int i = 0, childCount = getChildCount(); i < childCount; ++i) {
            View childView = getChildAt(i);
            LayoutParams childLayoutParams = childView.getLayoutParams();
            childView.measure(
                    getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childLayoutParams.width),
                    getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, childLayoutParams.height));
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > myWidth) {
                childLeft = paddingLeft;
                lineWidth = childLeft + childWidth + paddingRight;
                childTop += mVerticalSpacing + lineHeight;
                height += lineHeight;
                lineHeight = childHeight;
            }else {
                childLeft += childWidth + mHorizontalSpacing;
                lineWidth += childLeft + childWidth + paddingRight;
                lineHeight = Math.max(lineHeight, childHeight + paddingTop + paddingBottom);
            }
            if (i == getChildCount() - 1) {
                myWidth = Math.max(myWidth, lineWidth);
                height += lineHeight;
            }
        }
        int wantedHeight = childTop + lineHeight + paddingBottom;
        setMeasuredDimension(myWidth, resolveSize(wantedHeight, heightMeasureSpec));
        setMeasuredDimension((MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) ? MeasureSpec.getSize(widthMeasureSpec)
                : width, (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) ? MeasureSpec.getMode(heightMeasureSpec)
                : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int myWidth = r - l;

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();

        int childLeft = paddingLeft;
        int childTop = paddingTop;

        int lineHeight = 0;

        for (int i = 0, childCount = getChildCount(); i < childCount; ++i) {
            View childView = getChildAt(i);

            if (childView.getVisibility() == View.GONE) {
                continue;
            }

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            lineHeight = Math.max(childHeight, lineHeight);

            if (childLeft + childWidth + paddingRight > myWidth) {
                childLeft = paddingLeft;
                childTop += mVerticalSpacing + lineHeight;
                lineHeight = childHeight;
            }

            childView.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            childLeft += childWidth + mHorizontalSpacing;
        }
    }
}
