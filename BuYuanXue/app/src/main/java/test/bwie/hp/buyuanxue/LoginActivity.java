package test.bwie.hp.buyuanxue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import test.bwie.hp.buyuanxue.presenter.Presenter;
import test.bwie.hp.buyuanxue.view.IView;

public class LoginActivity extends AppCompatActivity implements IView{
   private ImageView iv;
    private EditText et,et1;
    private Button yzm,button;
    private TextView tv;
    private Button wxdl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dl);
        iv= (ImageView) findViewById(R.id.chacha);
        et= (EditText) findViewById(R.id.et);
        et1= (EditText) findViewById(R.id.et1);
        yzm= (Button) findViewById(R.id.yzm);
        button= (Button) findViewById(R.id.button);
        tv= (TextView) findViewById(R.id.xieyi1);
        wxdl= (Button) findViewById(R.id.wxdl);


        String code=et1.getText().toString();
        yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=et.getText().toString();
                Log.i("aqaq",phone);
                Presenter pre=new Presenter(LoginActivity.this);
                pre.getdata(phone,LoginActivity.this);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=et.getText().toString();
                String code=et1.getText().toString();
                Presenter presenter=new Presenter(LoginActivity.this);
                presenter.getdata1(phone,code);
            }
        });


    }
    @Override
    public void setdata1(String info) {
        try {
            JSONObject object=new JSONObject(info);
            String msg=object.getString("msg");
            int code=object.getInt("code");
            if(code==0){
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                finish();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
