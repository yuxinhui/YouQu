package com.jinfukeji.taqu.fragment.flashsalefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinfukeji.taqu.R;

/**
 * Created by "于志渊"
 * 时间:"16:10"
 * 包名:com.jinfukeji.taqu.fragment.flashsalefragment
 * 描述:限时抢购详情的场次界面
 */

public class XsqxqChangciFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_xsqxq_changci,container,false);
        return view;
    }
}
