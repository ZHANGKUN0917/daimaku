package test.bwie.com.dongjing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.adpter.MyAdpter;
import test.bwie.com.dongjing.adpter.MyAdpter1;
import test.bwie.com.dongjing.bean.FenLei;
import test.bwie.com.dongjing.bean.FenLei1;
import test.bwie.com.dongjing.presenter.Presenter;
import test.bwie.com.dongjing.view.IView;
import test.bwie.com.dongjing.view.IView1;

/**
 * 类的用途：
 *
 *import static android.R.attr.author;

@author HP
 * @date 2017/8/31 20 44
 */

public class FragmentPage2 extends Fragment implements IView,IView1 {
    private RecyclerView rcy;
    private RecyclerView rcy1;
    private MyAdpter adpter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragment2,null);
        rcy=view.findViewById(R.id.rcy);
        rcy1=view.findViewById(R.id.rcy1);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        Presenter presenter=new Presenter((IView) FragmentPage2.this);
        presenter.setdata();
        rcy1.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    @Override
    public void dd(FenLei fenlei) {
        adpter = new MyAdpter(getActivity(),fenlei);
         rcy.setAdapter(adpter);
        adpter.setLl(new MyAdpter.Listener() {
            @Override
            public void list(int position, int cid) {
                Presenter presenter=new Presenter((IView1) FragmentPage2.this);
                presenter.setdata1(position,cid);
            }
        });
    }

    @Override
    public void dd1(FenLei1 fenLei1) {
        if(fenLei1!=null){
            MyAdpter1 adpter1=new MyAdpter1(getActivity(),fenLei1);
            rcy1.setAdapter(adpter1);
        }


    }
}
