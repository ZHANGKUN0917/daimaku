package com.bwie.hp.mvpxutils3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bwie.hp.mvpxutils3.presenter.UserPresenter;
import com.bwie.hp.mvpxutils3.view.UserView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements UserView {
    @ViewInject(R.id.et)
    EditText et;
    @ViewInject(R.id.et1)
    EditText et1;
    @ViewInject(R.id.b)
    Button b;
    @ViewInject(R.id.b1)
    Button b1;
    private UserPresenter up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        up=new UserPresenter(this);
    }
    @Event(value={R.id.b,R.id.b1})
    private void getbutton(View view){
         switch (view.getId()){
             case R.id.b:
                 up.register(getname(),getpwd());
                 break;
             case R.id.b1:
                up.login(getname(),getpwd());
                 break;
         }
    }


    @Override
    public String getname() {
        return  et.getText().toString();
    }

    @Override
    public String getpwd() {
        return  et1.getText().toString();
    }

    @Override
    public void setname(String name) {

    }

    @Override
    public void setpwd(String pwd) {

    }


}
