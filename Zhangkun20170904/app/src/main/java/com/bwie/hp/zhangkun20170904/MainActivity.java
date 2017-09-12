package com.bwie.hp.zhangkun20170904;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/** *
 * 姓名:张堃
 * 时间:2017-09-04
 */
public class MainActivity extends AppCompatActivity {
   private CustomCircle cc;
    private Button b,b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cc= ((CustomCircle) findViewById(R.id.cc));
        b= ((Button) findViewById(R.id.b));
        b1= ((Button) findViewById(R.id.b1));
        b2= ((Button) findViewById(R.id.b2));
      b.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
