package model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bwie.hp.lanjieqi.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import presenter.Ipresenter;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/14 08 40
 */

public class Model implements IModel {
    private Ipresenter ip;

    public Model(Ipresenter ip) {
        this.ip = ip;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    try {
                        JSONObject object=new JSONObject((String)msg.obj);
                        String code = object.getString("code");
                        JSONObject data = object.getJSONObject("data");
                        String mobile = data.getString("mobile");
                        Log.i("123",mobile);
                        String password = data.getString("password");
                        Log.i("234",password);
                        String msg1=object.getString("msg");
                        if(code.equals("0")){
                            Log.i("qqqq","wijinaliale");
                           ip.fanhui(msg1,mobile,password);
                            Log.i("qqqq","我传过去了");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }
    };
    Map<String,String> map=new HashMap<>();
    @Override
    public void getdata(String phone, String pwd) {
        map.put("mobile",phone);
        map.put("password",pwd);
        String path="http://120.27.23.105/user/reg";
        Utils.doPost(path, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 String s=response.body().string();
                Log.i("qwe",s);
                Message message=new Message();
                message.what=0;
                message.obj=s;
                handler.sendMessage(message);
            }
        });
    }
}
