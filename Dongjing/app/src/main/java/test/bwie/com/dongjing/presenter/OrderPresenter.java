package test.bwie.com.dongjing.presenter;

import test.bwie.com.dongjing.model.IOrderModel;
import test.bwie.com.dongjing.model.Ordermodel;
import test.bwie.com.dongjing.view.IOrderView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/20 14 37
 */

public class OrderPresenter implements IOrederPresenter{
    private IOrderView iv;
    private IOrderModel im;

    public OrderPresenter(IOrderView iv) {
        this.iv = iv;
        im=new Ordermodel(this);
    }
    public void orderdata(int uid,int price){
        im.orderdata(uid,price);
    }

    @Override
    public void order(String msg) {
        iv.String(msg);
    }
}
