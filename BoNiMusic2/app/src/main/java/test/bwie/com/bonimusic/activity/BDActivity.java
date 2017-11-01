package test.bwie.com.bonimusic.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import test.bwie.com.bonimusic.R;
import test.bwie.com.bonimusic.bean.IBean;
import test.bwie.com.bonimusic.bean.Jd;
import test.bwie.com.bonimusic.bean.Lj;
import test.bwie.com.bonimusic.service.MyService;

public class BDActivity extends AppCompatActivity {
    private ListView lv;
    private List<SmBean> list;
    private ImageView ivv,ivv1,ivv3;
    private CheckBox ivv2;
    private static SeekBar skb;
    private int i1;
    private int temp;

    @Override
    protected void onResume() {
        super.onResume();
        if(MyService.mediaPlayer.isPlaying()){
            ivv2.setChecked(true);
        }else{
            ivv2.setChecked(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);
        Intent intent = getIntent();
        SmBean sb1 = (SmBean) intent.getSerializableExtra("dx");
        Log.i("123",sb1+"");
        String album1 = intent.getStringExtra("album");
        int duration1 = intent.getIntExtra("duration", 0);
        Bitmap bitmap=BitmapFactory.decodeFile(album1);
        EventBus.getDefault().register(this);
        lv = (ListView) findViewById(R.id.lv);
        ivv= (ImageView) findViewById(R.id.ivv);
        if(bitmap!=null){
            ivv.setImageBitmap(bitmap);
        }else{
            ivv.setImageResource(R.mipmap.ic_launcher);
        }
        ivv1= (ImageView) findViewById(R.id.ivv1);
        ivv2= (CheckBox) findViewById(R.id.ivv2);
        ivv3= (ImageView) findViewById(R.id.ivv3);
        skb= (SeekBar) findViewById(R.id.skb);
        list = new ArrayList<>();
        Log.i("1234",list.size()+"");
        //创建一个扫描游标
        Cursor c = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (c != null) {//创建Model对象
            SmBean sb;
            //循环读取
            //实例化Model对象
            while (c.moveToNext()) {
                sb = new SmBean();
                //扫描本地文件，得到歌曲的相关信息
                String music_name = c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String music_singer = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String path = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));//文件路径
                int duration = c.getInt(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                Log.i("url",path);
                String title = c.getString((c.getColumnIndex(MediaStore.Audio.Media.TITLE)));//音乐标题
                int size = c.getInt(c.getColumnIndex(MediaStore.Audio.Media.SIZE));  //文件大小
                String album = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ALBUM)); //唱片图片
                //设置值到Model的封装类中
                sb.setName(music_name);
                sb.setAuthor(music_singer);
                sb.setUrl(path);
                Log.i("utlll",path);
                sb.setDuration(duration);
                sb.setTitle(title);
                sb.setAlbum(album);
                sb.setSize(size);
                list.add(sb);
            }
            //打印出数组的长度
            System.out.println(list.size());
            MyBase1 base=new MyBase1();
            lv.setAdapter(base);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    i1=i;
                    EventBus.getDefault().post(new IBean(i1));
                    //listen.list(i1);
                    Log.i("1221",i1+"");
                    Log.i("qww1q",list.get(i).getName());
                    Bitmap bitmap=BitmapFactory.decodeFile(list.get(i).getAlbum());
                    EventBus.getDefault().post(new Lj(list.get(i).getUrl()));
                    ivv2.setChecked(true);
                    Log.i("qwa",list.get(i).getUrl());
                    if(bitmap!=null){
                        ivv.setImageBitmap(bitmap);
                    }
                }});
            ivv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("qwe","我进来了");
                    Intent it=new Intent(BDActivity.this,ZhuanjiActivity.class);
                    it.putExtra("list", (Serializable) list);
                    it.putExtra("position",i1);
                    startActivity(it);
                }
            });
           ivv2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                   if(b){
                       HomeActivity.binder.bindplay();
                   }else{
                       HomeActivity.binder.pauseplay();
                   }
               }
           });
            skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if(MyService.mediaPlayer!=null&&b){
                        HomeActivity.binder.bindseek(seekBar.getProgress());
                    }
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            ivv3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    i1++;
                    Log.i("123",i1+"");
                    i1=i1%list.size();
                    String url = list.get(i1).getUrl();
                    EventBus.getDefault().post(new Lj(list.get(i1).getUrl()));
                }
            });
        }
        MyService.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                i1++;
                Log.i("123",i1+"");
                i1=i1%list.size();
                String url = list.get(i1).getUrl();
                EventBus.getDefault().post(new Lj(list.get(i1).getUrl()));
            }
        });
       // listen.list(i1);

    }
    public listener listen;
    public interface listener {
        void list(int posi);
    }
    public void setListen(listener listen) {
        this.listen = listen;
    }

    @Subscribe
    public void ss(Jd jd){
        Log.i("qaq",jd.getProgress()+"");
        temp = jd.getProgress();
        skb.setProgress(temp);
    }
    class MyBase1 extends BaseAdapter {
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
                vh=new ViewHolder();
                view=View.inflate(BDActivity.this,R.layout.bendi,null);
                vh.iv=view.findViewById(R.id.iv);
                vh.tv=view.findViewById(R.id.tv);
                vh.tv1=view.findViewById(R.id.tv2);
                view.setTag(vh);
            }else{
                vh= (ViewHolder) view.getTag();
            }
            String album = list.get(i).getAlbum();
            Bitmap bitmap= BitmapFactory.decodeFile(album);
            vh.iv.setImageBitmap(bitmap);
            vh.tv.setText(list.get(i).getName());
            Log.i("123",list.get(i).getName());
            vh.tv1.setText(list.get(i).getAuthor());

            return view;
        }

    }
    class ViewHolder{
        ImageView iv;
        TextView tv,tv1;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
