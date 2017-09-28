package test.bwie.hp.buyuanxue;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hjm.bottomtabbar.BottomTabBar;

import test.bwie.hp.buyuanxue.fragment.FragmentA;
import test.bwie.hp.buyuanxue.fragment.FragmentB;
import test.bwie.hp.buyuanxue.fragment.FragmentC;

public class SecondActivity extends AppCompatActivity {
    private BottomTabBar mBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mBottomBar= (BottomTabBar) findViewById(R.id.mBottomBar);
        mBottomBar.init(getSupportFragmentManager())
                .setImgSize(90, 90)
                .setFontSize(12)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.GREEN, Color.RED)
                .addTabItem(" ",R.mipmap.index_checked,R.mipmap.index_unchecked,   FragmentA.class)
                .addTabItem("  ",  R.mipmap.recommand_checked,R.mipmap.recommand_unchecked, FragmentB.class)
                .addTabItem("   ", R.mipmap.mine_checked,R.mipmap.mine_unchecked,FragmentC.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener()
                 { @Override public void onTabChange(int position, String name)
                   {
                       if(position==2){

                       }
                    Log.i("TGA", "位置：" + position + " 选项卡：" + name);
                   }
                 });



    }
}
