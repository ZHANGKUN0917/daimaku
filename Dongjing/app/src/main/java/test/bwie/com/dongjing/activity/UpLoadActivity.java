package test.bwie.com.dongjing.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.presenter.PresenterLoad;
import test.bwie.com.dongjing.presenter.UserInfo;
import test.bwie.com.dongjing.view.InfoView;
import test.bwie.com.dongjing.view.UploadIView;

public class UpLoadActivity extends AppCompatActivity implements UploadIView,InfoView {
    private ImageView iv;
    private TextView tv,tv1;
    private Button b;
    private File file;
    private Uri uri;
    private int uid;
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        uid = sp.getInt("uid", 0);
        UserInfo ui=new UserInfo(this);
        ui.info(uid);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load);
        iv= (ImageView) findViewById(R.id.iv);
        tv= (TextView) findViewById(R.id.username);
        tv1= (TextView) findViewById(R.id.nicheng);
        b= (Button) findViewById(R.id.bb);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                take_photo();

            }
        });
    }
    public void take_photo() {
        //创建File对象，用于存储拍照后的图片
        file = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
        uri = Uri.fromFile(file);
        //启动相机程序
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 300);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 300) {
            Log.i("xxx222", file+"");
            //    load();
            PresenterLoad pl = new PresenterLoad(UpLoadActivity.this);
            Log.i("qqq",uid+"");
            pl.upload(file, uid);

        }

    }

    @Override
    public void info(String icon, String name, String nick) {
        Log.i("222",icon+name+nick);
        Bitmap bitmap= BitmapFactory.decodeFile(icon);
        if(bitmap!=null){
            iv.setImageBitmap(bitmap);
        }else{
            iv.setImageResource(R.mipmap.ic_launcher);
        }
        if(name!=null){
            tv1.setText(name);
        }else{
            tv1.setText("无姓名");
        }
        if(nick!=null){
            tv.setText(nick);
        }else{
            tv.setText("无昵称");
        }

    }
}
