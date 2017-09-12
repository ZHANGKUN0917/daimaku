package com.bwie.hp.mvplistview2.mode;

import android.content.Context;

import com.bwie.hp.mvplistview2.bean.Bean;
import com.bwie.hp.mvplistview2.view.UserView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/4 20 14
 */

public class Usermode implements IUserMode {
    @Override
    public void getdata(Context context, final UserView userview,int page) {
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        String path="http://www.yulin520.com/a2a/forum/allTypeList?sign=376C5BFC22179A1B8FF3A86D4588B29F&pageSize=10&ts=1877785007&forumType=0&page="+page+"";

        asyncHttpClient.get(context, path, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson=new Gson();
                Bean bean = gson.fromJson(responseString, Bean.class);
                List<Bean.DataBean> data  = bean.getData();

                userview.getlist(data);
            }
        });
    }
}
