package com.jinfukeji.taqu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinfukeji.taqu.R;

/**
 * Created by "于志渊"
 * 时间:"14:40"
 * 包名:com.jinfukeji.taqu.fragment
 * 描述:我的界面
 */

public class MyFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,container,false);
        return view;
    }
}
