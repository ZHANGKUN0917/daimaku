package presenter;

import model.Imodel1;
import model.model1;
import view.IView1;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/14 09 35
 */

public class Presenter1 implements Ipresenter1{
    private IView1 iv1;
    private model1 model1;

    public Presenter1(IView1 iv1) {
        this.iv1 = iv1;
        model1=new Imodel1(this);
    }
    public void getdata(String phone,String pwd){
         model1.getdata(phone,pwd);
    }

    @Override
    public void setdata(String mag, int uid,String username) {
        iv1.getdata(mag,uid,username);
    }
}
