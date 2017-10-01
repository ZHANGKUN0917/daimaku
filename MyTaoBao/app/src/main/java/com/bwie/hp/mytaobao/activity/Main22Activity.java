package com.bwie.hp.mytaobao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.hp.mytaobao.R;
import com.bwie.hp.mytaobao.adpter.MyBase;
import com.bwie.hp.mytaobao.bean.Bean2;
import com.bwie.test.okhttp3library.OkHttp3Utils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main22Activity extends AppCompatActivity {
  private RecyclerView rv;
    private String path="https://m.yunifang.com/yunifang/mobile/goods/detail?id=85";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        getdata();
    }

    private void getdata() {
        OkHttp3Utils.doGet(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 String s=response.body().string();
                Gson gson=new Gson();
                final Bean2 bean2 = gson.fromJson(s, Bean2.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyBase base=new MyBase(Main22Activity.this,bean2);
                        rv.setAdapter(base);
                    }
                });
            }
        });
    }
}
