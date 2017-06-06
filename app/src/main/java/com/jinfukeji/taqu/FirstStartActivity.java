package com.jinfukeji.taqu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jinfukeji.taqu.guide.GuideActivity;

/**
 * Created by "于志渊"
 * 时间:"14:55"
 * 包名:com.jinfukeji.taqu
 * 描述:首次启动app判定
 */

public class FirstStartActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean mFirst=isFirstEnter(this,this.getClass().getName());
        if (mFirst){
            handler.sendEmptyMessageAtTime(switch_guideactivity,1000);
        }else {
            handler.sendEmptyMessageAtTime(switch_bufferactivity,1000);
        }
    }


    // 判断应用是否初次加载，读取SharedPreferences中的guide_activity字段
    private static final String buffer_name="activity_buffer";
    private static final String guide_name="activity_guide";
    private boolean isFirstEnter(Context context, String s) {
        if (context == null || s == null || "".equalsIgnoreCase(s)){
            return false;
        }
        String isEnter=context.getSharedPreferences(buffer_name,Context.MODE_PRIVATE).getString(guide_name,"");
        if (isEnter.equalsIgnoreCase("false")){
            return false;
        }else {
            return true;
        }
    }

    //Handler:跳转至不同页面
    public static final int switch_bufferactivity=1000;
    public static final int switch_guideactivity=1001;
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case switch_bufferactivity:
                    startActivity(new Intent(FirstStartActivity.this,BufferActivity.class));
                    finish();
                    break;
                case switch_guideactivity:
                    startActivity(new Intent(FirstStartActivity.this, GuideActivity.class));
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
