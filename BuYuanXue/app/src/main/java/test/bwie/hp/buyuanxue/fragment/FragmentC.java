package test.bwie.hp.buyuanxue.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.bwie.hp.buyuanxue.LoginActivity;
import test.bwie.hp.buyuanxue.R;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/28 11 28
 */

public class FragmentC extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("istrue", true);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("istrue",false);
        edit.commit();
        View view = null;
        if(b){
             view=View.inflate(getActivity(), R.layout.fragmentc,null);
            return view;
        }else{
            Intent it=new Intent(getActivity(), LoginActivity.class);
            startActivity(it);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
