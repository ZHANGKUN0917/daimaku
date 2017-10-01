package com.bwie.hp.mytaobao.adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.hp.mytaobao.R;
import com.bwie.hp.mytaobao.bean.Bean1;
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
 * @date 2017/9/6 16 12
 */

public class MyAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnBannerListener {
    private Context context;
    private Bean1 bean;


    public MyAdpter(Context context, Bean1 bean) {
        this.context = context;
        this.bean = bean;
    }

    public static final int ONE_ITEM = 0;
    public static final int TWO_ITEM = 1;
    public static final int three_ITEM = 2;
    public static final int four_ITEM = 3;
    public static final int five_ITEM=4;
    public static final int six_ITEM=5;
    public static final int seven_ITEM=6;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh=null;
        if(ONE_ITEM==viewType){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpage,null);
            vh=new oneholder(view);
        }else if(TWO_ITEM==viewType){
            View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.linear,parent,false);
            vh=new twoholder(view1);
        }else if(three_ITEM==viewType){
            View view2= LayoutInflater.from(parent.getContext()).inflate(R.layout.second,parent,false);
            vh=new threeholder(view2);
        }else if(four_ITEM==viewType){
            View view3= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpage2,parent,false);
            vh=new fourholder(view3);
        }else if(five_ITEM==viewType){
            View view4= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewandimag,parent,false);
            vh=new fiveholder(view4);
        }else if(six_ITEM==viewType){
            View view5= LayoutInflater.from(parent.getContext()).inflate(R.layout.scroll,parent,false);
            vh=new sixholder(view5);
        }else{
            View view6= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid,parent,false);
            vh=new sevenholder(view6);

        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof oneholder){
            final List<String> list=new ArrayList<>();
            for (int i = 0; i <bean.getData().getAd1().size() ; i++) {
                list.add(bean.getData().getAd1().get(i).getImage());
            }
            ((oneholder) holder).banner.setBannerAnimation(Transformer.Default);
            ((oneholder) holder).banner.isAutoPlay(true);
            ((oneholder) holder).banner.setDelayTime(2000);
            ((oneholder) holder).banner.setImages(list);
            ((oneholder) holder).banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                        Picasso.with(context).load((String) path).into(imageView);
                }
            });
            ((oneholder) holder).banner.setIndicatorGravity(BannerConfig.CENTER).setOnBannerListener(this).start();

        }else if(holder instanceof twoholder){
            List<Bean1.DataBean.Ad5Bean> ad5 = bean.getData().getAd5();
            Picasso.with(context).load(ad5.get(0).getImage()).into(((twoholder) holder).iv);
            Picasso.with(context).load(ad5.get(1).getImage()).into(((twoholder) holder).iv1);
            Picasso.with(context).load(ad5.get(2).getImage()).into(((twoholder) holder).iv2);
            Picasso.with(context).load(ad5.get(3).getImage()).into(((twoholder) holder).iv3);
        }else if(holder instanceof threeholder){
            List<Bean1.DataBean.Ad8Bean> ad8 = bean.getData().getAd8();
            Picasso.with(context).load(ad8.get(0).getImage()).into(((threeholder) holder).iv);
            Picasso.with(context).load(ad8.get(1).getImage()).into(((threeholder) holder).iv1);
            Picasso.with(context).load(ad8.get(2).getImage()).into(((threeholder) holder).iv2);
        }else if(holder instanceof fourholder){
            final List<Bean1.DataBean.ActivityInfoBean.ActivityInfoListBean> infoList = bean.getData().getActivityInfo().getActivityInfoList();
            ((fourholder) holder).vp.setPageMargin(20);
            ((fourholder) holder).vp.setOffscreenPageLimit(3);
            ((fourholder) holder).vp.setCurrentItem(infoList.size()*10000);
            ((fourholder) holder).vp.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return Integer.MAX_VALUE;
                }
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view==object;
                }
                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    ImageView iv=new ImageView(context);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    Picasso.with(context).load(infoList.get(position%infoList.size()).getActivityImg()).placeholder(R.mipmap.ic_launcher).into(iv);
                    container.addView(iv);
                    return iv;
                }
                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }else if(holder instanceof fiveholder){
            List<Bean1.DataBean.SubjectsBean> subjects = bean.getData().getSubjects();
            if(position==4){
                ((fiveholder) holder).iv.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(subjects.get(0).getImage()).placeholder(R.mipmap.ic_launcher).into(((fiveholder) holder).iv);
            }else if(position==6){
                ((fiveholder) holder).iv.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(subjects.get(1).getImage()).placeholder(R.mipmap.ic_launcher).into(((fiveholder) holder).iv);
            }else if(position==8){
                ((fiveholder) holder).iv.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(subjects.get(2).getImage()).placeholder(R.mipmap.ic_launcher).into(((fiveholder) holder).iv);
            }else if(position==10){
                ((fiveholder) holder).iv.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(subjects.get(3).getImage()).placeholder(R.mipmap.ic_launcher).into(((fiveholder) holder).iv);
            }else if(position==12){
                ((fiveholder) holder).iv.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(subjects.get(4).getImage()).placeholder(R.mipmap.ic_launcher).into(((fiveholder) holder).iv);
            }else if(position==14){
                ((fiveholder) holder).iv.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(subjects.get(5).getImage()).placeholder(R.mipmap.ic_launcher).into(((fiveholder) holder).iv);
            }else if(position==16){
                ((fiveholder) holder).iv.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(subjects.get(6).getImage()).placeholder(R.mipmap.ic_launcher).into(((fiveholder) holder).iv);

            }
        }else if(holder instanceof sixholder){
            List<Bean1.DataBean.SubjectsBean.GoodsListBeanX> goodsList = bean.getData().getSubjects().get(0).getGoodsList();
            List<Bean1.DataBean.SubjectsBean.GoodsListBeanX> goodsList1 = bean.getData().getSubjects().get(1).getGoodsList();
            List<Bean1.DataBean.SubjectsBean.GoodsListBeanX> goodsList2 = bean.getData().getSubjects().get(2).getGoodsList();
            List<Bean1.DataBean.SubjectsBean.GoodsListBeanX> goodsList3 = bean.getData().getSubjects().get(3).getGoodsList();
            List<Bean1.DataBean.SubjectsBean.GoodsListBeanX> goodsList4 = bean.getData().getSubjects().get(4).getGoodsList();
            List<Bean1.DataBean.SubjectsBean.GoodsListBeanX> goodsList5 = bean.getData().getSubjects().get(5).getGoodsList();
            List<Bean1.DataBean.SubjectsBean.GoodsListBeanX> goodsList6 = bean.getData().getSubjects().get(6).getGoodsList();

            if(position==5){
                for (int i = 0; i < goodsList.size(); i++) {
                    View view= View.inflate(context,R.layout.ivtv,null);
                    ImageView iv=view.findViewById(R.id.ivv);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    TextView tv=view.findViewById(R.id.tvv);
                    Picasso.with(context).load(goodsList.get(i).getGoods_img()).placeholder(R.mipmap.ic_launcher).into(iv);
                    tv.setText(goodsList.get(i).getGoods_name());
                    ((sixholder) holder).lll.addView(view);
                }
            }else if(position==7){
                for (int i = 0; i < goodsList1.size(); i++) {
                    View view= View.inflate(context,R.layout.ivtv,null);
                    ImageView iv=view.findViewById(R.id.ivv);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    TextView tv=view.findViewById(R.id.tvv);
                    Picasso.with(context).load(goodsList1.get(i).getGoods_img()).placeholder(R.mipmap.ic_launcher).into(iv);
                    tv.setText(goodsList1.get(i).getGoods_name());
                    ((sixholder) holder).lll.addView(view);
                }
            }else if(position==9){
                for (int i = 0; i < goodsList2.size(); i++) {
                    View view= View.inflate(context,R.layout.ivtv,null);
                    ImageView iv=view.findViewById(R.id.ivv);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    TextView tv=view.findViewById(R.id.tvv);
                    Picasso.with(context).load(goodsList2.get(i).getGoods_img()).placeholder(R.mipmap.ic_launcher).into(iv);
                    tv.setText(goodsList2.get(i).getGoods_name());
                    ((sixholder) holder).lll.addView(view);
                }
            }else if(position==11){
                for (int i = 0; i < goodsList3.size(); i++) {
                    View view= View.inflate(context,R.layout.ivtv,null);
                    ImageView iv=view.findViewById(R.id.ivv);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    TextView tv=view.findViewById(R.id.tvv);
                    Picasso.with(context).load(goodsList3.get(i).getGoods_img()).placeholder(R.mipmap.ic_launcher).into(iv);
                    tv.setText(goodsList3.get(i).getGoods_name());
                    ((sixholder) holder).lll.addView(view);
                }
            }else if(position==13){
                for (int i = 0; i < goodsList4.size(); i++) {
                    View view= View.inflate(context,R.layout.ivtv,null);
                    ImageView iv=view.findViewById(R.id.ivv);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    TextView tv=view.findViewById(R.id.tvv);
                    Picasso.with(context).load(goodsList4.get(i).getGoods_img()).placeholder(R.mipmap.ic_launcher).into(iv);
                    tv.setText(goodsList4.get(i).getGoods_name());
                    ((sixholder) holder).lll.addView(view);
                }
            }else if(position==15){
                for (int i = 0; i < goodsList5.size(); i++) {
                    View view= View.inflate(context,R.layout.ivtv,null);
                    ImageView iv=view.findViewById(R.id.ivv);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    TextView tv=view.findViewById(R.id.tvv);
                    Picasso.with(context).load(goodsList5.get(i).getGoods_img()).placeholder(R.mipmap.ic_launcher).into(iv);
                    tv.setText(goodsList5.get(i).getGoods_name());
                    ((sixholder) holder).lll.addView(view);
                }
            }else if(position==17){
                for (int i = 0; i < goodsList6.size(); i++) {
                    View view= View.inflate(context,R.layout.ivtv,null);
                    ImageView iv=view.findViewById(R.id.ivv);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    TextView tv=view.findViewById(R.id.tvv);
                    Picasso.with(context).load(goodsList6.get(i).getGoods_img()).placeholder(R.mipmap.ic_launcher).into(iv);
                    tv.setText(goodsList6.get(i).getGoods_name());
                    ((sixholder) holder).lll.addView(view);
                }
            }
        }else if(holder instanceof sevenholder){
             ((sevenholder) holder).rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
             final List<Bean1.DataBean.DefaultGoodsListBean> defaultGoodsList = bean.getData().getDefaultGoodsList();

             ((sevenholder) holder).rv.setAdapter(new RecyclerView.Adapter() {
                 @Override
                 public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                     View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.ll,parent,false);

                     view.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             //自己活得position
                             int position=holder.getLayoutPosition();
                             //设置监听
                             if(listener!=null){
                                 listener.onRecyclerViewItemClick(position);
                             }
                         }

                     });
                     return new MyBase(view);
                 }
                 @Override
                 public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                         Picasso.with(context).load(defaultGoodsList.get(position).getGoods_img()).placeholder(R.mipmap.ic_launcher).into(((MyBase)holder).iv);
                         ((MyBase)holder).tv.setText(defaultGoodsList.get(position).getGoods_name());
                         ((MyBase)holder).tv1.setText(defaultGoodsList.get(position).getShop_price()+"");
                 }
                 @Override
                 public int getItemCount() {
                     return 6;
                 }


             });


        }
        Log.i("taqwer","我出来了");
    }
    @Override
    public int getItemCount() {
        return 19;
    }
    @Override
    public int getItemViewType(int position) {
       if(position==0){
           return ONE_ITEM;
       }else if(position==1){
           return TWO_ITEM;
       }else if(position==2){
           return three_ITEM;
       }else if(position==3){
           return four_ITEM;
       }else if(position==4){
           return five_ITEM;
       }else if(position==5){
           return  six_ITEM;
       } else if(position==6){
           return five_ITEM;
       }else if(position==7){
           return  six_ITEM;
       } else if(position==8){
           return five_ITEM;
       }else if(position==9){
           return  six_ITEM;
       }else if(position==10){
           return five_ITEM;
       }else if(position==11){
           return  six_ITEM;
       }else if(position==12){
           return five_ITEM;
       }else if(position==13){
           return  six_ITEM;
       }else if(position==14){
           return five_ITEM;
       }else if(position==15){
           return  six_ITEM;
       }else if(position==16){
           return five_ITEM;
       }else if(position==17){
           return  six_ITEM;
       }else if(position==18){
           return seven_ITEM;
       }
       return -1;
    }
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(context, "你点击的是+"+position+"", Toast.LENGTH_SHORT).show();
    }

    class oneholder extends RecyclerView.ViewHolder{
        Banner banner;
        public oneholder(View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.banner);
        }
    }
    class twoholder extends RecyclerView.ViewHolder{
        ImageView iv,iv1,iv2,iv3;

        public twoholder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            iv1=itemView.findViewById(R.id.iv1);
            iv2=itemView.findViewById(R.id.iv2);
            iv3=itemView.findViewById(R.id.iv3);


        }
    }
    class threeholder extends RecyclerView.ViewHolder{
        ImageView iv,iv1,iv2;
        public threeholder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv1=itemView.findViewById(R.id.iv1);
            iv1.setScaleType(ImageView.ScaleType.FIT_XY);
            iv2=itemView.findViewById(R.id.iv2);
            iv2.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
    class fourholder extends RecyclerView.ViewHolder{
        TextView tv;
        ViewPager vp;
        public fourholder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);
            vp=itemView.findViewById(R.id.vp);
        }
    }
    class fiveholder extends RecyclerView.ViewHolder{
       ImageView iv;
        public fiveholder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
        }
    }
    class sixholder extends RecyclerView.ViewHolder{
        LinearLayout lll;
        public sixholder(View itemView) {
            super(itemView);
            lll=itemView.findViewById(R.id.ll);
        }
    }
    class sevenholder extends RecyclerView.ViewHolder{
        RecyclerView rv;
        public sevenholder(View itemView) {
            super(itemView);
            rv=itemView.findViewById(R.id.rv);
        }

    }
    class MyBase extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv,tv1;
        public MyBase(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.ivvv);
            tv=itemView.findViewById(R.id.tvvv);
            tv1=itemView.findViewById(R.id.tvvv1);

        }
    }
    //声明接口
    private OnRrecyclerViewItemClickListener listener;

    //定义接口 和抽象方法
    public interface OnRrecyclerViewItemClickListener {
        void onRecyclerViewItemClick(int position);
    }

    //提供设置监听的方法
    public void setOnRrecyclerViewItemClickListener(OnRrecyclerViewItemClickListener listener) {
        this.listener = listener;
    }


}
