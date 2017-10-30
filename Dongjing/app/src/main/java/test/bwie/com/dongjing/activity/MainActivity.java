package test.bwie.com.dongjing.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import test.bwie.com.dongjing.R;

public class MainActivity extends AppCompatActivity {
   private ImageView iv;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(it);
            }
        },2000);
    }
}
