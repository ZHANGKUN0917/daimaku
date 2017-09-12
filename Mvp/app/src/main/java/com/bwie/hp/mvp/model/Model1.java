package com.bwie.hp.mvp.model;

import android.text.TextUtils;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/1 20 33
 */

public class Model1 implements Mode {

    @Override
    public void login(String name, String pwd, LoginListener listener) {
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
            listener.fail();
        }
        if("张三".equals(name)&&"123456".equals(pwd)){
            listener.onsucess();
        }else{
            listener.error();
        }
    }
}
