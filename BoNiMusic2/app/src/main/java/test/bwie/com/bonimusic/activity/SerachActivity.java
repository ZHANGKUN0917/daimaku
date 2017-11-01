package test.bwie.com.bonimusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.hp.lanjieqi.Utils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bwie.com.bonimusic.R;
import test.bwie.com.bonimusic.bean.Bean1;
import test.bwie.com.bonimusic.bean.Lj;

public class SerachActivity extends AppCompatActivity {
    private EditText et;
    private Button b;
    private ListView lv;
    private Handler handler1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    try {
                        JSONObject object=new JSONObject((String)msg.obj);
                        JSONObject data = object.getJSONObject("data");
                        String album= data.getString("img");
                        int size=data.getInt("fileSize");
                        int duration = data.getInt("timelength");
                        String url = data.getString("play_url");
                        Log.i("111",url);
                        String author = data.getString("author_name");
                        String name = data.getString("audio_name");
                        String title = data.getString("album_name");
                        String lyrics = data.getString("lyrics");
                        SmBean sb=new SmBean();
                        sb.setDuration(duration);
                        sb.setSize(size);
                        sb.setName(name);
                        sb.setUrl(url);
                        sb.setTitle(title);
                        sb.setAuthor(author);
                        sb.setAlbum(album);
                        EventBus.getDefault().post(new Lj(url));
                        Intent it=new Intent(SerachActivity.this,BDActivity.class);
                        it.putExtra("dx",sb);
                        it.putExtra("album",album);
                        it.putExtra("duration",duration);
                        startActivity(it);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }
    };
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Gson gson=new Gson();
                    Bean1 bean1 = gson.fromJson((String)msg.obj, Bean1.class);
                    info = bean1.getData().getInfo();
                    Log.i("123",info+"");
                    MyBase base=new MyBase();
                    lv.setAdapter(base);
            }
        }
    };
    private List<Bean1.DataBean.InfoBean> info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach);
        et= (EditText) findViewById(R.id.et);
        b= (Button) findViewById(R.id.su);
        lv= (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 String path="http://www.kugou.com/yy/index.php?r=play/getdata&hash="+info.get(i).getHash()+"";
                Utils.doGet(path, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                       String s=response.body().string();
                        Log.i("12",s);
                        Message message=new Message();
                        message.what=0;
                        message.obj=s;
                        handler1.sendMessage(message);
                    }
                });
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=et.getText().toString();
                String path="http://mobilecdn.kugou.com/api/v3/search/song?format=json&keyword="+s+"&page=1&pagesize=20&showtype=1";
                Utils.doGet(path, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                         String s=response.body().string();
                         Message message=new Message();
                         message.what=0;
                         message.obj=s;
                         handler.sendMessage(message);
                         Log.i("aq12",s);
                    }
                });
            }
        });
    }
    class MyBase extends BaseAdapter{

        @Override
        public int getCount() {
            return info.size();
        }

        @Override
        public Object getItem(int i) {
            return info.get(i);
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
                view=View.inflate(SerachActivity.this,R.layout.singerlist,null);
                vh.tv=view.findViewById(R.id.tv);
                vh.tv1=view.findViewById(R.id.tv1);
                view.setTag(view);
            }else{
                vh= (ViewHolder) view.getTag();
            }
            vh.tv.setText(info.get(i).getSongname());
            vh.tv1.setText(info.get(i).getSingername());
           /* SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("hash",info.get(i).getHash());
            edit.commit();*/
            return view;
        }
        class ViewHolder{
            TextView tv,tv1;
        }
    }


}
