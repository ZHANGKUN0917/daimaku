package test.bwie.com.dongjing.model;

import android.util.Log;

import com.bwie.okhttputils.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import test.bwie.com.dongjing.api.JieKou;
import test.bwie.com.dongjing.bean.Show;
import test.bwie.com.dongjing.presenter.IprsenterShow;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/18 18 56
 */

public class ModelShow implements IModelShow {
    private IprsenterShow is;

    public ModelShow(IprsenterShow is) {
        this.is = is;
    }

    Map<String,String> map=new HashMap<>();
    @Override
    public void showdata(int position, int pcid, int pscid) {
        map.put("page",1+"");
        map.put("sort",0+"");
        map.put("pscid",pscid+"");
        Log.i("llll",position+pcid+pscid+"");
        HttpUtils.doPost(JieKou.show, map, new HttpUtils.GsonObjectCallback<Show>() {
            @Override
            public void onUi(Show show) {
                Log.i("jjjj",show.toString());
                is.show(show);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
        
    }
}
