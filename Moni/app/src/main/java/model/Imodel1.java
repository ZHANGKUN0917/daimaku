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
import presenter.Ipresenter1;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/14 09 36
 */

public class Imodel1 implements model1{
    private Ipresenter1 ip1;

    public Imodel1(Ipresenter1 ip1) {
        this.ip1 = ip1;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    JSONObject object= null;
                    try {
                        object = new JSONObject((String)msg.obj);
                        String code = object.getString("code");
                        JSONObject data = object.getJSONObject("data");
                        int uid = data.getInt("uid");
                        String username = data.getString("username");
                        String msg1 = object.getString("msg");
                        if(code.equals("0")){
                            ip1.setdata(msg1,uid,username);
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
        String path="http://120.27.23.105/user/login";
        Utils.doPost(path, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String r=response.body().string();
                Log.i("1234",r);
                Message message=new Message();
                message.what=0;
                message.obj=r;
                handler.sendMessage(message);
            }
        });
    }
}
