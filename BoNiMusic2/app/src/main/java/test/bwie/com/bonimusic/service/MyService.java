package test.bwie.com.bonimusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import test.bwie.com.bonimusic.bean.Jd;
import test.bwie.com.bonimusic.bean.Lj;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/11 22 18
 */

public class MyService extends Service {
    public static MediaPlayer mediaPlayer;
    private int duration;
    private String path;
    Timer timer=new Timer();
    private MyTask task;
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        mediaPlayer=new MediaPlayer();
        task=new MyTask();
    }
    @Subscribe
    public void lj(Lj lj){
        path=lj.getLj();
        Log.i("qwe",path);
        initplay(path);
    }
    private void initplay(String path) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
            duration=mediaPlayer.getDuration();
                timer.schedule(task,0,1000);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //播放
   private void play(){
        mediaPlayer.start();
    }
    //暂停u
    private void pause(){
        mediaPlayer.pause();
    }
    private void seekto(int progress){
        int temp=progress*duration/100;
        mediaPlayer.seekTo(temp);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        MyBinder binder=new MyBinder();
        return binder;
    }
   public class MyBinder extends Binder{
        public void bindplay(){
            play();
        }
        public void pauseplay(){
            pause();
        }
        public void bindseek(int progress){
            seekto(progress);
        }
    }
    class MyTask extends TimerTask{
        @Override
        public void run() {
            //获取当前进度
            int currentposition=mediaPlayer.getCurrentPosition();
            //算当前progress的进度
            int temp1=currentposition*100/duration;
            Log.i("xxx", "run: "+temp1);
            EventBus.getDefault().post(new Jd(temp1));
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

}
