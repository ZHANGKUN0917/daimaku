package com.bwie.hp.mvpxutils3.presenter;

import com.bwie.hp.mvpxutils3.model.UserMode;
import com.bwie.hp.mvpxutils3.model.UserMode1;
import com.bwie.hp.mvpxutils3.view.UserView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/2 09 25
 */

public class UserPresenter {
    private UserView uv;
    private UserMode userMode;

    public UserPresenter(UserView uv) {
        this.uv = uv;
        userMode = new UserMode1();
    }
    public void register(String name,String pwd){
        userMode.registe(name,pwd);
    }
    public void login(String name,String pwd){
           userMode.login(name,pwd);
    }
}
