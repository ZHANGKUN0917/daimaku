package com.bwie.hp.mvplistview2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.bwie.hp.mvplistview2.adpter.MyAdpter;
import com.bwie.hp.mvplistview2.bean.Bean;
import com.bwie.hp.mvplistview2.presenter.Presenter;
import com.bwie.hp.mvplistview2.view.UserView;
import com.bwie.myxlistview.XListView;

import java.util.ArrayList;
import java.util.List;

import static com.bwie.hp.mvplistview2.R.id.lv;

public class MainActivity extends AppCompatActivity implements UserView {
    private int page=1;
    private XListView xlv;
    private Handler handler=new Handler();

    private MyAdpter adpter;
    private List<Bean.DataBean> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xlv= (XListView) findViewById(lv);
        final Presenter pt=new Presenter(this);
        pt.getdata(this,page);
        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);
        xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       page=1;
                       pt.getdata(MainActivity.this,page);
                       xlv.stopRefresh();
                   }
               },2000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        pt.getdata(MainActivity.this,page);
                       xlv.stopLoadMore();
                    }
                },2000);
            }
        });

    }

    @Override
    public void getlist(List<Bean.DataBean> list2) {
        for (int i = 0; i <list2.size() ; i++) {
            list.add(list2.get(i));
        }
        if(page==1){
            adpter = new MyAdpter(MainActivity.this,list);
            xlv.setAdapter(adpter);
        }else{
            adpter.notifyDataSetChanged();
        }



    }


}
