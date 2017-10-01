package com.bwie.hp.mytaobao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.hp.mytaobao.R;
import com.bwie.hp.mytaobao.adpter.MyBase1;
import com.bwie.hp.mytaobao.adpter.MyBase2;
import com.bwie.hp.mytaobao.bean.Beann;
import com.bwie.test.okhttp3library.OkHttp3Utils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/31 20 44
 */

public class FragmentPage2 extends Fragment {

    private RecyclerView rv,rv1;
    private Beann beann;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    base = new MyBase1(getActivity(),beann);
                    rv.setAdapter(base);
                    base.setListener(new MyBase1.yijilistener() {
                        @Override
                        public void yijilistener(int position) {
                            Beann.DatasBean.ClassListBean listBean = beann.getDatas().getClass_list().get(position);
                            String gc_id = listBean.getGc_id();
                            String path2="http://169.254.254.142/mobile/index.php?act=goods_class&gc_id="+gc_id+"";
                            getdata1(path2);
                        }
                    });

            }
        }
    };
    private Handler handler1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    base2 = new MyBase2(getActivity(),beann1);
                    rv1.setAdapter(base2);
            }
        }
    };


    private MyBase1 base;
    private Beann beann1;
    private MyBase2 base2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragment2,null);
        rv=view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv1=view.findViewById(R.id.rv1);
        rv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         getdata();
        String path1="http://169.254.254.142/mobile/index.php?act=goods_class&gc_id="+1;
        getdata1(path1);
    }

    private void getdata1(String path) {


        OkHttp3Utils.doGet(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s1=response.body().string();
                Log.i("qwerrewq",s1);
                Gson gson=new Gson();
                beann1 = gson.fromJson(s1, Beann.class);
                Message message=new Message();
                message.what=0;
                message.obj=s1;
                handler1.sendMessage(message);

            }
        });
    }

    private void getdata() {
        String path=" http://169.254.254.142/mobile/index.php?act=goods_class";

        OkHttp3Utils.doGet(path, new Callback()
       {
            @Override
            public void onFailure(Call call, IOException e) {

            }
           @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
               Log.i("taggg",s);
               Gson gson=new Gson();
               beann = gson.fromJson(s, Beann.class);
               Message message=new Message();
                message.what=0;
                message.obj=s;
                handler.sendMessage(message);
            }
        });
    }

}
