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
import test.bwie.com.dongjing.presenter.UserInfo;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/18 14 22
 */

public class InfoModel implements InfoIModel {
    private UserInfo ui;

    public InfoModel(UserInfo ui) {
        this.ui = ui;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    try {
                        JSONObject object=new JSONObject((String)msg.obj);
                        JSONObject data = object.getJSONObject("data");
                        String code = object.getString("code");
                        String icon = data.getString("icon");
                        String nickname = data.getString("nickname");
                        String username=data.getString("username");
                        if(code.equals("0")){
                           ui.info(icon,username,nickname);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }
    };
    Map<String,String> map=new HashMap<>();
    @Override
    public void Info(int uid) {
        map.put("uid",uid+"");
        HttpUtils.doPost(JieKou.info, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
                Message message=new Message();
                message.what=0;
                message.obj=s;
                handler.sendMessage(message);
                Log.i("111",s);
            }
        });
    }
}
