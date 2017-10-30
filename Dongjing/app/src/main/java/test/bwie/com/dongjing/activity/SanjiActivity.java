package test.bwie.com.dongjing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.adpter.SearchAdpter;
import test.bwie.com.dongjing.adpter.ShowAdpter;
import test.bwie.com.dongjing.bean.Search;
import test.bwie.com.dongjing.bean.Show;
import test.bwie.com.dongjing.presenter.IPresenterShow;
import test.bwie.com.dongjing.presenter.SerachPresenter;
import test.bwie.com.dongjing.view.ISerachView;
import test.bwie.com.dongjing.view.IViewShow;

public class SanjiActivity extends AppCompatActivity implements IViewShow,ISerachView{
    private EditText et;
    private Button b;
    private RecyclerView rlv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanji);
        et= (EditText) findViewById(R.id.et);
        b= (Button) findViewById(R.id.search);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        Log.i("11",position+"");
        int pcid = intent.getIntExtra("pcid",0);
        Log.i("22",pcid+"");
        int pscid = intent.getIntExtra("pscid",0);
        Log.i("33",pscid+"");
        Log.i("111",position+pcid+pscid+"");
        rlv= (RecyclerView) findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        IPresenterShow is=new IPresenterShow(SanjiActivity.this);
        is.show(position,pcid,pscid);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = et.getText().toString();
                SerachPresenter sp=new SerachPresenter(SanjiActivity.this);
                sp.search(s);
            }
        });
    }
    @Override
    public void show(Show db) {
        ShowAdpter sd=new ShowAdpter(SanjiActivity.this,db);
        rlv.setAdapter(sd);
    }
    @Override
    public void Searchdata(Search sh) {
        Log.i("345",sh+"");
        SearchAdpter sha=new SearchAdpter(SanjiActivity.this,sh);
        rlv.setAdapter(sha);
        sha.setListen(new SearchAdpter.listener() {
            @Override
            public void listen(int position, int pid) {
                Intent it=new Intent(SanjiActivity.this,XQActivity.class);
                it.putExtra("pid",pid);
                startActivity(it);
            }
        });

    }
}
