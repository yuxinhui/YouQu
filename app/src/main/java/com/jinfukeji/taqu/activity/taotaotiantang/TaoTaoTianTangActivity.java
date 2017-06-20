package com.jinfukeji.taqu.activity.taotaotiantang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.adapter.TtxqXuanFenleiAdapter;
import com.jinfukeji.taqu.fragment.tttt_fragment.RenqiFragment;
import com.jinfukeji.taqu.fragment.tttt_fragment.XinpinFragment;
import com.jinfukeji.taqu.utils.GridSpacingItemDecoration;

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
    private RecyclerView ttqx_xuan_fenlei,ttxq_xuan_pinpai;
    private Button ttxq_xuan_quxiao,ttxq_xuan_ok;
    private TtxqXuanFenleiAdapter xuanFenleiAdapter;
    int spanCount=3;
    int spacing=10;
    private void popInIntView() {
        ttxq_xuan_pinpai= (RecyclerView) shaixuan_view.findViewById(R.id.ttxq_xuan_pinpai);
        ttqx_xuan_fenlei= (RecyclerView) shaixuan_view.findViewById(R.id.ttqx_xuan_fenlei);
        ttxq_xuan_quxiao= (Button) shaixuan_view.findViewById(R.id.ttxq_xuan_quxiao);
        ttxq_xuan_ok= (Button) shaixuan_view.findViewById(R.id.ttxq_xuan_ok);

        GridLayoutManager layoutManager=new GridLayoutManager(shaixuan_view.getContext(),3,GridLayoutManager.VERTICAL,false);
        ttxq_xuan_pinpai.setLayoutManager(layoutManager);
        ttxq_xuan_pinpai.addItemDecoration(new GridSpacingItemDecoration(spanCount,spacing,false));
        String[] mPaizi={"坏坏","哈哈","杜蕾斯","杰士邦","西鞥类","爱惜吧"};
        xuanFenleiAdapter=new TtxqXuanFenleiAdapter(mPaizi,shaixuan_view.getContext());
        xuanFenleiAdapter.setmOnItemClickListener(new TtxqXuanFenleiAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                view.findViewById(R.id.item_ttxq_xuan_fenlei_tit).setBackgroundResource(R.drawable.item_ttxq_xuan_text_style_zhong);
            }
        });
        ttxq_xuan_pinpai.setAdapter(xuanFenleiAdapter);

        String[] mFenlei={"坏坏","哈哈","杜蕾斯","杰士邦","西鞥类","爱惜吧","西巴拉","巴扎黑"};
        GridLayoutManager layoutManager1=new GridLayoutManager(shaixuan_view.getContext(),3,GridLayoutManager.VERTICAL,false);
        ttqx_xuan_fenlei.setLayoutManager(layoutManager1);
        ttqx_xuan_fenlei.addItemDecoration(new GridSpacingItemDecoration(spanCount,spacing,false));
        xuanFenleiAdapter=new TtxqXuanFenleiAdapter(mFenlei,shaixuan_view.getContext());
        ttqx_xuan_fenlei.setAdapter(xuanFenleiAdapter);
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
