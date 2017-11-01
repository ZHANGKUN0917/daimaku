package test.bwie.com.fifteenminutes.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.fifteenminutes.R;
import test.bwie.com.fifteenminutes.activity.TopBar;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/30 22 08
 */

public class FragmentA extends Fragment {
    private RadioGroup rg;
    private RadioButton rb,rb1;
    private ViewPager vp;
    private FragmentAA fa;
    private FragmentBB fb;
    private ImageView iv,iv1;
    private TextView tv;
    private List<Fragment> list;
    private TopBar tb;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragmenta,null);
        rg=view.findViewById(R.id.rg);
        tb=view.findViewById(R.id.tb);
        rb=view.findViewById(R.id.rb);
        rb1=view.findViewById(R.id.rb1);
        vp=view.findViewById(R.id.vp);
        fa=new FragmentAA();
        fb=new FragmentBB();
        list=new ArrayList<>();
        list.add(fa);
        list.add(fb);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentMyAdpter am=new FragmentMyAdpter(manager);
        tb.rightimage.setImageResource(R.mipmap.b);
        tb.leftimage.setImageResource(R.mipmap.aa);
        tb.tv.setText("推荐");
        tb.tv.setTextColor(Color.BLUE);
        tb.tv.setTextSize(30);
        tb.setTopBarClickListener(new TopBar.TopBarClickListener() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {

            }
        });
        vp.setAdapter(am);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rg.check(R.id.rb);
                        break;
                    case 1:
                        rg.check(R.id.rb1);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rb.setTextColor(Color.BLUE);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                  case R.id.rb:
                      vp.setCurrentItem(0);
                      rb.setTextColor(Color.BLUE);
                      rb1.setTextColor(Color.BLACK);
                      break;
                  case R.id.rb1:
                      vp.setCurrentItem(1);
                      rb1.setTextColor(Color.BLUE);
                      rb.setTextColor(Color.BLACK);
                      break;
              }
            }
        });
    }
    class FragmentMyAdpter extends FragmentPagerAdapter {
        public FragmentMyAdpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
