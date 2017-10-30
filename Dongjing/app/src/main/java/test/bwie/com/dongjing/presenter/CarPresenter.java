package test.bwie.com.dongjing.presenter;

import test.bwie.com.dongjing.bean.Car;
import test.bwie.com.dongjing.model.CarModel;
import test.bwie.com.dongjing.model.ICarModel;
import test.bwie.com.dongjing.view.ICarView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/19 16 36
 */

public class CarPresenter implements ICarPresenter{
    private ICarView iv;
    private ICarModel im;

    public CarPresenter(ICarView iv) {
        this.iv = iv;
        im=new CarModel(this);
    }
    public void car(int uid){
        im.Cardata(uid);
    }
    @Override
    public void shopcar(Car car) {
         iv.shopcar(car);
    }
}
