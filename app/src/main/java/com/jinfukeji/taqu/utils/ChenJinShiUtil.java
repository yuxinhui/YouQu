package com.jinfukeji.taqu.utils;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by "于志渊"
 * 时间:"13:33"
 * 包名:com.jinfukeji.taqu.utils
 * 描述:沉浸式界面
 */

public class ChenJinShiUtil {
    /**
     * SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思
     * SYSTEM_UI_FLAG_FULLSCREEN,SYSTEM_UI_FLAG_LAYOUT_STABLE，注意两个Flag必须要结合在一起使用，
     * 表示会让应用的主体内容占用系统状态栏的空间
     * SYSTEM_UI_FLAG_HIDE_NAVIGATION和SYSTEM_UI_FLAG_FULLSCREEN，这样就可以将状态栏和导航栏同时隐藏了
     * SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION，表示会让应用的主体内容占用系统导航栏的空间
     * */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void chenJin(AppCompatActivity activity){
        if (Build.VERSION.SDK_INT >= 19){
            View decorView=activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar=activity.getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
    }
}
