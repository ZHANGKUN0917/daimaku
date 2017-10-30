package test.bwie.com.dongjing.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.bean.Detail;
import test.bwie.com.dongjing.presenter.DetailPresenter;
import test.bwie.com.dongjing.presenter.ShopPresenter;
import test.bwie.com.dongjing.view.IDetailView;
import test.bwie.com.dongjing.view.IShopView;

public class XQActivity extends AppCompatActivity implements IDetailView,IShopView{
    private WebView wv;
    private Button b,b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);
        wv= (WebView) findViewById(R.id.wv);
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        wv.setWebChromeClient(new WebChromeClient());
        wv.setWebViewClient(new WebViewClient());
        b= (Button) findViewById(R.id.shopcar);
        b1= (Button) findViewById(R.id.shopping);
        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 0);
        Log.i("123098",pid+"");
        DetailPresenter dp=new DetailPresenter(XQActivity.this);
        dp.detail(pid);
    }
    @Override
    public void detail(Detail detail) {
        Log.i("2112",detail.getMsg());
        final int pid = detail.getData().getPid();
        Log.i("lolo",pid+"");
        final int sellerid = detail.getData().getSellerid();
        Log.i("mkmk",sellerid+"");
        SharedPreferences id = getSharedPreferences("id", MODE_PRIVATE);
        SharedPreferences.Editor edit = id.edit();
        edit.putInt("pid",pid);
        edit.putInt("sellerid",sellerid);
        edit.commit();
        String detailUrl = detail.getData().getDetailUrl();
        wv.loadUrl(detailUrl);
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        final int uid = sp.getInt("uid", 0);
        Log.i("000",uid+"");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopPresenter shop=new ShopPresenter(XQActivity.this);
                shop.car(uid,pid,sellerid);
            }
        });

    }

    @Override
    public void car(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
