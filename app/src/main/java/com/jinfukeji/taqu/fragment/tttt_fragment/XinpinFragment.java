package com.jinfukeji.taqu.fragment.tttt_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinfukeji.taqu.R;

/**
 * Created by "于志渊"
 * 时间:"14:29"
 * 包名:com.jinfukeji.taqu.fragment.tttt_fragment
 * 描述:套套天堂新品界面
 */

public class XinpinFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_ttxq_xinpin,container,false);
        return view;
    }
}
