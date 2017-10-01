package com.bwie.hp.mytaobao.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.hp.mytaobao.R;
import com.bwie.hp.mytaobao.activity.DlActivity;
import com.bwie.hp.mytaobao.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/31 20 44
 */

public class FragmentPage5 extends Fragment {
    private List<Bean> list;
    private ListView lv;
    private Button b;
    private ImageView iv_ren;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragment5,null);
        list=new ArrayList<>();
        lv=view.findViewById(R.id.lv);
        b=view.findViewById(R.id.b);
        iv_ren=view.findViewById(R.id.iv_ren);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent it=new Intent(getActivity(), DlActivity.class);
                startActivity(it);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bean bean=new Bean(R.mipmap.my_order_icon,"我的订单");
        Bean bean1=new Bean(R.mipmap.my_post_icon,"我的帖子");
        Bean bean2=new Bean(R.mipmap.my_invite_gift_icon,"邀请有礼");
        Bean bean3=new Bean(R.mipmap.my_face_test_icon,"刷脸测尺寸");
        Bean bean4=new Bean(R.mipmap.exchange_area_icon,"兑奖专区" );
        Bean bean5=new Bean(R.mipmap.my_coupon_icon,"我的卡片");
        Bean bean6=new Bean(R.mipmap.my_lottery_icon,"我的抽奖单");
        Bean bean7=new Bean(R.mipmap.my_collection_icon,"我收藏的商品");
        Bean bean8=new Bean(R.mipmap.personal_center_contact_service_icon,"联系客服");
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
        list.add(bean7);
        list.add(bean8);
        MyBase base=new MyBase();
        lv.setAdapter(base);

    }
    class MyBase extends BaseAdapter{
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
            view=View.inflate(getActivity(),R.layout.ss,null);
            TextView tv=view.findViewById(R.id.tv);
            ImageView iv=view.findViewById(R.id.iv);
            tv.setText(list.get(i).getTitle());
            iv.setBackgroundResource(list.get(i).getTupian());
            return view;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp = getActivity().getSharedPreferences("name", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("istrue", false);
        if(b){
            iv_ren.setImageResource(R.mipmap.user_icon_set);
        }

    }
}
