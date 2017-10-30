package test.bwie.com.dongjing.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bwie.okhttputils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.dongjing.api.JieKou;
import test.bwie.com.dongjing.bean.FenLei;
import test.bwie.com.dongjing.bean.FenLei1;
import test.bwie.com.dongjing.presenter.IPresenter;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/17 19 51
 */

public class Model implements IModel {
   private Handler handler1=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           switch (msg.what){
               case 0:
                   try {
                       JSONObject object=new JSONObject((String)msg.obj);
                       JSONObject data = object.getJSONObject("data");
                       int uid = data.getInt("uid");
                       String code = object.getString("code");
                       String msg1 = object.getString("msg");
                       if(code.equals("0")){
                           ip.login(msg1,uid);
                       }
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
                }
            }
   };
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    try {
                        JSONObject object=new JSONObject((String)msg.obj);
                        String code = object.getString("code");
                        String msg1=object.getString("msg");
                        JSONObject data = object.getJSONObject("data");
                        String mobile = data.getString("mobile");
                        String password = data.getString("password");
                        if(code.equals("0")){
                             ip.gRegister(msg1,mobile,password);
                       }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }
    };
    private List<FenLei.DataBean> list;
    private IPresenter ip;
    private Map<String,String> map=new HashMap<>();
    private Map<String,String> map1=new HashMap<>();
    private Map<String,String> map2=new HashMap<>();
    public Model(IPresenter ip) {
        this.ip = ip;
    }
  //第一次分类
    @Override
    public void Fgetdata() {
        HttpUtils.doGet(JieKou.fenlei, new HttpUtils.GsonObjectCallback<FenLei>() {
            @Override
            public void onUi(FenLei fenLei) {
                Log.i("123",fenLei+"");
                ip.gview(fenLei);
            }
            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }
    //第二次分类
    @Override
    public void Fgetdata1(int position,int cid) {
        map.put("cid",cid+"");
        HttpUtils.doPost(JieKou.fenlei1, map, new HttpUtils.GsonObjectCallback<FenLei1>() {
            @Override
            public void onUi(FenLei1 fenLei1) {
                ip.gview1(fenLei1);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
    //注册
    @Override
    public void RegisterData(String phone, String pwd) {
        map1.put("mobile",phone);
        map1.put("password",pwd);
        HttpUtils.doPost(JieKou.register, map1, new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {

          }
            @Override
          public void onResponse(Call call, Response response) throws IOException {
              String s=response.body().string();
              Log.i("123",s);
                Message message=new Message();
                message.what=0;
                message.obj=s;
                handler.sendMessage(message);
          }
      });
    }
   //登录
    @Override
    public void LoginData(String phone, String pwd) {
        map2.put("mobile",phone);
        map2.put("password",pwd);
        HttpUtils.doPost(JieKou.login, map2, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String ss=response.body().string();
                Message message=new Message();
                Log.i("qa",ss);
                message.what=0;
                message.obj=ss;
                handler1.sendMessage(message);
            }
        });
    }


}
