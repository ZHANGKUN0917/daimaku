package test.bwie.com.bonimusic.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import test.bwie.com.bonimusic.R;
import test.bwie.com.bonimusic.bean.Jd;
import test.bwie.com.bonimusic.bean.Lj;
import test.bwie.com.bonimusic.service.MyService;

import static test.bwie.com.bonimusic.R.id.ivv2;

public class ZhuanjiActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv,tvTotalTime,pm;
    public Toolbar tb;
    public static SeekBar skb;
    private DiscView div;
    private ImageView ivv1,ivv3;
    private CheckBox cb;
    private List<SmBean> list1;
    private int temp;
    private int position;

    @Override
    protected void onResume() {
        super.onResume();
        if(MyService.mediaPlayer.isPlaying()){
            cb.setChecked(true);
            div.playAnimator();
        }else{
            cb.setChecked(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanji);
        Intent intent = getIntent();
        EventBus.getDefault().register(this);
        list1 = (List<SmBean>)intent.getSerializableExtra("list");
        int pp = intent.getIntExtra("position",0);
        position=pp;
        Log.i("12345",position+"");
        tv= (TextView) findViewById(R.id.tv);
        div= (DiscView) findViewById(R.id.discview);
        tvTotalTime= (TextView) findViewById(R.id.tvTotalTime);
        pm= (TextView) findViewById(R.id.pm);
        skb= (SeekBar) findViewById(R.id.musicSeekBar);
        ivv1= (ImageView) findViewById(R.id.ivv1);
        cb= (CheckBox) findViewById(ivv2);
        ivv3= (ImageView) findViewById(R.id.ivv3);
        div.setMusicDataList(this.list1);
        ivv1.setOnClickListener(this);
        //改变播放和暂停
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    HomeActivity.binder.bindplay();
                    div.playAnimator();
                }else{
                    HomeActivity.binder.pauseplay();
                    div.pauseAnimator();
                }
            }
        });
        ivv3.setOnClickListener(this);
        /*BDActivity bd=new BDActivity();
        bd.setListen(new BDActivity.listener() {
            @Override
            public void list(int posi) {
                ZhuanjiActivity.this.position =posi;
                Log.i("qwe", ZhuanjiActivity.this.position +"");
            }
        });*/
    }
    //传过进度条的进度
    @Subscribe
    public void onEvent(Jd jd){
        Log.i("qa1111",jd.getProgress()+"");
        temp = jd.getProgress();
        skb.setProgress(temp);
    }
   /* @Subscribe
    public void onon(IBean ib){
        Log.i("1111",ib.getI()+"");
    }*/
    //上一首下一首的监听
    @Override
    public void onClick(View v){
      if (v == ivv3){
          div.next();
          position++;
          if(position>=list1.size()){
              position=1;
          }
          Log.i("qa",position+"");
          String url=list1.get(position).getUrl();
          EventBus.getDefault().post(new Lj(url));
        } else if (v == ivv1){
            div.last();
          if(position<=0){
              position=list1.size();
          }
          position--;
          Log.i("12",position+"");
          String url=list1.get(position).getUrl();
          EventBus.getDefault().post(new Lj(url));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
