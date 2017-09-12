package com.bwie.hp.mvplistview2.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.hp.mvplistview2.R;
import com.bwie.hp.mvplistview2.bean.Bean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/4 20 28
 */

public class MyAdpter extends BaseAdapter {
    private Context context;
    private List<Bean.DataBean> list;

    public MyAdpter(Context context, List<Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=View.inflate(context, R.layout.ss,null);
        ImageView iv=view.findViewById(R.id.iv);
        TextView tv=view.findViewById(R.id.tv);
        tv.setText(list.get(i).getTitle());
        Picasso.with(context).load(list.get(i).getHeadImg()).error(R.mipmap.ic_launcher).into(iv);

       return view;
    }

}
