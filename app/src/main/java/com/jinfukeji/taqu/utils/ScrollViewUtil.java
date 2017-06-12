package com.jinfukeji.taqu.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by "于志渊"
 * 时间:"14:00"
 * 包名:com.jinfukeji.taqu.utils
 * 描述:重写竖滑view
 */

public class ScrollViewUtil extends ScrollView{
    //渐变view
    private View fadingView;
    //滑动view的高度，如果这里fadingHeightView是一张图片，
    // 那么就是这张图片上滑至完全消失时action bar 完全显示，
    // 过程中透明度不断增加，直至完全显示
    private View fadingHeightView;
    //滑动距离，默认设置滑动500 时完全显示，根据实际需求自己设置
    private int fadingHeight = 200;

    public void setFadingView(View fadingView) {
        this.fadingView = fadingView;
    }

    public void setFadingHeightView(View fadingHeightView) {
        this.fadingHeightView = fadingHeightView;
    }

    public ScrollViewUtil(Context context) {
        super(context);
    }

    public ScrollViewUtil(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewUtil(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(fadingHeightView != null)
            fadingHeight = fadingHeightView.getMeasuredHeight();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float fading = t>fadingHeight ? fadingHeight : (t > 30 ? t : 0);
        updateActionBarAlpha( fading / fadingHeight);
    }

    void updateActionBarAlpha(float alpha){
        try {
            setActionBarAlpha(alpha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setActionBarAlpha(float alpha) throws Exception{
        if(fadingView==null){
            throw new Exception("fadingView is null...");
        }
        fadingView.setAlpha(alpha);
    }
}
