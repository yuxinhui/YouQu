package com.jinfukeji.taqu.activity.dengluandzhuce;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.utils.ChenJinShiUtil;

/**
 * Created by "于志渊"
 * 时间:"13:43"
 * 包名:com.jinfukeji.taqu.activity.dengluandzhuce
 * 描述:登录界面
 */

public class DengluActivity extends AppCompatActivity{
    private ImageView denglu_fanhui;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChenJinShiUtil.chenJin(this);
        setContentView(R.layout.actiivty_denglu);
        initView();//初始化控件
        onClick();//点击事件
    }

    //点击事件
    private void onClick() {
       denglu_fanhui.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent();
               intent.putExtra("my",1);
               setResult(11,intent);
               finish();
           }
       });
    }

    //初始化控件
    private void initView() {
        denglu_fanhui= (ImageView) this.findViewById(R.id.denglu_fanhui);
    }
}
