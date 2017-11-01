package test.bwie.com.bonimusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.bonimusic.R;
import test.bwie.com.bonimusic.activity.BDActivity;
import test.bwie.com.bonimusic.activity.BdBean;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/10 21 48
 */

public class FragmentB extends Fragment{
    private ListView lv;
    private List<BdBean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.fragmentb,null);
        lv=view.findViewById(R.id.lv);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list=new ArrayList<>();
        BdBean bb=new BdBean(R.mipmap.xe,"本地音乐");
        BdBean bb1=new BdBean(R.mipmap.xz,"最近播放");
        BdBean bb2=new BdBean(R.mipmap.x9,"下载管理");
        BdBean bb3=new BdBean(R.mipmap.x3,"我的歌手");
        list.add(bb);
        list.add(bb1);
        list.add(bb2);
        list.add(bb3);
        MyBase base=new MyBase();
        lv.setAdapter(base);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Log.i("qwwq","我进来了s");
                    Intent it=new Intent(getActivity(), BDActivity.class);
                    startActivity(it);
                }
            }
        });

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
            view=View.inflate(getActivity(),R.layout.bendi,null);
            ImageView iv=view.findViewById(R.id.iv);
            TextView tv=view.findViewById(R.id.tv);
            iv.setImageResource(list.get(i).getTp());
            tv.setText(list.get(i).getInfo());
            return view;
        }
    }
}
