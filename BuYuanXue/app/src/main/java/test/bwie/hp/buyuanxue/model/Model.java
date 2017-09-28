package test.bwie.hp.buyuanxue.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.bwie.hp.lanjieqi.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.hp.buyuanxue.presenter.IPresenter;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/28 15 26
 */

public class Model implements IModel {
    private Map<String,String> map;
    private Map<String,String> map1;
    private Context con;
    IPresenter ip;

    public Model(IPresenter ip) {
        this.ip = ip;
    }

    private Handler handler1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    JSONObject object= null;
                    try {
                        object = new JSONObject((String)msg.obj);
                        String info=object.getString("msg");
                        ip.setdata1(info);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                   
            }
        }
    };
    private  Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    try {
                        JSONObject object=new JSONObject((String)msg.obj);
                        String info=object.getString("msg");
                        Toast.makeText(con, info, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


            }
        }
    };

    @Override
    public void getdata(String phone1, Context context) {
        con=context;
        map=new HashMap<>();
        map.put("mobile",phone1);
        String path="http://lexue365.51dangao.cn/api/user/sendsms";
        Utils.doPost(path, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  String s=response.body().string();
                  Log.i("asd",s);
                Message message=new Message();
                message.what=0;
                message.obj=s;
                handler.sendMessage(message);
            }
        });

    }
    public void getdata1(String phone2,String code){
        String path1="http://lexue365.51dangao.cn/api/user/auth";
        map1=new HashMap<>();
        map1.put("mobile",phone2);
        map1.put("code",code);
        map1.put("clt_id","1");
        Utils.doPost(path1, map1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 String s1=response.body().string();
                Log.i("zxccxz",s1);
                Message message=new Message();
                message.what=0;
                message.obj=s1;
                handler1.sendMessage(message);
            }
        });
    }
}
