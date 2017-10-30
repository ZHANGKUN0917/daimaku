package test.bwie.com.dongjing.presenter;

import test.bwie.com.dongjing.bean.FenLei;
import test.bwie.com.dongjing.bean.FenLei1;
import test.bwie.com.dongjing.model.IModel;
import test.bwie.com.dongjing.model.Model;
import test.bwie.com.dongjing.view.IView;
import test.bwie.com.dongjing.view.IView1;
import test.bwie.com.dongjing.view.LoginView;
import test.bwie.com.dongjing.view.RegisterIView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/17 19 51
 */

public class Presenter implements IPresenter{
    private IView iv;
    private IModel im;
    private IView1 iv1;
    private RegisterIView riv;
    private LoginView lv;
    public Presenter(IView1 iv1) {
        this.iv1 = iv1;
        im=new Model(this);
    }
    public Presenter(IView iv) {
        this.iv = iv;
        im=new Model(this);
    }

    public Presenter(RegisterIView riv) {
        this.riv = riv;
        im=new Model(this);
    }

    public Presenter(LoginView lv) {
        this.lv = lv;
        im=new Model(this);
    }
    public void setdata(){
        im.Fgetdata();
    }
    public void setdata1(int position,int cid){
         im.Fgetdata1(position,cid);
    }
    public void registerdata(String phone,String pwd){
        im.RegisterData(phone,pwd);
    }
    public void Logindata(String phone,String pwd){
        im.LoginData(phone,pwd);
    }
    @Override
    public void gview(FenLei fenLei) {
        iv.dd(fenLei);
    }
    @Override
    public void gview1(FenLei1 fenLei1) {
         iv1.dd1(fenLei1);
    }
    @Override
    public void gRegister(String mag,String phone,String pwd) {
        riv.GRegister(mag,phone,pwd);
    }
    @Override
    public void login(String msg, int uid) {
        lv.Login(msg,uid);
    }
}
