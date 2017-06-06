package com.jinfukeji.taqu;

import android.app.Application;

/**
 * Created by "于志渊"
 * 时间:"14:51"
 * 包名:com.jinfukeji.taqu
 * 描述:存放全局的量
 */

public class TaQuAppcation extends Application{
    public static final String URL="";
    public TaQuAppcation instace;

    public static String getURL() {
        return URL;
    }

    public TaQuAppcation getInstace() {
        return instace;
    }

    public void setInstace(TaQuAppcation instace) {
        this.instace = instace;
    }
}
