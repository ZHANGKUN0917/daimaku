package test.bwie.com.dongjing.model;

import android.util.Log;

import com.bwie.okhttputils.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import test.bwie.com.dongjing.api.JieKou;
import test.bwie.com.dongjing.bean.Search;
import test.bwie.com.dongjing.presenter.LSerachPresenter;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/18 21 03
 */

public class SearchModel implements ISearchModel {
    private LSerachPresenter lp;

    public SearchModel(LSerachPresenter lp) {
        this.lp = lp;
    }

    Map<String,String> map=new HashMap<>();
    @Override
    public void searchdata(String key) {
        map.put("page",1+"");
        map.put("keywords",key);
        map.put("sort",0+"");
        HttpUtils.doPost(JieKou.search, map, new HttpUtils.GsonObjectCallback<Search>() {
            @Override
            public void onUi(Search search) {
                Log.i("111",search+"");
                Log.i("qwer",search.getMsg());
                lp.search(search);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
