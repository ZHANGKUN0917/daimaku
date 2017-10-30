package test.bwie.com.dongjing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.presenter.Presenter;
import test.bwie.com.dongjing.view.RegisterIView;
public class RegisterActivity extends AppCompatActivity implements RegisterIView {
    private TextView tv,tv1;
    private EditText et,et1;
    private Button b;
    public static boolean bb=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tv= (TextView) findViewById(R.id.tv);
        tv1= (TextView) findViewById(R.id.tv1);
        et= (EditText) findViewById(R.id.et);
        et1= (EditText) findViewById(R.id.et1);
        b= (Button) findViewById(R.id.login);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = et.getText().toString();
                String pwd=et1.getText().toString();
                Log.i("123",phone);
                Log.i("234",pwd);
                Presenter presenter=new Presenter(RegisterActivity.this);
                presenter.registerdata(phone,pwd);
            }
        });
    }
    @Override
    public void GRegister(String mag,String phone,String pwd) {
        Toast.makeText(this, mag, Toast.LENGTH_SHORT).show();
        bb=true;
        Intent it=new Intent(RegisterActivity.this,LoginActivity.class);
        it.putExtra("mobile",phone);
        it.putExtra("pwd",pwd);
        startActivity(it);
        finish();
    }

}
