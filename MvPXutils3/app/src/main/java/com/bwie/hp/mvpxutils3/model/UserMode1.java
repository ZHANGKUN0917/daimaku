package com.bwie.hp.mvpxutils3.model;

import android.util.Log;

import com.bwie.hp.mvpxutils3.app.MyApp;
import com.bwie.hp.mvpxutils3.bean.UserBean;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/2 09 08
 */

public class UserMode1 implements UserMode {
    DbManager manager = MyApp.getDb();
    @Override
    public void registe(String name, String pwd) {

        List<UserBean> list=new ArrayList<>();
        list.add(new UserBean(name,pwd));
        try {
            manager.save(list);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(String name, String pwd) {
        try {
            List<UserBean> all = manager.findAll(UserBean.class);
            for (UserBean l:all) {
                if(name.equals(l.getName())&&pwd.equals(l.getPwd())){
                    Log.i("ssss","登陆成功");
                }else{
                    Log.i("ddddd","登陆失败");
                }
                
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


}
