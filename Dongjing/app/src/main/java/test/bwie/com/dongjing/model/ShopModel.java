package test.bwie.com.dongjing.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bwie.okhttputils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.dongjing.api.JieKou;
import test.bwie.com.dongjing.presenter.IShopPresenter;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/19 14 25
 */

public class ShopModel implements IShopModel {
    private IShopPresenter isp;

    public ShopModel(IShopPresenter isp) {
        this.isp = isp;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    try {
                        JSONObject object=new JSONObject((String)msg.obj);
                        String msg1 = object.getString("msg");
                        String code = object.getString("code");
                        if(code.equals("0")){
                            isp.car(msg1);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }
    };
   Map<String,String> map=new HashMap<>();
    @Override
    public void car(int uid, int pid, int sellerid) {
        map.put("uid",uid+"");
        map.put("pid",pid+"");
        map.put("sellerid",sellerid+"");
        HttpUtils.doPost(JieKou.car, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               String s=response.body().string();
                Log.i("9090",s);
                Message message=new Message();
                message.what=0;
                message.obj=s;
                handler.sendMessage(message);
            }
        });
    }
}
