package presenter;

import android.content.Context;

import java.io.File;

import model.IModel2;
import model.Model2;
import test.bwie.com.moni.activity.PersonActivity;
import view.IView2;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/14 10 38
 */

public class Presenter2 implements IPresenter2{
    private IView2 iv2;
    private IModel2 im2;

    public Presenter2(IView2 iv2) {
        this.iv2 = iv2;
        im2=new Model2(this);
    }
    public void setdata(Context context, int uid, File file){
        im2.getdata(context,uid,file);
    }
    public void setdata1(PersonActivity personActivity, int uid){
        im2.getdata1(uid);
    }
    @Override
    public void setdata(String mag) {
        iv2.getdata(mag);
    }

    @Override
    public void setdata1(String url) {
         iv2.getdata1(url);
    }
}
