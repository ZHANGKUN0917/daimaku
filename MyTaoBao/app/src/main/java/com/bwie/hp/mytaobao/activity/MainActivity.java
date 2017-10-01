package com.bwie.hp.mytaobao.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.hp.mytaobao.R;

public class MainActivity extends AppCompatActivity {
   private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(it);
            }
        },3000);
    }
}
