package com.bwie.hp.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.hp.mvp.model.LoginListener;
import com.bwie.hp.mvp.presenter.UserPresenter;
import com.bwie.hp.mvp.view.UserView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,UserView {
    private EditText et, et1;
    private TextView tv, tv1;
    private Button b;
    private UserPresenter up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = ((EditText) findViewById(R.id.et));
        et1 = ((EditText) findViewById(R.id.et1));
        b = ((Button) findViewById(R.id.b));
        b.setOnClickListener(this);
        up = new UserPresenter(this);

    }

    @Override
    public void onClick(View view) {
        up.login(listener);
    }

    @Override
    public String getname() {
        return et.getText().toString();
    }

    @Override
    public String getpwd() {
        return et1.getText().toString();
    }

    private LoginListener listener = new LoginListener() {
        @Override
        public void onsucess() {
            Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void error() {
            Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail() {
            Toast.makeText(MainActivity.this, "数据不完整", Toast.LENGTH_SHORT).show();
        }
    };
}

