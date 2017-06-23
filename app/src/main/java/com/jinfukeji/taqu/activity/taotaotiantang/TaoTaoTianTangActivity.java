package com.jinfukeji.taqu.activity.taotaotiantang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.fragment.tttt_fragment.RenqiFragment;
import com.jinfukeji.taqu.fragment.tttt_fragment.XinpinFragment;
import com.jinfukeji.taqu.utils.FlowLayout;

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
                showPop();//底部弹窗
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

    //筛选的弹窗
    View shaixuan_view;
    AlertDialog dialog;
    private void showPop() {
        clearColor();
        ttxq_banner_xuan_txt.setTextColor(huang);
        ttxq_banner_xuan_img.setImageResource(R.mipmap.tttt_xiang_banner_xuan_ok);
        dialog=new AlertDialog.Builder(this).create();
        dialog.show();
        Window window=dialog.getWindow();
        assert window != null;
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.shaixuan_popup);
        shaixuan_view=View.inflate(this,R.layout.pop_view_shaixuan,null);
        popInIntView();//弹窗里面的控件
        popOnclick();//点击事件
        window.setContentView(shaixuan_view);
        WindowManager windowManager= (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int pop_height=(windowManager.getDefaultDisplay().getHeight())/2;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,pop_height);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
    }

    //弹窗里面的控件
    private FlowLayout ttxq_xuan_fenlei,ttxq_xuan_pinpai;
    private Button ttxq_xuan_quxiao,ttxq_xuan_ok;
    private String[] mPaizi={"坏坏二","哈哈哈","杜蕾斯","杰士邦","西鞥类","爱惜吧"};
    private String[] mFenlei={"小二坏坏","哈哈花花","冰火二重","随便测试","西东南北","上下左右","掐后例外","傻逼小雷"};
    private void popInIntView() {
        ttxq_xuan_pinpai= (FlowLayout) shaixuan_view.findViewById(R.id.ttxq_xuan_pinpai);
        ttxq_xuan_fenlei= (FlowLayout) shaixuan_view.findViewById(R.id.ttqx_xuan_fenlei);
        ttxq_xuan_quxiao= (Button) shaixuan_view.findViewById(R.id.ttxq_xuan_quxiao);
        ttxq_xuan_ok= (Button) shaixuan_view.findViewById(R.id.ttxq_xuan_ok);

        ttxq_xuan_pinpai.removeAllViews();
        for (int i=0;i<mPaizi.length;i++){
            CheckBox paiZiBox= (CheckBox) View.inflate(shaixuan_view.getContext(),R.layout.item_flowlayout,null);
            paiZiBox.setText(mPaizi[i]);
            final int finalX=i;
            paiZiBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshCheckBox(mPaizi[finalX]);
                }
            });
            ttxq_xuan_pinpai.addView(paiZiBox);
        }
        ttxq_xuan_fenlei.removeAllViews();
        for (int a=0;a<mPaizi.length;a++){
            CheckBox fenLeiBox= (CheckBox) View.inflate(shaixuan_view.getContext(),R.layout.item_flowlayout,null);
            fenLeiBox.setText(mFenlei[a]);
            final int finalX=a;
            fenLeiBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshCheckBox1(mFenlei[finalX]);
                }
            });
            ttxq_xuan_fenlei.addView(fenLeiBox);
        }
    }

    private void refreshCheckBox(String s) {
        for(int y=0;y<ttxq_xuan_pinpai.getChildCount();y++){
            CheckBox radio = (CheckBox) ttxq_xuan_pinpai.getChildAt(y);

            if(s.equals(radio.getText())){
                radio.setChecked(true);
            }else {
                radio.setChecked(false);
            }
        }
    }

    private void refreshCheckBox1(String s) {
        for(int y=0;y<ttxq_xuan_fenlei.getChildCount();y++){
            CheckBox radio = (CheckBox) ttxq_xuan_fenlei.getChildAt(y);

            if(s.equals(radio.getText())){
                radio.setChecked(true);
            }else {
                radio.setChecked(false);
            }
        }
    }

    //弹窗里点击事件
    private void popOnclick(){
        ttxq_xuan_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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
