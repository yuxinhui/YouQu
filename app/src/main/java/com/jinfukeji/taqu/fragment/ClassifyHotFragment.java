package com.jinfukeji.taqu.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.adapter.ClassifyHotAdapter;
import com.jinfukeji.taqu.utils.LinearlayoutSpacesItemDecoration;

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
    private RecyclerView classify_hot_rv;
    private LinearLayoutManager layoutManager;
    private SwipeRefreshLayout classify_swipe;
    private ClassifyHotAdapter hotAdapter;
    private List<String> mListData;
    private List<String> mListLoadData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_classify_hot,container,false);
        mContext=getContext();
        initData();
        initView();//初始化控件
        onClick();//点击事件
        return view;
    }

    private void initData() {
        mListData = new ArrayList<>();
        mListLoadData = new ArrayList<>();
        for(int i=0;i<20;i++){
            mListData.add("数据"+i);
            mListLoadData.add("加载的新数据"+i);
        }
    }

    //点击事件
    private void onClick() {
        // 监听 ListView 滑动事件
        classify_hot_rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!classify_swipe.isRefreshing()){
                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==hotAdapter.getItemCount()){
                        //调用Adapter里的changeMoreStatus方法来改变加载脚View的显示状态为：正在加载...
                        hotAdapter.changeMoreStatus(ClassifyHotAdapter.ISLOADING);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mListData.addAll(mListLoadData);
                                hotAdapter.notifyDataSetChanged();
                                //当加载完数据后，再恢复加载脚View的显示状态为：上拉加载更多
                                hotAdapter.changeMoreStatus(ClassifyHotAdapter.PULLUP_LOAD_MORE);
                            }
                        },3000);
                    }
                }
            }
        });
        classify_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        classify_swipe.setRefreshing(false);
                    }
                },5000);
            }
        });
    }

    //控件实例化
    private void initView() {
        classify_swipe= (SwipeRefreshLayout) view.findViewById(R.id.classify_swipe);
        classify_swipe.setColorSchemeColors(Color.BLUE,Color.RED,Color.YELLOW);
        classify_hot_rv= (RecyclerView) view.findViewById(R.id.classify_hot_rv);
        layoutManager=new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classify_hot_rv.setLayoutManager(layoutManager);
        classify_hot_rv.addItemDecoration(new LinearlayoutSpacesItemDecoration(10));
        hotAdapter=new ClassifyHotAdapter(mContext,mListData);
        hotAdapter.setmOnItemClickLitener(new ClassifyHotAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        classify_hot_rv.setAdapter(hotAdapter);
    }
}
