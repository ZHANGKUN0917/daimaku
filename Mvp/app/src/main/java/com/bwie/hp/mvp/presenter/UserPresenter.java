package com.bwie.hp.mvp.presenter;

import com.bwie.hp.mvp.model.LoginListener;
import com.bwie.hp.mvp.model.Mode;
import com.bwie.hp.mvp.model.Model1;
import com.bwie.hp.mvp.view.UserView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/1 20 43
 */

public class UserPresenter {
    //数据源
    private UserView uv;
    //处理业务逻辑
    private Mode mode;

    public UserPresenter(UserView uv) {
        this.uv = uv;
        mode=new Model1();
    }

    public void login(LoginListener listener){
        mode.login(uv.getname(),uv.getpwd(),listener);
    }

}
