package test.bwie.com.moni.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import presenter.Presenter2;
import test.bwie.com.moni.R;
import view.IView2;

public class PersonActivity extends AppCompatActivity implements IView2 {
   private ImageView iv;
    private EditText et,et1;
    private Button b,b1;
    private File file;
    private int uid;
    private int page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        int uid = sp.getInt("uid", 1);
        Presenter2 p2=new Presenter2(PersonActivity.this);
        p2.setdata1(this,uid);
        iv= (ImageView) findViewById(R.id.iv);
        et= (EditText) findViewById(R.id.et);
        et1= (EditText) findViewById(R.id.et1);
        b= (Button) findViewById(R.id.tclogin);
        b1= (Button) findViewById(R.id.good);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        et1.setText(username);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv;
                View inflate = View.inflate(PersonActivity.this, R.layout.alertview, null);
                AlertDialog.Builder builder=new AlertDialog.Builder(PersonActivity.this);
                builder.setTitle("选择照片");
                builder.setView(inflate);
                builder.show();
                tv=inflate.findViewById(R.id.tv1);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openic();
                    }
                });
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(PersonActivity.this,GoodActivity.class);
                startActivity(it);
            }
        });
    }
    private void openic() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 200);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            getPicData(data);
        }
    }
    private void getPicData(Intent data) {
        //获取图片
        if (data != null) {
            // Bitmap bitmap =data.getParcelableExtra("data");
            Uri uri = data.getData();
            Bitmap bitmap = getBitmap(uri);
            iv.setImageBitmap(bitmap);
            file = getFile(bitmap);
            SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
            uid = sp.getInt("uid", 123);
            Log.i("wawa",file+"");
            Presenter2 p2=new Presenter2(PersonActivity.this);
             p2.setdata(PersonActivity.this, uid,file);
        }
    }
    //把bitmap转换成file
    private File getFile(Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory(), "a.jpg");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    //把uri转换成Bitmap
    private Bitmap getBitmap(Uri uri) {
        try {
            //把uri转换成Bitmap
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void getdata(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getdata1(String url) {
        Picasso.with(PersonActivity.this).load(url).into(iv);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
