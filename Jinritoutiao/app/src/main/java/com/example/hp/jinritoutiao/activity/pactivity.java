package com.example.hp.jinritoutiao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.hp.jinritoutiao.R;

public class pactivity extends AppCompatActivity {
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pactivity);
        iv= ((ImageView) findViewById(R.id.iv));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(pactivity.this,MainActivity.class);
                startActivity(it);
            }
        });

    }
}
