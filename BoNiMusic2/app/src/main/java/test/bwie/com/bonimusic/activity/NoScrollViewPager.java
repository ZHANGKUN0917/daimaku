package test.bwie.com.bonimusic.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.bonimusic.fragment.FragmentA;
import test.bwie.com.bonimusic.fragment.FragmentB;
import test.bwie.com.bonimusic.fragment.FragmentC;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/11 09 34
 */

public class NoScrollViewPager extends ViewPager {

    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;
    private static List<Fragment> list1;
    private boolean noScroll = false;
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        // TODO Auto-generated constructor stub
    }
    private void init(Context context) {
        fragmentA=new FragmentA();
        fragmentB=new FragmentB();
        fragmentC=new FragmentC();
        list1=new ArrayList<>();
        list1.add(fragmentA);
        list1.add(fragmentB);
        list1.add(fragmentC);
    }
    public NoScrollViewPager(Context context) {
        super(context);
    }
    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }
    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }
    public static class MyBase extends FragmentPagerAdapter {

        public MyBase(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list1.get(position);
        }

        @Override
        public int getCount() {
            return list1.size();
        }
    }
}
