package com.bwie.hp.xrecycleview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.bwie.test.okhttp3library.OkHttp3Utils;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdpter adpter;
    private PullLoadMoreRecyclerView  mPullLoadMoreRecyclerView;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if(page==1){
                        list = new ArrayList<>();
                    }
                    for (int i = 0; i <bean1.getData().size() ; i++) {
                         list.add(bean1.getData().get(i).getHeadImg());
                    }
                    if(adpter==null){
                        RecyclerViewAdpter adpter=new RecyclerViewAdpter(MainActivity.this,bean1);
                        mPullLoadMoreRecyclerView.setAdapter(adpter);
                    }else{
                        adpter.notifyDataSetChanged();
                    }
            }
        }
    };
    private int page=1;

    private com.bwie.hp.xrecycleview.bean bean1;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getdata();
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView)findViewById(R.id.pullLoadMoreRecyclerView);
        mPullLoadMoreRecyclerView.setLinearLayout();
        //设置网格布局
        //mPullLoadMoreRecyclerView.setGridLayout(2);//参数为列数
        //设置瀑布流
        //mPullLoadMoreRecyclerView.setStaggeredGridLayout(2);//参数为列数

        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           page=1;
                           getdata();
                       mPullLoadMoreRecyclerView.setRefreshing(false);
                       }
                   },2000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        getdata();
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                },2000);
            }
        });
    }

    private void getdata() {
        String url = "http://www.yulin520.com/a2a/forum/allTypeList?sign=376C5BFC22179A1B8FF3A86D4588B29F&pageSize=10&ts=1877785007&forumType=0&page="+page+"";
        OkHttp3Utils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 String s=response.body().string();
                Gson gson=new Gson();
                bean1 = gson.fromJson(s, bean.class);
                Message message=new Message();
                message.what=0;
                message.obj=s;
                handler.sendMessage(message);
            }
        });
    }
}
