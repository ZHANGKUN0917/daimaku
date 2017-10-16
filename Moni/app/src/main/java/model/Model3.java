package model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bwie.hp.lanjieqi.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import presenter.IPresenter3;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/15 11 06
 */

public class Model3 implements IModel3 {
    private IPresenter3 ip3;

    public Model3(IPresenter3 ip3) {
        this.ip3 = ip3;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    try {
                        JSONObject object=new JSONObject((String)msg.obj);
                        JSONArray data = object.getJSONArray("data");
                        Log.i("123321",data+"");
                        ip3.setdata(data);
                        Log.i("234","我过来了");
                        Log.i("12", data+"");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }
    };
    private Map<String,String> map=new HashMap<>();
    @Override
    public void getdata(String name,int page){
        map.put("keywords",name);
        map.put("page",page+"");
        map.put("sort",0+"");
        String path="http://120.27.23.105/product/searchProducts";
        Utils.doPost(path, map, new Callback() {
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
                Log.i("123",s);
            }
        });

    }
}
