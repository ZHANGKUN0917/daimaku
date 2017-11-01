package test.bwie.com.fifteenminutes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.bwie.com.fifteenminutes.R;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/31 15 15
 */

public class FragmentAA extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=View.inflate(getActivity(), R.layout.fragmentaa,null);
        return view;
    }
}
