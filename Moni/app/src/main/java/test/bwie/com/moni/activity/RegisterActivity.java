package test.bwie.com.moni.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import presenter.Presenter;
import test.bwie.com.moni.R;
import view.IView;

public class RegisterActivity extends AppCompatActivity implements IView{
    private EditText et,et1;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register= (Button) findViewById(R.id.register);
        et= (EditText) findViewById(R.id.et);
        et1= (EditText) findViewById(R.id.et1);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et.getText().toString();
                String pwd=et1.getText().toString();
                Presenter pre=new Presenter(RegisterActivity.this);
                pre.getdata(name,pwd);
            }
        });
    }
    @Override
    public void setdata(String msg,String phone,String pwd) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.i("345",msg+phone+pwd);
        Intent it=new Intent(this,MainActivity.class);
        startActivity(it);
        it.putExtra("phone",phone);
        it.putExtra("pwd",pwd);
        startActivity(it);
        finish();
    }
}
