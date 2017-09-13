package com.example.hp.jinritoutiao.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.jinritoutiao.R;
import com.example.hp.jinritoutiao.bean.Beann;
import com.example.hp.jinritoutiao.bean.Source;
import com.example.hp.jinritoutiao.fragment.Fragmenti;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.hp.jinritoutiao.R.mipmap.i;

public class MainActivity extends AppCompatActivity {
    private ViewPager vp;
    private TabLayout tab;
    private List<Source> tabs;
    private List<Fragment> views;
    private ImageView iv;
    private List<Beann> list;

    private FragmentManager manager;
    private ImageView ivv;
    private ImageView iv1;
    private ImageView iv2;

    private static final String TAG = "MainActivity";
    private static final String APP_ID = "222222";//官方获取的APPID
    public static Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private TextView tv;
    private ImageView ivvv;
    private LinearLayout layout;
    private LinearLayout layout1;
    private View view;
    private ImageView iv4;
    private Button button;
    private ImageView iviv;
    public static MainActivity instance;
    //默认是日间模式
    private int theme=R.style.AppTheme;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            theme=savedInstanceState.getInt("theme");
            setTheme(theme);
        }

        setContentView(R.layout.activity_main);
        instance=this;
        preferences = getSharedPreferences("user",MODE_PRIVATE);
        vp= ((ViewPager) findViewById(R.id.vp));
        tab= ((TabLayout) findViewById(R.id.tab));
        iv= ((ImageView) findViewById(R.id.iv));
        iviv= ((ImageView) findViewById(R.id.iviv));
        tabs=new ArrayList<Source>();
        views=new ArrayList<>();
        list=new ArrayList<Beann>();
        views=new ArrayList<>();
         iviv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent it=new Intent(MainActivity.this,PimdaoActivity.class);
                 startActivity(it);
             }
         });
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,MainActivity.this.getApplicationContext());
        Context context=MainActivity.this;
        Beann bean=new Beann(R.mipmap.f,"好友动态");
        Beann bean1=new Beann(R.mipmap.g,"我的话题");
        Beann bean2=new Beann(R.mipmap.h,"收藏");
        Beann bean3=new Beann(i,"活动");
        Beann bean4=new Beann(R.mipmap.j,"商城");
        Beann bean5=new Beann(R.mipmap.k,"反馈");
        list.add(bean);
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        view = View.inflate(this, R.layout.cehua, null);
        tv=view.findViewById(R.id.tv_tv);
        ivvv=view.findViewById(R.id.iv_iv);
        iv4=view.findViewById(R.id.iv4);
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this,SheZhiActivity.class);
                startActivityForResult(it,200);

            }
        });
        //处理侧滑
        final SlidingMenu slidingMenu=new SlidingMenu(this);
        //设置侧滑从那边滑出
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置策划宽度
        slidingMenu.setBehindOffset(200);
        //依附在activity
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //设置sliding布局
       slidingMenu.setMenu(view);
        tabs.add(new Source("推荐","http://ic.snssdk.com/2/article/v25/stream/?count=20&min_behot_time=1455521444&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455521401&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000"));
        tabs.add(new Source("国际","http://ic.snssdk.com/2/article/v25/stream/?category=news_world&count=20&min_behot_time=1455523059&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000"));
        tabs.add(new Source("军事","http://ic.snssdk.com/2/article/v25/stream/?category=news_military&count=20&min_behot_time=1455522991&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000"));
        tabs.add(new Source("视频","http://ic.snssdk.com/2/article/v25/stream/?category=video&count=20&min_behot_time=1455521349&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522107&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000"));
        tabs.add(new Source("科技","http://ic.snssdk.com/2/article/v25/stream/?category=news_tech&count=20&min_behot_time=1455522427&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000"));
        tabs.add(new Source("订阅","http://ic.snssdk.com/2/article/v25/stream/?category=news_entertainment&count=20&min_behot_time=1455522338&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000"));
        tabs.add(new Source("热点","http://ic.snssdk.com/2/article/v25/stream/?category=news_hot&count=20&min_behot_time=1455521166&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455521401&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_nme=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000"));

        for (int j = 0; j <tabs.size() ; j++) {
            tab.addTab(tab.newTab().setText(tabs.get(j).getName()));
            Fragmenti fragmenti= (Fragmenti) Fragmenti.newInstance(tabs.get(j));
            views.add(fragmenti);
        }
        //设置模式
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        manager = getSupportFragmentManager();
        MyBase base=new MyBase(manager);
        vp.setAdapter(base);
        //进行关联
        tab.setupWithViewPager(vp);
        tab.setTabsFromPagerAdapter(base);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 slidingMenu.toggle();
            }
        });
        //加载listview布局
        ListView lv= view.findViewById(R.id.lv);
        lv.setAdapter(new MyBase1());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 if(i==2){
                     Intent it=new Intent(MainActivity.this,shoucang.class);
                     startActivity(it);
                 }
            }
        });
        ivv = ((ImageView) view.findViewById(R.id.iv));
        iv1 = ((ImageView) view.findViewById(R.id.iv1));
        iv2 = ((ImageView) view.findViewById(R.id.iv2));
        ivv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this,pactivity.class);
                startActivity(it);
            }
        });
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(MainActivity.this,"all", mIUiListener);

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

    class MyBase extends FragmentPagerAdapter{
        @Override
        public CharSequence getPageTitle(int position) {

            return tabs.get(position).getName();
        }

        public MyBase(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return views.get(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }
    }
    class MyBase1 extends BaseAdapter{
       
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
            ViewHolder vh;
            if(view==null){
                Log.i("tag----","我进来了");
                vh=new ViewHolder();
                view=View.inflate(MainActivity.this,R.layout.listview,null);
                vh.tv=view.findViewById(R.id.tv);
                vh.iv=view.findViewById(R.id.iv);
                view.setTag(vh);
            }else
            {
                vh= (ViewHolder) view.getTag();
            }
            vh.iv.setImageResource(list.get(i).getTupian());
            vh.tv.setText(list.get(i).getText());
            return view;
        }
    }
    class ViewHolder{
        ImageView iv;
        TextView tv;
    }
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");

                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(final Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                        JSONObject obj= (JSONObject) response;
                        String name = obj.optString("nickname");
                        Log.e("name-------",name);
                        String qq_2 = obj.optString("figureurl_qq_2");
                        layout = view.findViewById(R.id.lll);
                        layout1=view.findViewById(R.id.ll);
                        layout1.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.GONE);
                        tv.setText(name);
                        //DisplayImageOptions options = utils.getOptions();
                        DisplayImageOptions options=new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(40)).build();
                        ImageLoader.getInstance().displayImage(qq_2,ivvv,options);
                        ImageLoader.getInstance().displayImage(qq_2,iv,options);
                    }


                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }


                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);

        }
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200&&resultCode==20){
            Toast.makeText(this, "我是你爸爸", Toast.LENGTH_SHORT).show();
            if(preferences.getBoolean("istrue",false)){
                theme=R.style.NightAppTheme;
                recreate();
            }else{
                theme=R.style.AppTheme;
                recreate();
            }
        }
    }
    //QQ分享
    public void share(String title,String url)
    {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "要分享的摘要");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  url);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "zk的app");
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  0);//0代表QQ好友 1代表QQ空间
        mTencent.shareToQQ(MainActivity.this, params, new BaseUiListener());
    }

}
