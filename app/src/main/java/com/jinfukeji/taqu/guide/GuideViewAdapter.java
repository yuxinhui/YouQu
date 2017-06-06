package com.jinfukeji.taqu.guide;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"16:47"
 * 包名:com.jinfukeji.taqu.guide
 * 描述:引导图适配器
 */

public class GuideViewAdapter extends PagerAdapter{
    private List<View> list;

    public GuideViewAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }
}
