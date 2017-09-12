package com.bwie.hp.mvplistview2.presenter;

import android.content.Context;

import com.bwie.hp.mvplistview2.mode.IUserMode;
import com.bwie.hp.mvplistview2.mode.Usermode;
import com.bwie.hp.mvplistview2.view.UserView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/4 20 24
 */

public class Presenter {
    private UserView userview;
    private IUserMode iusermode;

    public Presenter(UserView userview) {
        this.userview = userview;
        iusermode=new Usermode();
    }
    public void getdata(Context context,int page){
        iusermode.getdata(context,userview,page);
    }
}
