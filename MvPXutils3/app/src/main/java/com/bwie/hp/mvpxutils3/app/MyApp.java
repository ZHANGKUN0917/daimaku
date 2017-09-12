package com.bwie.hp.mvpxutils3.app;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/2 08 48
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
    //初始化daoconfig
    public static DbManager getDb(){
        DbManager.DaoConfig daoConfig=new DbManager.DaoConfig().setDbName("bawei.db").setDbDir(new File("/mnt/sdcard")).setDbVersion(1);
        DbManager db = x.getDb(daoConfig);
        return db;
    }
}
