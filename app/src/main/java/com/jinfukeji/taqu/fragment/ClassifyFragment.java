package com.jinfukeji.taqu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinfukeji.taqu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"14:33"
 * 包名:com.jinfukeji.taqu.fragment
 * 描述:社区界面
 */

public class ClassifyFragment extends Fragment {
    View view;
    private ViewPager classify_vp;
    private TabLayout classify_tab;
    private List<Fragment> mList;
    private List<String> titleList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_classify,container,false);
        initView();//初始化控件
        return view;
    }

    //实例化控件
    private void initView() {
        classify_vp= (ViewPager) view.findViewById(R.id.classify_vp);
        classify_tab= (TabLayout) view.findViewById(R.id.classify_tab);
        //设置ViewPager里面也要显示的图片
        mList = new ArrayList<>();
        mList.add(new ClassifyHotFragment());
        mList.add(new ClassifyQuanFragment());
        //设置标题
        titleList = new ArrayList<>();
        titleList.add("热门");
        titleList.add("圈子");
        //设置tab的模式
        classify_tab.setTabMode(TabLayout.GRAVITY_CENTER);
        //添加tab选项卡
        for (int i = 0; i < titleList.size(); i++) {
            classify_tab.addTab(classify_tab.newTab().setText(titleList.get(i)));
        }
        //把TabLayout和ViewPager关联起来
        classify_tab.setupWithViewPager(classify_vp);
        //给ViewPager绑定Adapter
        classify_vp.setAdapter(new MyViewPagerAdapter(getFragmentManager(),mList,titleList));
    }

    //adapter适配器
    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;
        private List<String> titleList;

        public MyViewPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> titleList) {
            super(fm);
            this.list = list;
            this.titleList = titleList;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
    }
}
