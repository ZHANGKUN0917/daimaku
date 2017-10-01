package com.bwie.hp.mytaobao.app;

import android.app.Application;

import org.xutils.x;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/12 13 56
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}
