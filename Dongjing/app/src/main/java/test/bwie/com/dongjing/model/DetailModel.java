package test.bwie.com.dongjing.model;

import android.util.Log;

import com.bwie.okhttputils.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import test.bwie.com.dongjing.api.JieKou;
import test.bwie.com.dongjing.bean.Detail;
import test.bwie.com.dongjing.presenter.IDetailPresenter;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/19 13 47
 */

public class DetailModel implements IDetailModel {
    private IDetailPresenter idp;

    public DetailModel(IDetailPresenter idp) {
        this.idp = idp;
    }

    Map<String,String> map=new HashMap<>();
    @Override
    public void detaildata(int pid) {
        map.put("pid",pid+"");
        HttpUtils.doPost(JieKou.detail, map, new HttpUtils.GsonObjectCallback<Detail>() {
            @Override
            public void onUi(Detail detail) {
                Log.i("234",detail+"");
                idp.detail(detail);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
