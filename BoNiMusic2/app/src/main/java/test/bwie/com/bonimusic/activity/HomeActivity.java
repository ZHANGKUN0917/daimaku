package test.bwie.com.bonimusic.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.bonimusic.R;
import test.bwie.com.bonimusic.fragment.FragmentA;
import test.bwie.com.bonimusic.fragment.FragmentB;
import test.bwie.com.bonimusic.fragment.FragmentC;
import test.bwie.com.bonimusic.service.MyService;

public class HomeActivity extends AppCompatActivity {
    private ViewPager pager;
    private ImageView iv,iv1,iv2,iv3;
    private ListView lv;
    private TextView tv;
    private List<Bean> list;
    private List<Fragment> list1;
    private ServiceConnection serviceConnection;
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;
    public static MyService.MyBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent it=new Intent(this, MyService.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                binder= (MyService.MyBinder) iBinder;

            }
            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        bindService(it, serviceConnection,BIND_AUTO_CREATE);
        lv = (ListView) findViewById(R.id.lv);
        iv = (ImageView) findViewById(R.id.ivv);
        iv1 = (ImageView) findViewById(R.id.img);
        iv2 = (ImageView) findViewById(R.id.img1);
        iv3 = (ImageView) findViewById(R.id.img2);
        tv= (TextView) findViewById(R.id.tv);
        list = new ArrayList<>();
        pager= (ViewPager) findViewById(R.id.pager);
        Bean bean = new Bean(R.mipmap.ah5, "我的消息");
        Bean bean1 = new Bean(R.mipmap.aha, "积分商城");
        Bean bean2 = new Bean(R.mipmap.ahd, "会员中心");
        Bean bean3 = new Bean(R.mipmap.ah1, "在线听歌免流量");
        Bean bean4 = new Bean(R.mipmap.ah3, "听歌识曲");
        Bean bean5 = new Bean(R.mipmap.ah_, "主题换肤");
        Bean bean6 = new Bean(R.mipmap.ah5, "夜间模式");
        Bean bean7 = new Bean(R.mipmap.ahb, "定时停止播放");
        Bean bean8 = new Bean(R.mipmap.agx, "音乐闹钟");
        Bean bean9 = new Bean(R.mipmap.ahc, "驾驶模式");
        list.add(bean);
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
        list.add(bean7);
        list.add(bean8);
        list.add(bean9);
        fragmentA=new FragmentA();
        fragmentB=new FragmentB();
        fragmentC=new FragmentC();
        list1=new ArrayList<>();
        list1.add(fragmentA);
        list1.add(fragmentB);
        list1.add(fragmentC);
        MyAdpter adpter = new MyAdpter();
        lv.setAdapter(adpter);
        FragmentManager manager = getSupportFragmentManager();
        MyBase base=new MyBase(manager);
        pager.setAdapter(base);
       pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        pager.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        pager.requestDisallowInterceptTouchEvent(false);

                    default:
                        break;
                }
                return true;
            }
        });
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(0);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(1);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(2);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent it=new Intent(HomeActivity.this,SerachActivity.class);
                startActivity(it);
            }
        });
    }
    class MyAdpter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view=View.inflate(HomeActivity.this,R.layout.item,null);
            ImageView iv=view.findViewById(R.id.iv);
            TextView tv=view.findViewById(R.id.tv);
            iv.setImageResource(list.get(i).getTp());
            tv.setText(list.get(i).getInfo());
            return view;
        }
    }
    class MyBase extends FragmentPagerAdapter{

        public MyBase(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list1.get(position);
        }

        @Override
        public int getCount() {
            return list1.size();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
