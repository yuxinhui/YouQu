package com.jinfukeji.taqu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by "于志渊"
 * 时间:"13:45"
 * 包名:com.jinfukeji.taqu.adapter
 * 描述:所有轮播图的适配器
 */

public class GuideAdapter extends PagerAdapter{
    private int[] imgsID;
    private Context context;

    public GuideAdapter(int[] imgsID, Context context) {
        this.imgsID = imgsID;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        imageView.setImageResource(imgsID[position%imgsID.length]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
