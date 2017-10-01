package com.bwie.hp.mytaobao.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.hp.mytaobao.R;
import com.bwie.hp.mytaobao.bean.Bean2;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/9 12 58
 */

public class MyBase extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnBannerListener{
    private Context context;
    private Bean2 bean;

    public MyBase(Context context, Bean2 bean) {
        this.context = context;
        this.bean = bean;
    }
    public final static int one=0;
    public final static int two=1;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder rh = null;
        if(one==viewType){
            View view = LayoutInflater.from(context).inflate(R.layout.xq1, parent, false);
            rh=new oneholder(view);
        }else if(two==viewType){
            View view1=LayoutInflater.from(context).inflate(R.layout.xq2,parent,false);
            rh=new twoholder(view1);
        }
        return rh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if(holder instanceof oneholder){
           List<String> list=new ArrayList<>();
           List<Bean2.DataBean.GoodsBean.GalleryBean> gallery = bean.getData().getGoods().getGallery();
           for (int i = 0; i <gallery.size() ; i++) {
               list.add(gallery.get(i).getNormal_url());
           }
           ((oneholder) holder).ba.setBannerAnimation(Transformer.Default);
           ((oneholder) holder).ba.isAutoPlay(true);
           ((oneholder) holder).ba.setDelayTime(2000);
           ((oneholder) holder).ba.setImages(list);
           ((oneholder) holder).ba.setImageLoader(new ImageLoader() {
               @Override
               public void displayImage(Context context, Object path, ImageView imageView) {
                   Picasso.with(context).load((String) path).into(imageView);
               }
           });
           ((oneholder) holder).ba.setIndicatorGravity(BannerConfig.CENTER).setOnBannerListener(this).start();
           ((oneholder) holder).tv.setText(bean.getData().getGoods().getGoods_name());
           ((oneholder) holder).tv1.setText(bean.getData().getGoods().getShop_price()+"");
           ((oneholder) holder).tv2.setText("运费:"+bean.getData().getGoods().getShipping_fee());

       }else if(holder instanceof twoholder){
           ((twoholder) holder).tv.setText(bean.getData().getActivity().get(0).getTitle());
           ((twoholder) holder).tv2.setText(bean.getData().getActivity().get(1).getTitle());
           ((twoholder) holder).tv3.setText(bean.getData().getActivity().get(2).getTitle());
           Picasso.with(context).load(bean.getData().getGoodsRelDetails().get(0).getGoods_img()).placeholder(R.mipmap.ic_launcher).into(((twoholder) holder).iv);
           Picasso.with(context).load(bean.getData().getGoodsRelDetails().get(1).getGoods_img()).placeholder(R.mipmap.ic_launcher).into(((twoholder) holder).iv2);
           Picasso.with(context).load(bean.getData().getGoodsRelDetails().get(2).getGoods_img()).placeholder(R.mipmap.ic_launcher).into(((twoholder) holder).iv3);
       }
    }

    @Override
    public int getItemViewType(int position) {
       if(position==0){
           return one;
       }else if(position==1){
           return two;
       }
       return -1;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public void OnBannerClick(int position) {

    }

    class oneholder extends RecyclerView.ViewHolder{
        Banner ba;
        TextView tv,tv1,tv2;
        public oneholder(View itemView) {
            super(itemView);
            ba=itemView.findViewById(R.id.ban);
            tv=itemView.findViewById(R.id.text);
            tv1=itemView.findViewById(R.id.text1);
            tv2=itemView.findViewById(R.id.text2);
        }
    }
    class twoholder extends RecyclerView.ViewHolder{
        TextView tv,tv2,tv3;
        ImageView iv,iv2,iv3;
        public twoholder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.text);
            tv2=itemView.findViewById(R.id.text1);
            tv3=itemView.findViewById(R.id.text2);
            iv=itemView.findViewById(R.id.img);
            iv2=itemView.findViewById(R.id.img1);
            iv3=itemView.findViewById(R.id.img2);
        }
    }
}
