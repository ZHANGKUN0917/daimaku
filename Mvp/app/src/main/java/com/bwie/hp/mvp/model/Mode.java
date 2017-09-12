package com.bwie.hp.mvp.model;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/1 20 31
 */

public interface Mode {
    public void login(String name,String pwd,LoginListener listener);
}
