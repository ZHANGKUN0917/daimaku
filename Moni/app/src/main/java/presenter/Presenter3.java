package presenter;

import org.json.JSONArray;

import model.IModel3;
import model.Model3;
import view.IView3;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/15 11 07
 */

public class Presenter3 implements IPresenter3{
    private IView3 iv3;
    private IModel3 im3;

    public Presenter3(IView3 iv3) {
        this.iv3 = iv3;
        im3=new Model3(this);
    }
    public void getdata(String name,int page){
        im3.getdata(name,page);
    }
    @Override
    public void setdata(JSONArray data) {
        iv3.getdata(data);
    }
}
