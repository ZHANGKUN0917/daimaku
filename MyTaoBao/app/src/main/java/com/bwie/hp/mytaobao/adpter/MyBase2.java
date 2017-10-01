package com.bwie.hp.mytaobao.adpter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.hp.mytaobao.R;
import com.bwie.hp.mytaobao.bean.Beann;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/10 22 14
 */

public class MyBase2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private Beann bean;


    public MyBase2(Context context, Beann bean) {
        this.context = context;
        this.bean = bean;
    }
    public int one=0;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder rvh=null;
        if(one==viewType){
            View view = LayoutInflater.from(context).inflate(R.layout.fl2, parent, false);
            rvh=new oneHolder(view);
        }
        return rvh;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        String gc_id = bean.getDatas().getClass_list().get(position).getGc_id();
        if(holder instanceof oneHolder){
            Log.i("zxcv", "我进来了");
            ((oneHolder) holder).tv.setText(bean.getDatas().getClass_list().get(position).getGc_name());

             String path="http://169.254.254.142/mobile/index.php?act=goods_class&gc_id="+gc_id+"";
              RequestParams params=new RequestParams(path);
              x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                     Gson gson=new Gson();
                    final Beann beann1 = gson.fromJson(result, Beann.class);
                    ((oneHolder) holder).rv.setLayoutManager(new GridLayoutManager(context,3));
                     ((oneHolder) holder).rv.setAdapter(new RecyclerView.Adapter() {
                         @Override
                         public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                             RecyclerView.ViewHolder rvh=null;
                             View view = LayoutInflater.from(context).inflate(R.layout.fl3, parent, false);
                             rvh=new TwoHolder(view);
                             return rvh;
                         }

                         @Override
                         public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                             ((TwoHolder)holder).tv.setText(beann1.getDatas().getClass_list().get(position).getGc_name());
                         }

                         @Override
                         public int getItemCount() {
                             return beann1.getDatas().getClass_list().size();
                         }
                     });

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
                class TwoHolder extends RecyclerView.ViewHolder{
                TextView tv;
                    public TwoHolder(View itemView) {
                        super(itemView);
                        tv=itemView.findViewById(R.id.text);
                    }
                }
            });

        }
    }
    @Override
    public int getItemCount() {
        return bean.getDatas().getClass_list().size();
    }

    @Override
    public int getItemViewType(int position) {
       return one;
    }
    class oneHolder extends RecyclerView.ViewHolder{
       TextView tv;
        RecyclerView rv;
        public oneHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tvvt1);
            rv=itemView.findViewById(R.id.rv2);
        }
    }
}
