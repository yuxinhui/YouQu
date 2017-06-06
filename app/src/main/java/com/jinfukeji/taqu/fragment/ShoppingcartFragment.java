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
 * 时间:"14:38"
 * 包名:com.jinfukeji.taqu.fragment
 * 描述:购物车界面
 */

public class ShoppingcartFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shopping,container,false);
        return view;
    }
}
