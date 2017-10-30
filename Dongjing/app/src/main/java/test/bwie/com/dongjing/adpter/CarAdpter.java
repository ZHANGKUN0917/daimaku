package test.bwie.com.dongjing.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import bwie.com.cartnumlibrary.AmountView;
import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.bean.Car;
import test.bwie.com.dongjing.bean.Price;

import static test.bwie.com.dongjing.R.id.av;
import static test.bwie.com.dongjing.R.id.iv;
import static test.bwie.com.dongjing.R.id.price;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/19 16 32
 */

public class CarAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Car car;
    public CarAdpter(Context context, Car car) {
        this.context = context;
        this.car = car;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        RecyclerView.ViewHolder rv;
        View view= LayoutInflater.from(context).inflate(R.layout.gooscar,parent,false);
        rv=new OneViewholder(view);
        return rv;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof OneViewholder){
            ((OneViewholder) holder).elv.setGroupIndicator(null);

            ((OneViewholder) holder).elv.setAdapter(new BaseExpandableListAdapter() {
                @Override
                public int getGroupCount(){
                    return car.getData().size();
                }
                @Override
                public int getChildrenCount(int i) {
                    return car.getData().get(i).getList().size();
                }
                @Override
                public Object getGroup(int i) {
                    return car.getData().get(i);
                }
                @Override
                public Object getChild(int i, int i1) {
                    return car.getData().get(i).getList().get(i1);
                }
                @Override
                public long getGroupId(int i) {
                    return i;
                }
                @Override
                public long getChildId(int i, int i1) {
                    return i1;
                }
                @Override
                public boolean hasStableIds() {
                    return true;
                }
                @Override
                public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
                    final ViewHolder vh;
                    if(view==null){
                        vh=new ViewHolder();
                        view=View.inflate(context,R.layout.yijiitem,null);
                        vh.cb=view.findViewById(R.id.cb);
                        vh.tv=view.findViewById(R.id.shangjia);
                        view.setTag(vh);
                    }else{
                        vh= (ViewHolder) view.getTag();
                    }
                    vh.tv.setText(car.getData().get(i).getSellerName());
                    Log.i("qqw",car.getData().get(i).getIstrue()+"");
                   

                    //给复选框赋值，这时候复选框是false
                    vh.cb.setChecked(car.getData().get(i).getIstrue());
                    vh.cb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //点击事件触发时，复选框状态值改变，把这个时候值放到bean里面
                            car.getData().get(i).setIstrue(vh.cb.isChecked());
                            int price=0;
                            //遍历一级
                            for (int z = 0; z <car.getData().size();z++) {
                                if (car.getData().get(z).getIstrue()) {
                                    //如果一级被选中，二级下都要被选中，遍历二级列表，都赋成选中状态
                                    for (int j = 0; j < car.getData().get(z).getList().size(); j++) {
                                        car.getData().get(z).getList().get(j).setIstrue(true);
                                        //如果二级被选中算价钱
                                        if (car.getData().get(z).getList().get(j).istrue()) {
                                            int num = (int) (car.getData().get(z).getList().get(j).getNum() * car.getData().get(z).getList().get(j).getPrice());
                                            price = price + num;
                                        }
                                    }
                                } else {
                                    for (int j = 0; j < car.getData().get(z).getList().size(); j++) {
                                        car.getData().get(z).getList().get(j).setIstrue(false);
                                    }
                                }
                            }
                            EventBus.getDefault().post(new Price(price));
                            notifyDataSetChanged();
                        }
                    });

                    return view;
                }
                @Override
                public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
                    final ViewHolder1 vh1;
                    if(view==null){
                        vh1=new ViewHolder1();
                        view=View.inflate(context,R.layout.erjiitem,null);
                        vh1.cb=view.findViewById(R.id.cb);
                        vh1.iv=view.findViewById(iv);
                        vh1.tv=view.findViewById(R.id.title);
                        vh1.tv1=view.findViewById(price);
                        vh1.av=view.findViewById(av);
                        view.setTag(vh1);
                    }else{
                        vh1= (ViewHolder1) view.getTag();
                    }
                    String images = car.getData().get(i).getList().get(i1).getImages();
                    String[] split = images.split("\\|");

                    Picasso.with(context).load(split[0]).placeholder(R.mipmap.ic_launcher).into(vh1.iv);
                    vh1.tv.setText(car.getData().get(i).getList().get(i1).getTitle());
                    vh1.tv1.setText(car.getData().get(i).getList().get(i1).getPrice()+"");
                    vh1.av.setGoods_storage(100);
                    vh1.av.setAmount(car.getData().get(i).getList().get(i1).getNum());
                    vh1.av.btnDecrease.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int amount = vh1.av.getAmount();
                            if(amount>1){
                                amount--;
                            }
                            vh1.av.setAmount(amount);
                            car.getData().get(i).getList().get(i1).setNum(vh1.av.getAmount());
                            notifyDataSetChanged();
                        }
                    });
                    vh1.av.btnIncrease.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int amount = vh1.av.getAmount();
                            if(amount<100){
                                amount++;
                            }
                            vh1.av.setAmount(amount);
                            car.getData().get(i).getList().get(i1).setNum(vh1.av.getAmount());
                            notifyDataSetChanged();
                            Log.i("111",vh1.av.getAmount()+"");

                        }
                    });

                    vh1.cb.setChecked(car.getData().get(i).getList().get(i1).istrue());
                    vh1.cb.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            //把此时二级列表的状态赋值到bean里面
                            car.getData().get(i).getList().get(i1).setIstrue(vh1.cb.isChecked());
                            int z=0;
                            for (int j = 0; j <car.getData().get(i).getList().size() ; j++) {
                                if(car.getData().get(i).getList().get(j).istrue()){
                                    z++;
                                }
                            }
                            if(z==car.getData().get(i).getList().size()){
                                car.getData().get(i).setIstrue(true);
                                Log.i("123",123+"");
                            }else{
                                car.getData().get(i).setIstrue(false);
                                Log.i("1234",1235+"");
                            }
                            //suanqina
                            int num=0;
                            int price=0;
                            for (int j = 0; j <car.getData().size();j++) {
                                for (int k = 0; k <car.getData().get(j).getList().size() ; k++) {
                                    if(car.getData().get(j).getList().get(k).istrue()){
                                        num= (int) (car.getData().get(j).getList().get(k).getNum()*car.getData().get(j).getList().get(k).getPrice());
                                        price=price+num;
                                    }
                                }
                            }
                            EventBus.getDefault().post(new Price(price));
                            Log.i("qqq9090",car.getData().get(i).getIstrue()+"");
                            notifyDataSetChanged();
                        }
                    });

                    return view;
                }
                @Override
                public boolean isChildSelectable(int i, int i1) {
                    return true;
                }
                class ViewHolder{
                    CheckBox cb;
                    TextView tv;
                }
                class ViewHolder1{
                    CheckBox cb;
                    ImageView iv;
                    TextView tv,tv1;
                    AmountView av;
                }
            });
            //二级列表默认展开
            int count = (((OneViewholder) holder).elv).getCount();
            for (int j = 0; j<count; j++) {
                ((OneViewholder) holder).elv.expandGroup(j);
            }
        }
    }
    @Override
    public int getItemCount() {
        return 1;
    }
    class OneViewholder extends RecyclerView.ViewHolder{
        ExpandableListView elv;
        public OneViewholder(View itemView) {
            super(itemView);
            elv=itemView.findViewById(R.id.elv);
        }
    }
    public interface listener{
        void lis(int price);
    }
    public listener ll;
    public void setLl(listener ll) {
        this.ll = ll;
    }
    /*public interface listener1{
        void lis1(int sum);
    }
    public listener1 ll1;
    public void setLl1(listener1 ll1) {
        this.ll1 = ll1;
    }*/
}
