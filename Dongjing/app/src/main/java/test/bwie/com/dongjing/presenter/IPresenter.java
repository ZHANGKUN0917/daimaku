package test.bwie.com.dongjing.presenter;

import test.bwie.com.dongjing.bean.FenLei;
import test.bwie.com.dongjing.bean.FenLei1;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/17 19 52
 */

public interface IPresenter {
    void gview(FenLei FenLei);
    void gview1(FenLei1 fenLei1);
    void gRegister(String mag,String phone,String pwd);
    void login(String msg,int uid);
}
