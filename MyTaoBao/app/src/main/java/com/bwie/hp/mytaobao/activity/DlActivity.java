package com.bwie.hp.mytaobao.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.hp.mytaobao.R;
import com.bwie.hp.mytaobao.bean.User;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class DlActivity extends AppCompatActivity {
    private Button btt,b,b1,button;
    private ImageView iv;
    private EditText et,et1;
    //private Map<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dl);
        btt= (Button) findViewById(R.id.btt);
        b= (Button) findViewById(R.id.b);
        b1= (Button) findViewById(R.id.b1);
        et= (EditText) findViewById(R.id.et);
        et1= (EditText) findViewById(R.id.et1);
        button= (Button) findViewById(R.id.button);
        iv= (ImageView) findViewById(R.id.image);
        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(DlActivity.this,ZcActivity.class);
                startActivity(it);
            }
        });


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*map=new HashMap<>();
                map.put("username",et.getText().toString());
                map.put("password",et1.getText().toString());
                map.put("client","android");*/
                getpost();
            }
        });

    }

    private void getpost() {
        String path="http://169.254.222.214/mobile/index.php?act=login";
        RequestParams params=new RequestParams(path);
        params.addBodyParameter("username",et.getText().toString());
        params.addBodyParameter("password",et1.getText().toString());
        params.addBodyParameter("client","android");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                User user = gson.fromJson(result, User.class);
                int code = user.getCode();
                User.DatasBean datas = user.getDatas();
                if(code==200){
                    Toast.makeText(DlActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("name", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("istrue",true);
                    edit.commit();
                    finish();

                }else if(code==400){
                    Toast.makeText(DlActivity.this, datas.getError(), Toast.LENGTH_SHORT).show();
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
        /*OkHttp3Utils.doPost(path, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });*/
    }
}
