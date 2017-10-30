package test.bwie.com.dongjing.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.presenter.Presenter;
import test.bwie.com.dongjing.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView{
   private EditText et,et1;
    private TextView tv,tv1;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        String mobile = intent.getStringExtra("mobile");
        String pwd = intent.getStringExtra("pwd");
        et= (EditText) findViewById(R.id.et);
        et1= (EditText) findViewById(R.id.et1);
        et.setText(mobile);
        et1.setText(pwd);
        tv= (TextView) findViewById(R.id.tv);
        tv1= (TextView) findViewById(R.id.tv1);
        b= (Button) findViewById(R.id.login);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Presenter presenter=new Presenter(LoginActivity.this);
                presenter.Logindata(et.getText().toString(),et1.getText().toString());
            }
        });
    }

    @Override
    public void Login(String msg, int uid) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("uid",uid);
        edit.commit();
    }
}
