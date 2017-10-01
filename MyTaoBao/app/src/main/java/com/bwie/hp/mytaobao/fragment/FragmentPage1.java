package com.bwie.hp.mytaobao.fragment;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.hp.mytaobao.R;
import com.bwie.hp.mytaobao.activity.Main22Activity;
import com.bwie.hp.mytaobao.activity.SouSuoActivity;
import com.bwie.hp.mytaobao.adpter.MyAdpter;
import com.bwie.hp.mytaobao.bean.Bean1;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/31 20 44
 */

public class FragmentPage1 extends Fragment {
    private String path="https://m.yunifang.com/yunifang/mobile/home";
    private RecyclerView re;
    private Bean1 bean1;
    private ImageView iv,iv1,iv2,iv3;
    private TextView tv;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    MyAdpter adpter=new MyAdpter(getActivity(),bean1);
                    re.setAdapter(adpter);
                    adpter.setOnRrecyclerViewItemClickListener(new MyAdpter.OnRrecyclerViewItemClickListener() {
                        @Override
                        public void onRecyclerViewItemClick(int position) {
                            Intent it=new Intent(getActivity(), Main22Activity.class);
                            startActivity(it);
                        }
                    });
                    Log.i("wwww",bean1+"");
            }
        }
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=View.inflate(getActivity(),R.layout.fragment1,null);
        re= ((RecyclerView) view.findViewById(R.id.recy));
        re.setLayoutManager(new LinearLayoutManager(getActivity()));
        iv=view.findViewById(R.id.iv);
        iv1=view.findViewById(R.id.iv1);
        iv2=view.findViewById(R.id.iv2);
        iv3=view.findViewById(R.id.iv3);
        tv=view.findViewById(R.id.tv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getActivity(), SouSuoActivity.class);
                startActivityForResult(it,200);
            }
        });
        getdata();


    }

    private void getdata() {
        OkHttpClient ok=new OkHttpClient();
        Request request=new Request.Builder().url(path).build();
        Call call = ok.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                String s=response.body().string();
                bean1 = gson.fromJson(s, Bean1.class);
                Message message=new Message();
                message.what=0;
                message.obj=s;
                handler.sendMessage(message);

            }
        });
    }
}
