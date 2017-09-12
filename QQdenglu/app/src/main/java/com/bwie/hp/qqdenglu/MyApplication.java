package com.bwie.hp.qqdenglu;

import android.app.Application;

import com.example.imagelibrary.utils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/14 20 05
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = utils.getConfiguration(this);
        ImageLoader.getInstance().init(configuration);
    }
}
