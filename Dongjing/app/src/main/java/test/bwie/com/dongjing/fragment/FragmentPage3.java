package test.bwie.com.dongjing.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.activity.OrderActivity;
import test.bwie.com.dongjing.adpter.CarAdpter;
import test.bwie.com.dongjing.bean.Car;
import test.bwie.com.dongjing.bean.Price;
import test.bwie.com.dongjing.presenter.CarPresenter;
import test.bwie.com.dongjing.view.ICarView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/31 20 44
 */

public class FragmentPage3 extends Fragment implements ICarView{
    private RecyclerView rlv;
    public CheckBox cb;
    private TextView hj;
    private Button js;
    private CarAdpter ca;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragment3,null);
        rlv=view.findViewById(R.id.rcv);
        cb=view.findViewById(R.id.cb);
        hj=view.findViewById(R.id.hj);
        js=view.findViewById(R.id.sum);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        CarPresenter cp=new CarPresenter(FragmentPage3.this);
        cp.car(uid);
        js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               OrderActivity oa=new OrderActivity();
                oa.PaySignFromServer();
                Intent it=new Intent(getActivity(),OrderActivity.class);
               startActivity(it);
            }
        });
        
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void shopcar(final Car car) {
        ca = new CarAdpter(getActivity(),car);
        rlv.setAdapter(ca);
        cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(cb.isChecked()){
                    for (int i = 0; i <car.getData().size();i++){
                        car.getData().get(i).setIstrue(true);
                        for (int j = 0; j <car.getData().get(i).getList().size() ; j++){
                            car.getData().get(i).getList().get(j).setIstrue(true);
                        }
                    }
                }else{
                    for (int i = 0; i <car.getData().size();i++){
                        car.getData().get(i).setIstrue(false);
                        for (int j = 0; j <car.getData().get(i).getList().size() ; j++){
                            car.getData().get(i).getList().get(j).setIstrue(false);
                        }
                    }
                }
                ca.notifyDataSetChanged();
                ca.setLl(new CarAdpter.listener() {
                    @Override
                    public void lis(int price) {
                        Log.i("qa",price+"");
                        hj.setText(price+"");
                    }
                });
            }
        });
       
       /*ca.setLl(new CarAdpter.listener() {
           @Override
           public void lis(int positoion) {
               int n=0;
               for (int j = 0; j <car.getData().size() ; j++) {
                   Log.i("vvvv","傻逼");
                   if(car.getData().get(j).getIstrue()){
                       n++;
                       Log.i("bbbb",n+"");
                   }
               }
               if(n==car.getData().size()){
                   Log.i("hhh","我进来了");
                   cb.setChecked(true);
               }else{
                   cb.setChecked(false);
               }
               ca.notifyDataSetChanged();
           }
       });*/



            /*public void lis(int positoion) {
                int z=0;
                for (int i = 0; i <car.getData().size();i++) {
                    if(car.getData().get(i).getIstrue()){
                        z++;
                        Log.i("12",z+"");
                    }
                }
                if(z==car.getData().size()){
                    cb.setChecked(true);
                }else{
                    cb.setChecked(false);
                }
                ca.notifyDataSetChanged();
            }*/

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void pp(final Price pr){
        hj.setText(pr.getPrice()+"");
        Log.i("qqq",pr.getPrice()+"");
        js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getActivity(), OrderActivity.class);
                it.putExtra("price",pr.getPrice());
                startActivity(it);
            }
        });

    }

}
