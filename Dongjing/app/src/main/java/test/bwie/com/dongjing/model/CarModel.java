package test.bwie.com.dongjing.model;

import android.util.Log;

import com.bwie.okhttputils.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import test.bwie.com.dongjing.api.JieKou;
import test.bwie.com.dongjing.bean.Car;
import test.bwie.com.dongjing.presenter.ICarPresenter;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/19 16 36
 */

public class CarModel implements ICarModel {
    private ICarPresenter ICP;

    public CarModel(ICarPresenter ICP) {
        this.ICP = ICP;
    }

    private Map<String,String> map=new HashMap<>();
    @Override
    public void Cardata(int uid) {
        map.put("uid",uid+"");
        HttpUtils.doPost(JieKou.shopping, map, new HttpUtils.GsonObjectCallback<Car>() {
            @Override
            public void onUi(Car car) {
                Log.i("123",car+"");
                ICP.shopcar(car);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
