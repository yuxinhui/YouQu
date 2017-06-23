package com.jinfukeji.taqu.activity.xianshiqiang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.fragment.flashsalefragment.XsqxqChangciFragment;
import com.jinfukeji.taqu.fragment.flashsalefragment.XsqxqTimeFragment;

/**
 * Created by "于志渊"
 * 时间:"13:51"
 * 包名:com.jinfukeji.taqu.activity.xianshiqiang
 * 描述:限时抢购详情页
 */

public class FlashSalectivity extends AppCompatActivity implements View.OnClickListener{
    private XsqxqTimeFragment timeFragment;
    private XsqxqChangciFragment changciFragment;
    private RelativeLayout xsqxiangqin_time_rl,xsqxiangqin_changci_rl;
    private TextView xsqxq_time_tv,xsqxq_changci_tv,xsqxq_changci;
    private ImageView xsqxq_fanhui;
    //FragmentManager对象
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashsale);
        fragmentManager = getSupportFragmentManager();
        initView();//初始化控件
        setChooseView(0);//默认选中第一个
    }

    //初始化控件
    private void initView() {
        xsqxq_time_tv= (TextView) this.findViewById(R.id.xsqxq_time_tv);
        xsqxq_changci= (TextView) this.findViewById(R.id.xsqxq_changci);
        xsqxq_changci_tv= (TextView) this.findViewById(R.id.xsqxq_changci_tv);
        xsqxiangqin_time_rl= (RelativeLayout) this.findViewById(R.id.xsqxiangqin_time_rl);
        xsqxiangqin_changci_rl= (RelativeLayout) this.findViewById(R.id.xsqxiangqin_changci_rl);
        xsqxq_fanhui= (ImageView) this.findViewById(R.id.xsqxq_fanhui);

        xsqxiangqin_time_rl.setOnClickListener(this);
        xsqxiangqin_changci_rl.setOnClickListener(this);
        xsqxq_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xsqxq_fanhui:
                Intent intent=new Intent();
                intent.putExtra("index",2);
                setResult(22,intent);
                finish();
                break;
            case R.id.xsqxiangqin_time_rl:
                setChooseView(0);
                break;
            case R.id.xsqxiangqin_changci_rl:
                setChooseView(1);
                break;
            default:
                break;
        }
    }

    public void setChooseView(int i) {
        //重置选项+隐藏所有Fragment
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        clearColor();
        hideFragments(transaction);
        switch (i){
            case 0:
                xsqxiangqin_time_rl.setBackgroundColor(huang);
                xsqxq_time_tv.setTextColor(juhuang);
                if (timeFragment == null){
                    timeFragment=new XsqxqTimeFragment();
                    transaction.add(R.id.xsqxq_content,timeFragment);
                }else {
                    transaction.show(timeFragment);
                }
                break;
            case 1:
                xsqxiangqin_changci_rl.setBackgroundColor(huang);
                xsqxq_changci.setTextColor(juhuang);
                xsqxq_changci_tv.setTextColor(juhuang);
                if (changciFragment ==null){
                    changciFragment=new XsqxqChangciFragment();
                    transaction.add(R.id.xsqxq_content,changciFragment);
                }else {
                    transaction.show(changciFragment);
                }
                break;
        }
        transaction.commit();
    }

    //隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (timeFragment != null){
            transaction.hide(timeFragment);
        }
        if (changciFragment != null){
            transaction.hide(changciFragment);
        }
    }

    private int hui=0xFF474545;
    private int juhuang=0xFFF12623;
    private int hei=0xFF000000;
    private int huang=0xFFF4DD34;
    //初始化颜色
    private void clearColor() {
        xsqxiangqin_time_rl.setBackgroundColor(hei);
        xsqxiangqin_changci_rl.setBackgroundColor(hei);
        xsqxq_time_tv.setTextColor(hui);
        xsqxq_changci_tv.setTextColor(hui);
        xsqxq_changci.setTextColor(hui);
    }
}
