package com.bwie.hp.mytaobao.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.hp.mytaobao.R;
import com.bwie.hp.mytaobao.bean.Beann;
import com.bwie.hp.mytaobao.utils.MyUtils;
import com.squareup.picasso.Picasso;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/10 22 14
 */

public class MyBase1 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private Beann b;

    public MyBase1(Context context, Beann b) {
        this.context = context;
        this.b = b;
    }
   public static int one=0;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder rv=null;
        if(one==viewType){
            View view = LayoutInflater.from(context).inflate(R.layout.fl1, parent, false);
            rv=new ViewHolder(view);
            final RecyclerView.ViewHolder finalRv = rv;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= finalRv.getLayoutPosition();
                    listener.yijilistener(position);
                }
            });

        }
        return rv;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
           if(holder instanceof ViewHolder){
               Toast.makeText(context,b.getDatas().getClass_list().get(position).getImage(), Toast.LENGTH_SHORT).show();
               if(MyUtils.Unicode2GBK(b.getDatas().getClass_list().get(position).getImage()).length()>0){

                   Picasso.with(context).load(MyUtils.Unicode2GBK(b.getDatas().getClass_list().get(position).getImage())).placeholder(R.mipmap.ic_launcher).into(((ViewHolder) holder).iv);
               }
               ((ViewHolder) holder).tv.setText(b.getDatas().getClass_list().get(position).getGc_name());
           }
    }
    @Override
    public int getItemCount() {
        Log.i("eeeee",b.getDatas().getClass_list().size()+"");
        return b.getDatas().getClass_list().size();
    }
    @Override
    public int getItemViewType(int position) {
       return one;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.ivvi);
            tv=itemView.findViewById(R.id.tvvt);
        }
    }
    //定义接口
    public interface yijilistener{
        void yijilistener(int position);
    }
    //声明接口
    private yijilistener listener;

    public void setListener(yijilistener listener) {
        this.listener = listener;
    }
}
