package test.bwie.com.fifteenminutes.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/11/1 14 50
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(setview());
        //加载视图
        initview();
        //加载数据
        initdata();
    }
    public abstract void initview();
    public abstract  void initdata();
    public abstract int setview();



}
