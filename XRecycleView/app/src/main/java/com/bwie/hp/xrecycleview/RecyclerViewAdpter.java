package com.bwie.hp.xrecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/10 20 32
 */

public class RecyclerViewAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private Context context;
    private bean bean1;

    public RecyclerViewAdpter(Context context, bean bean1) {
        this.context = context;
        this.bean1 = bean1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.ss, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        List<bean.DataBean> data = bean1.getData();
        if(holder instanceof  ViewHolder){
            Log.i("tag",data.size()+"");

                Picasso.with(context).load(data.get(position).getHeadImg()).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) holder).iv);
                /*Picasso.with(context).load(data.get(1).getHeadImg()).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) holder).iv);
                Picasso.with(context).load(data.get(2).getHeadImg()).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) holder).iv);
                Picasso.with(context).load(data.get(3).getHeadImg()).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) holder).iv);
                Picasso.with(context).load(data.get(4).getHeadImg()).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) holder).iv);
                Picasso.with(context).load(data.get(5).getHeadImg()).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) holder).iv);
                Picasso.with(context).load(data.get(6).getHeadImg()).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) holder).iv);
                Picasso.with(context).load(data.get(7).getHeadImg()).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) holder).iv);
                Picasso.with(context).load(data.get(8).getHeadImg()).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) holder).iv);
                Picasso.with(context).load(data.get(9).getHeadImg()).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) holder).iv);*/

        }



    }

    @Override
    public int getItemCount() {
        return 10;
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
        }
    }
}
