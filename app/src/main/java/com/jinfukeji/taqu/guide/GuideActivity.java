package com.jinfukeji.taqu.guide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jinfukeji.taqu.MainActivity;
import com.jinfukeji.taqu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"15:15"
 * 包名:com.jinfukeji.taqu.guide
 * 描述:app引导图
 */

public class GuideActivity extends AppCompatActivity {
    private ViewPager viewPager;
    //图片资源
    private int[] pics=new int[]{R.mipmap.banner_index_ok,R.mipmap.banner_classify_ok,
            R.mipmap.banner_shoppingcart_ok,R.mipmap.banner_my_ok};
    private List<View> list;
    //底部小点
    private ViewGroup lldots;
    //进入按钮
    private Button guide_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();//控件初始化
        initOper();//监听事件
        addView();//添加图片
        addDots();//添加圆点
    }

    //添加圆点
    private void addDots() {
        for (int i=0;i<pics.length;i++){
            LinearLayout.LayoutParams dotParams=new LinearLayout.LayoutParams(15,15);
            if (i < 1){
                dotParams.setMargins(0,0,0,0);
            }else {
                dotParams.setMargins(20,0,0,0);
            }
            ImageView iv=new ImageView(this);
            iv.setLayoutParams(dotParams);
            iv.setBackgroundResource(R.drawable.huidot);
            lldots.addView(iv);
        }
        lldots.getChildAt(0).setBackgroundResource(R.drawable.baidot);
    }

    //添加图片
    private void addView() {
        list=new ArrayList<View>();
        // 将pics添加到view
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        for (int i=0;i<pics.length;i++){
            ImageView iv=new ImageView(this);
            iv.setLayoutParams(params);
            iv.setBackgroundResource(pics[i]);
            list.add(iv);
        }
        // 加入适配器
        viewPager.setAdapter(new GuideViewAdapter(list));
    }

    private void initOper() {
        guide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置已经引导
                setGuide();
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
        //监听当前显示的页面，将对应的小圆点设置为选中状态，其它设置为未选中状态
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                monitorPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //判断小圆点
    private void monitorPoint(int position) {
        for (int i=0;i<pics.length;i++){
            if (i == position){
                lldots.getChildAt(position).setBackgroundResource(R.drawable.baidot);
            }else {
                lldots.getChildAt(i).setBackgroundResource(R.drawable.huidot);
            }
        }
        //当滑动到最后一个添加按钮点击进入
        if (position == pics.length-1){
            guide_btn.setVisibility(View.VISIBLE);
        }else {
            guide_btn.setVisibility(View.GONE);
        }
    }

    private static final String buffer_name="activity_buffer";
    private static final String guide_name="activity_guide";
    private void setGuide() {
        SharedPreferences preferences=getSharedPreferences(buffer_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(guide_name,"false");
        editor.apply();
    }

    //初始化控件
    private void initView() {
        viewPager= (ViewPager) this.findViewById(R.id.viewpage);
        lldots= (ViewGroup) this.findViewById(R.id.dots_ll);
        guide_btn= (Button) this.findViewById(R.id.guide_btn);
    }
}
