package com.example.hp.jinritoutiao.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hp.jinritoutiao.R;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private List<View> views;
    private ViewPager vp;
    private Button b;
    private SharedPreferences preferences;
    private SharedPreferences.Editor edit;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        views=new ArrayList<>();
        vp= ((ViewPager) findViewById(R.id.vp));

        preferences = getSharedPreferences("user",MODE_PRIVATE);
        Boolean bb=preferences.getBoolean("istrue",true);
        edit = preferences.edit();
        edit.putBoolean("istrue",false);
        edit.commit();
        View view=View.inflate(SecondActivity.this,R.layout.view,null);
        View view1=View.inflate(SecondActivity.this,R.layout.view1,null);
        View view2=View.inflate(SecondActivity.this,R.layout.view2,null);
        b= ((Button) view2.findViewById(R.id.b));
        views.add(view);
        views.add(view1);
        views.add(view2);
        vp.setAdapter(new MyBase());
        if(bb){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it=new Intent(SecondActivity.this,MainActivity.class);
                    startActivity(it);
                }
            });
        }else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent it=new Intent(SecondActivity.this,MainActivity.class);
                    startActivity(it);
                }
            },3000);

        }
    }
    class MyBase extends PagerAdapter{

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=views.get(position);
            container.addView(view);
            return  view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
