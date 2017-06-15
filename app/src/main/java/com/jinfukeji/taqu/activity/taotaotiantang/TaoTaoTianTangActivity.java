package com.jinfukeji.taqu.activity.taotaotiantang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.fragment.tttt_fragment.RenqiFragment;
import com.jinfukeji.taqu.fragment.tttt_fragment.XinpinFragment;

/**
 * Created by "于志渊"
 * 时间:"14:38"
 * 包名:com.jinfukeji.taqu.activity.taotaotiantang
 * 描述:套套天堂详情界面
 */

public class TaoTaoTianTangActivity extends AppCompatActivity implements View.OnClickListener{
    private RenqiFragment renqiFragment;
    private XinpinFragment xinpinFragment;
    private LinearLayout tttt_xiang_banner_jiage,tttt_xiang_banner_xuan;
    private TextView tttt_xiang_banner_renqi,tttt_xiang_banner_xinpin,ttxq_banner_jiage_txt,ttxq_banner_xuan_txt;
    private ImageView ttxq_banner_jiage_img,ttxq_banner_xuan_img,tttt_xiang_fanhui;
    //FragmentManager对象
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tttt);
        fragmentManager = getSupportFragmentManager();
        initView();//初始化控件
        setChooseView(0);//默认选中第一个
    }

    //初始化控件
    private void initView() {
        tttt_xiang_banner_renqi= (TextView) this.findViewById(R.id.tttt_xiang_banner_renqi);
        tttt_xiang_banner_xinpin= (TextView) this.findViewById(R.id.tttt_xiang_banner_xinpin);
        ttxq_banner_jiage_txt= (TextView) this.findViewById(R.id.ttxq_banner_jiage_txt);
        ttxq_banner_xuan_txt= (TextView) this.findViewById(R.id.ttxq_banner_xuan_txt);

        tttt_xiang_banner_jiage= (LinearLayout) this.findViewById(R.id.tttt_xiang_banner_jiage);
        tttt_xiang_banner_xuan= (LinearLayout) this.findViewById(R.id.tttt_xiang_banner_xuan);

        ttxq_banner_jiage_img= (ImageView) this.findViewById(R.id.ttxq_banner_jiage_img);
        ttxq_banner_xuan_img= (ImageView) this.findViewById(R.id.ttxq_banner_xuan_img);

        tttt_xiang_fanhui= (ImageView) this.findViewById(R.id.tttt_xiang_fanhui);

        tttt_xiang_banner_renqi.setOnClickListener(this);
        tttt_xiang_banner_xinpin.setOnClickListener(this);
        tttt_xiang_banner_jiage.setOnClickListener(this);
        tttt_xiang_banner_xuan.setOnClickListener(this);
        tttt_xiang_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tttt_xiang_banner_renqi:
                setChooseView(0);
                break;
            case R.id.tttt_xiang_banner_xinpin:
                setChooseView(1);
                break;
            case R.id.tttt_xiang_banner_jiage:
                break;
            case R.id.tttt_xiang_banner_xuan:
                break;
            case R.id.tttt_xiang_fanhui:
                Intent intent=new Intent();
                intent.putExtra("index",1);
                setResult(11,intent);
                finish();
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
                tttt_xiang_banner_renqi.setTextColor(huang);
                if (renqiFragment == null){
                    renqiFragment=new RenqiFragment();
                    transaction.add(R.id.tttt_xiang_content,renqiFragment);
                }else {
                    transaction.show(renqiFragment);
                }
                break;
            case 1:
                tttt_xiang_banner_xinpin.setTextColor(huang);
                if (xinpinFragment == null){
                    xinpinFragment=new XinpinFragment();
                    transaction.add(R.id.tttt_xiang_content,xinpinFragment);
                }else {
                    transaction.show(xinpinFragment);
                }
                break;
        }
        transaction.commit();
    }

    //隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (renqiFragment != null){
            transaction.hide(renqiFragment);
        }
        if (xinpinFragment != null){
            transaction.hide(xinpinFragment);
        }
    }

    private int hui=0xFF474545;
    private int huang=0xFFF4DD34;
    //初始化颜色
    private void clearColor() {
        tttt_xiang_banner_renqi.setTextColor(hui);
        tttt_xiang_banner_xinpin.setTextColor(hui);
        ttxq_banner_jiage_txt.setTextColor(hui);
        ttxq_banner_xuan_txt.setTextColor(hui);
        ttxq_banner_jiage_img.setImageResource(R.mipmap.tttt_xiang_banner_jiage);
        ttxq_banner_xuan_img.setImageResource(R.mipmap.tttt_xiang_banner_xuan_hui);
    }
}
