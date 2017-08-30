package com.example.hp.imageloader.app;

import android.app.Application;

import com.example.imagelibrary.utils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/4 14 25
 */

public class MyApplication extends Application {

    private ImageLoaderConfiguration configuration;

    @Override
    public void onCreate() {
        super.onCreate();
         //进行全局配置
        configuration = utils.getConfiguration(this);
        //使用到单例设计模式  进行初始化
        ImageLoader.getInstance().init(configuration);


    }
}
