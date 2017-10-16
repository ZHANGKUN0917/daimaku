package model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bwie.hp.lanjieqi.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import presenter.IPresenter2;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/14 10 38
 */

public class Model2 implements IModel2{
    private IPresenter2 ip2;
    public Model2(IPresenter2 ip2) {
        this.ip2 = ip2;
    }

    private Context con;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    try {
                        JSONObject ob=new JSONObject((String)msg.obj);
                        String msg1 = ob.getString("msg");
                        String code = ob.getString("code");
                        if(code.equals("0")){
                            ip2.setdata(msg1);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }


        }
    };
    private Handler handler1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    try {
                        JSONObject object=new JSONObject((String)msg.obj);
                        JSONObject data = object.getJSONObject("data");
                        String icon = data.getString("icon");
                        ip2.setdata1(icon);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }
    };
    @Override
    public void getdata(Context context,int uid, File file) {
        con=context;
        String path="http://120.27.23.105/file/upload?uid="+uid;
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建RequestBody 封装file参数
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        //创建RequestBody 设置类型等
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", file.getName(), fileBody).build();
        //创建Request
        final Request request = new Request.Builder().url(path).post(requestBody).build();
        //得到Call
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
                Log.i("qaz",s);
                Message message=new Message();
                message.what=0;
                message.obj=s;
                handler.sendMessage(message);
            }
        });

    }
    @Override
    public void getdata1(int uid) {
        final Map<String,String> map=new HashMap<>();
        map.put("uid",uid+"");
        String path="http://120.27.23.105/user/getUserInfo";
        Utils.doPost(path, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
               String s=response.body().string();
                Log.i("12345",s);
                Message me=new Message();
                me.what=0;
                me.obj=s;
                handler1.sendMessage(me);
            }
        });
        
    }

}
