package com.example.hp.jinritoutiao.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hp.jinritoutiao.R;

import static com.example.hp.jinritoutiao.R.id.bbb;

public class SheZhiActivity extends AppCompatActivity {
    //默认是日间模式
    private int theme=R.style.AppTheme;
    private CheckBox cb;
    private SharedPreferences preferences;
    private SharedPreferences.Editor edit;
    private Button b;
    private ImageView iv,iv1;
    public static int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //判断是否有存储的模式
        if(savedInstanceState!=null){
            theme=savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_she_zhi);
        super.onCreate(savedInstanceState);
        cb= ((CheckBox) findViewById(R.id.bb));
        b= ((Button) findViewById(bbb));
        preferences = getSharedPreferences("user",MODE_PRIVATE);
        if(preferences.getBoolean("istrue",false)){
            cb.setChecked(true);
            if(i==0){
                theme=R.style.NightAppTheme;
                recreate();
                i=1;
            }else if(i==1){
                theme=R.style.NightAppTheme;
                recreate();
                i=2;
            }
        }
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edit = preferences.edit();
                edit.putBoolean("istrue",cb.isChecked());
                edit.commit();
                if(cb.isChecked()){
                     theme=R.style.NightAppTheme;
                     recreate();
                }else{
                    theme = R.style.AppTheme;
                    recreate();
                }
            }
        });

        b.setOnClickListener(new View.OnClickListener() {

            private View layout1;
            private View layout;

            @Override
            public void onClick(View view) {
                MainActivity.mTencent.logout(MainActivity.instance);
                Toast.makeText(SheZhiActivity.this, "我要出去了", Toast.LENGTH_SHORT).show();
                View vieww=View.inflate(SheZhiActivity.this,R.layout.cehua,null);
                View view1=View.inflate(SheZhiActivity.this,R.layout.activity_main,null);
                layout = vieww.findViewById(R.id.ll);
                layout1 = vieww.findViewById(R.id.lll);
                layout.setVisibility(View.GONE);
                layout1.setVisibility(View.VISIBLE);
                iv=view1.findViewById(R.id.iv);
                iv.setImageResource(R.mipmap.a);
            }
        });
        iv1= ((ImageView) findViewById(R.id.iv));
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=1;
                setResult(20);
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme",theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme=savedInstanceState.getInt("theme");
    }
    }

