package test.bwie.com.dongjing.presenter;

import test.bwie.com.dongjing.model.IShopModel;
import test.bwie.com.dongjing.model.ShopModel;
import test.bwie.com.dongjing.view.IShopView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/19 14 24
 */

public class ShopPresenter implements IShopPresenter {
    private IShopView isv;
    private IShopModel ism;

    public ShopPresenter(IShopView isv) {
        this.isv = isv;
        ism=new ShopModel(this);
    }
    public void car(int uid,int pid,int sellerid){
        ism.car(uid,pid,sellerid);
    }

    @Override
    public void car(String msg) {
        isv.car(msg);
    }
}
