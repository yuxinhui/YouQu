package com.jinfukeji.taqu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.activity.dengluandzhuce.DengluActivity;

/**
 * Created by "于志渊"
 * 时间:"14:40"
 * 包名:com.jinfukeji.taqu.fragment
 * 描述:我的界面
 */

public class MyFragment extends Fragment{
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my,container,false);
        zhuceAndDenglu();//登录与注册
        return view;
    }

    //登录与注册
    private Button my_denglu_btn,my_zhuce_btn;
    private void zhuceAndDenglu() {
        my_denglu_btn= (Button) view.findViewById(R.id.my_denglu_btn);
        my_zhuce_btn= (Button) view.findViewById(R.id.my_zhuce_btn);
        my_denglu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DengluActivity.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 11:
                String denglu=data.getExtras().getString("my");
                break;
            default:
                break;
        }
    }
}
