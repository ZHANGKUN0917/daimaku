package test.bwie.hp.buyuanxue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private ViewPager vp;
    private List<Integer> list;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp= (ViewPager) findViewById(R.id.vp);
        list=new ArrayList<>();
        list.add(R.mipmap.welcom1);
        list.add(R.mipmap.welcom2);
        list.add(R.mipmap.welcom3);
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        boolean b = sp.getBoolean("istrue", true);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("istrue",false);
        edit.commit();
        if(b){
            MyBase base=new MyBase();
            vp.setAdapter(base);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent it=new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(it);
                }
            },5000);
        }else{
           handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   Intent it=new Intent(MainActivity.this,SecondActivity.class);
                   startActivity(it);
               }
           },3000);

        }
    }
    class MyBase extends PagerAdapter{
        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv=new ImageView(MainActivity.this);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(list.get(position));
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
