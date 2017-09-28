package com.bwie.hp.lanjieqi;

import android.app.Application;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/21 19 56
 */

public class MyApp extends Application {
    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }
    public static MyApp getInstance() {
        return mInstance;
    }
}
