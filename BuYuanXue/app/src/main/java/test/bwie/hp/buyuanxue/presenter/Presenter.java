package test.bwie.hp.buyuanxue.presenter;

import android.content.Context;

import test.bwie.hp.buyuanxue.model.IModel;
import test.bwie.hp.buyuanxue.model.Model;
import test.bwie.hp.buyuanxue.view.IView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/28 15 26
 */

public class Presenter implements IPresenter{
    private IModel iModel;
    private IView iview;

    public Presenter(IView iview) {
        this.iview = iview;
        iModel=new Model(this);
    }


    public void getdata(String phone, Context context){
        iModel.getdata(phone,context);
    }
    public void getdata1(String phone,String code){
         iModel.getdata1(phone,code);
    }

    @Override
    public void setdata1(String info) {
         iview.setdata1(info);
    }
}
