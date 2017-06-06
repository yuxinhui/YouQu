package com.jinfukeji.taqu.utils;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.jinfukeji.taqu.R;

/**
 * Created by "于志渊"
 * 时间:"14:02"
 * 包名:com.jinfukeji.taqu.utils
 * 描述:轮播图动态加点与根据当前page来显示不同的文字和点
 */

public class GuideUtil {

    //动态加点
    public static void initDot(int[] ints,Context context,LinearLayout linearLayout){
        for (int i=0;i<ints.length;i++){
            View view=new View(context);
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(15,15);
            lp.leftMargin=(i==0?0:20);//给除了第一个点之外的点都加上marginLeft
            view.setLayoutParams(lp);
            view.setBackgroundResource(R.drawable.banner_select_dot);
            linearLayout.addView(view);
        }
    }

    //根据当前page来显示不同的文字和点
    public static void updateDescAndDot(ViewPager viewPager,int[] ints,LinearLayout linearLayout){
        int currentPage=viewPager.getCurrentItem()%ints.length;
        //更新点
        //遍历所有的点，当点的位置和currentPage相当的时候，则设置为可用，否则是禁用
        for (int i=0;i<linearLayout.getChildCount();i++){
            linearLayout.getChildAt(i).setEnabled(i==currentPage);
        }
    }

    public static void setOnTouchListener(ViewPager viewPager, final Handler handler){
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        handler.sendEmptyMessageDelayed(0,2000);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.sendEmptyMessageDelayed(0,2000);
                        break;
                }
                return false;
            }
        });
    }

    public static void setOnPageChangeListener(final ViewPager viewPager, final int[] ints, final LinearLayout linearLayout){
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                GuideUtil.updateDescAndDot(viewPager,ints,linearLayout);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public static void handleMessage1(Handler handler, final ViewPager viewPager){
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
        handler.sendEmptyMessageDelayed(0,3000);
    }
}
