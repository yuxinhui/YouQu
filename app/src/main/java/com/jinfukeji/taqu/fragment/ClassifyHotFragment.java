package com.jinfukeji.taqu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.adapter.GuideAdapter;
import com.jinfukeji.taqu.utils.GuideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"16:59"
 * 包名:com.jinfukeji.taqu.fragment
 * 描述:社区分类里的热帖界面
 */

public class ClassifyHotFragment extends Fragment {
    View view;

    private Context mContext;
    private ListView mListView;
    private View mHeadView,mFloatBarInLvHeader,mFloatBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_classify_hot,container,false);
        mContext=getContext();
        initView();//初始化控件
        onClick();//点击事件
        initBanner();//轮播图
        return view;
    }

    //点击事件
    private void onClick() {
        // 监听 ListView 滑动事件
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                /**
                 * 判断ListView头部中的浮动栏(mFloatBarInLvHeader)当前是否可见
                 * 来决定隐藏或显示浮动栏(mFloatBar)
                 */
                if (firstVisibleItem >= 1){
                    mFloatBar.setVisibility(View.VISIBLE);
                }else {
                    mFloatBar.setVisibility(View.GONE);
                }
            }
        });
    }

    //控件实例化
    private void initView() {
        mListView= (ListView) view.findViewById(R.id.list_view);
        //ListView 顶部隐藏的浮动栏
        mFloatBar=view.findViewById(R.id.float_bar);
        //ListView 第一个头部控件（效果图中的红色区域）
        mHeadView= LayoutInflater.from(mContext).inflate(R.layout.classify_hot_listview_headview,mListView,false);
        mListView.addHeaderView(mHeadView);
        // ListView 第二个头部控件（浮动栏）
        mFloatBarInLvHeader=LayoutInflater.from(mContext).inflate(R.layout.classify_hot_floatbar,mListView,false);
        mListView.addHeaderView(mFloatBarInLvHeader);

        List<String> data=new ArrayList<>(23);
        for (int i=0;i< 23;i++){
            data.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,data);
        mListView.setAdapter(adapter);
        mListView.setSelection(0);
    }

    //轮播图
    private int[] shequ_imgs;
    private LinearLayout shequ_hot_ll;
    private ViewPager shequ_hot_vp;
    private Handler classify_hot_Handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            GuideUtil.handleMessage1(classify_hot_Handler,shequ_hot_vp);
        }
    };
    private void initBanner() {
        shequ_imgs=new int[]{R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3};
        shequ_hot_ll= (LinearLayout) mHeadView.findViewById(R.id.classify_hot_lunbotu_ll);
        GuideUtil.initDot(shequ_imgs,getContext(),shequ_hot_ll);
        shequ_hot_vp= (ViewPager) mHeadView.findViewById(R.id.classify_hot_lunbotu_vp);
        GuideAdapter shequHot_guideAdapter=new GuideAdapter(shequ_imgs,getContext());
        shequ_hot_vp.setAdapter(shequHot_guideAdapter);
        shequ_hot_vp.setCurrentItem(shequ_imgs.length*10000);
        GuideUtil.updateDescAndDot(shequ_hot_vp,shequ_imgs,shequ_hot_ll);
        classify_hot_Handler.sendEmptyMessageDelayed(0,3000);
        GuideUtil.setOnTouchListener(shequ_hot_vp,classify_hot_Handler);
        GuideUtil.setOnPageChangeListener(shequ_hot_vp,shequ_imgs,shequ_hot_ll);
    }
}
