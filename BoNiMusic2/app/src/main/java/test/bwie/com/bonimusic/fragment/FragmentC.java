package test.bwie.com.bonimusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.bwie.com.bonimusic.R;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/10 21 48
 */

public class FragmentC extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.fragmentc,null);
        return view;
    }
}
