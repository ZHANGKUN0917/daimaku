package com.bwie.hp.mytaobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.hp.mytaobao.R;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/31 20 44
 */

public class FragmentPage3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragment3,null);
        return view;
    }
}
