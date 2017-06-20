package com.jinfukeji.taqu.fragment.flashsalefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.adapter.XsqxqTimeAdapter;
import com.jinfukeji.taqu.utils.LinearlayoutSpacesItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"16:06"
 * 包名:com.jinfukeji.taqu.fragment.flashsalefragment
 * 描述:限时抢购详情的倒计时界面
 */

public class XsqxqTimeFragment extends Fragment{
    View view;
    private RecyclerView xsqxq_time_rv;
    private XsqxqTimeAdapter timeAdapter;
    List<Integer> integerList=new ArrayList<Integer>(Arrays.asList(R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3,R.mipmap.buffer4,
            R.mipmap.buffer5,R.mipmap.buffer6,R.mipmap.buffer7,R.mipmap.buffer8));
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_xsqxq_time,container,false);
        initView();//初始化控件
        return view;
    }

    private void initView() {
        xsqxq_time_rv= (RecyclerView) view.findViewById(R.id.xsqxq_time_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xsqxq_time_rv.setLayoutManager(layoutManager);
        xsqxq_time_rv.addItemDecoration(new LinearlayoutSpacesItemDecoration(10));
        timeAdapter=new XsqxqTimeAdapter(getContext(),integerList);
        xsqxq_time_rv.setAdapter(timeAdapter);
    }
}
