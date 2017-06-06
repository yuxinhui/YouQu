package com.jinfukeji.taqu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by "于志渊"
 * 时间:"14:59"
 * 包名:com.jinfukeji.taqu
 * 描述:App每次打开的缓冲图
 */

public class BufferActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer);
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 startActivity(new Intent(BufferActivity.this,MainActivity.class));
                 finish();
             }
         },2000);
    }
}
