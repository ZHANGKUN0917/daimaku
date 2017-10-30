package test.bwie.com.dongjing.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/18 13 26
 */

public class UploadImodel implements UploadModel {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    try {
                        JSONObject object=new JSONObject((String)msg.obj);
                        String msg1 = object.getString("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }
    };
    @Override
    public void upload(File file, int uid) {
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
}
