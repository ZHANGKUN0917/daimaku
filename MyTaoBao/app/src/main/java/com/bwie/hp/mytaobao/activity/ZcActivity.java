package com.bwie.hp.mytaobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.hp.mytaobao.R;
import com.bwie.hp.mytaobao.bean.User;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class ZcActivity extends AppCompatActivity {
   private Button b;
    private EditText et,et1,et2,et3;
   // private Map<String,String> map;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("asddsa",123456+"");
        setContentView(R.layout.activity_zc);
        Log.i("asddsa",123456+"");
        b= (Button) findViewById(R.id.bbb);
        et= (EditText) findViewById(R.id.et);
        et1= (EditText) findViewById(R.id.et1);
        et2= (EditText) findViewById(R.id.et2);
        et3= (EditText) findViewById(R.id.et3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*map=new HashMap<>();
                map.put("username",et.getText().toString());
                map.put("password",et1.getText().toString());
                map.put("password_confirm",et2.getText().toString());
                map.put("email",et3.getText().toString());
                map.put("client","android");*/
                getdata();
            }
        });

    }

    private void getdata() {
        //Log.i("qqqq",map.size()+"");
        String path = "http://169.254.222.214/mobile/index.php?act=login&op=register";
        RequestParams params=new RequestParams(path);
        params.addBodyParameter("username",et.getText().toString());
        params.addBodyParameter("password",et1.getText().toString());
        params.addBodyParameter("password_confirm",et2.getText().toString());
        params.addBodyParameter("email",et3.getText().toString());
        params.addBodyParameter("client","android");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("qweqwe",result);
                Gson gson = new Gson();
                User user = gson.fromJson(result, User.class);
                int code = user.getCode();
                User.DatasBean datas = user.getDatas();
                if (code == 200) {
                    Toast.makeText(ZcActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent it = new Intent(ZcActivity.this, DlActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }, 3000);
                } else if (code == 400) {
                    Toast.makeText(ZcActivity.this, datas.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
       /* OkHttp3Utils.doPost(path, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String ss = response.body().string();
                Log.i("qweqwe",ss);
                Gson gson = new Gson();
                User user = gson.fromJson(ss, User.class);
                int code = user.getCode();
                User.DatasBean datas = user.getDatas();
                if (code == 200) {
                    Toast.makeText(ZcActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent it = new Intent(ZcActivity.this, DlActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }, 3000);
                } else if (code == 400) {
                    Toast.makeText(ZcActivity.this, datas.getError(), Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}
