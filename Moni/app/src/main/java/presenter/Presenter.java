package presenter;

import android.util.Log;

import model.IModel;
import model.Model;
import view.IView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/14 08 40
 */

public class Presenter implements Ipresenter{
    private IView iv;
    private IModel im;

    public Presenter(IView iv) {
        this.iv = iv;
        im=new Model(this);

    }
    public void getdata(String phone,String pwd){
           im.getdata(phone,pwd);
    }


    @Override
    public void fanhui(String msg, String phone, String pawd) {
        Log.i("qwa","我给viewle");
        iv.setdata(msg,phone,pawd);
    }
}
