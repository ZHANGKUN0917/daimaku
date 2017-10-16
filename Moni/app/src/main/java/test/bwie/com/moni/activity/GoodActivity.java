package test.bwie.com.moni.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.json.JSONArray;

import presenter.Presenter3;
import test.bwie.com.moni.R;
import test.bwie.com.moni.activity.adpter.MyAdpter;
import view.IView3;

public class GoodActivity extends AppCompatActivity implements IView3 {
    private RecyclerView recyclerView;
    private EditText et;
    private Button b,sc;
    private PopupWindow popupWindow;
    private int page=1;
    private MyAdpter adpter;
    private SwipeRefreshLayout sr;
    private String s;
    private Handler handler=new Handler();
    private LinearLayoutManager manager;
    private GridLayoutManager manager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);
        recyclerView= (RecyclerView) findViewById(R.id.rcy);
        manager=new LinearLayoutManager(this);
        manager1=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        sr= (SwipeRefreshLayout) findViewById(R.id.sr);
        sc= (Button) findViewById(R.id.su);
        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;
                        Presenter3 pr3=new Presenter3(GoodActivity.this);
                        pr3.getdata(s,page);
                        sr.setRefreshing(false);
                    }
                },2000);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(manager!=null){
                    int lastitem=manager.findLastVisibleItemPosition();
                    if(lastitem+1==adpter.getItemCount()){
                        page++;
                        handler.postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                Presenter3 pr3=new Presenter3(GoodActivity.this);
                                pr3.getdata(s,page);
                            }
                        },2000);
                        adpter.notifyDataSetChanged();
                    }
                }
                if(manager1!=null){
                    int lastitem=manager1.findLastVisibleItemPosition();
                    if(lastitem+1==adpter.getItemCount()){
                        page++;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Presenter3 pr3=new Presenter3(GoodActivity.this);
                                pr3.getdata(s,page);
                            }
                        },2000);
                        adpter.notifyDataSetChanged();
                    }
                }

            }
        });
        et= (EditText) findViewById(R.id.et);
        b= (Button) findViewById(R.id.b);
        sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = et.getText().toString();
                Presenter3 pr3=new Presenter3(GoodActivity.this);
                pr3.getdata(s,page);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1=View.inflate(GoodActivity.this,R.layout.pop,null);
                TextView tv,tv1;
                tv=view1.findViewById(R.id.tv);
                tv1=view1.findViewById(R.id.tv1);
                popupWindow = new PopupWindow(view1,100,70,true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.showAsDropDown(b);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(GoodActivity.this));
                    }
                });
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerView.setLayoutManager(new GridLayoutManager(GoodActivity.this,2,GridLayoutManager.VERTICAL,false));
                    }
                });
            }
        });
    }
    @Override
    public void getdata(JSONArray data) {
        Log.i("890",data+"");
        if(adpter==null){
            adpter = new MyAdpter(this,data);
            recyclerView.setAdapter(adpter);
        }else{
            adpter.notifyDataSetChanged();
        }


    }
}
