package test.bwie.com.moni.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import presenter.Presenter1;
import test.bwie.com.moni.R;
import view.IView1;

public class MainActivity extends AppCompatActivity implements IView1 {

    private EditText et,et1;
    private Button b,b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et= (EditText) findViewById(R.id.et);
        et1= (EditText) findViewById(R.id.et1);
        b= (Button) findViewById(R.id.login);
        b1= (Button) findViewById(R.id.register);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(it);
            }
        });
        Intent intent = getIntent();
        final String phone = intent.getStringExtra("phone");
        final String pwd = intent.getStringExtra("pwd");
        if(phone!=null&&pwd!=null){
            et.setText(phone);
            et1.setText(pwd);
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Presenter1 p1=new Presenter1(MainActivity.this);
                p1.getdata(et.getText().toString(),et1.getText().toString());
            }
        });
    }

    @Override
    public void getdata(String msg, int uid,String name) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("uid", uid);
        edit.commit();
        Intent it=new Intent(MainActivity.this,PersonActivity.class);
        it.putExtra("username",name);
        startActivity(it);
        finish();

    }
}
