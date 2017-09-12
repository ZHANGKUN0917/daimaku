package com.bwie.hp.mvplistview2.mode;

import android.content.Context;

import com.bwie.hp.mvplistview2.view.UserView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/4 19 38
 */

public interface IUserMode {
    void getdata(Context context, UserView userview,int page);
}
