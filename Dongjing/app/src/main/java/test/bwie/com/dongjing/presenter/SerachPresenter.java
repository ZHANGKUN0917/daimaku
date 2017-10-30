package test.bwie.com.dongjing.presenter;

import test.bwie.com.dongjing.bean.Search;
import test.bwie.com.dongjing.model.ISearchModel;
import test.bwie.com.dongjing.model.SearchModel;
import test.bwie.com.dongjing.view.ISerachView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/18 21 02
 */

public class SerachPresenter implements LSerachPresenter{
    private ISerachView isv;
    private ISearchModel ism;

    public SerachPresenter(ISerachView isv) {
        this.isv = isv;
        ism=new SearchModel(this);
    }
    public void search(String key){
       ism.searchdata(key);
    }

    @Override
    public void search(Search sh) {
        isv.Searchdata(sh);
    }
}
