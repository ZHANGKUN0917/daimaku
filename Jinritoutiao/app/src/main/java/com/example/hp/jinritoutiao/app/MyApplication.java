package com.example.hp.jinritoutiao.app;

import android.app.Application;

import com.example.imagelibrary.utils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/10 23 22
 */

public class MyApplication extends Application{

    private ImageLoaderConfiguration configuration;

    @Override
    public void onCreate() {
        super.onCreate();
        configuration = utils.getConfiguration(this);
        ImageLoader.getInstance().init(configuration);
    }
}
