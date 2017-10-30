package test.bwie.com.dongjing.presenter;

import test.bwie.com.dongjing.bean.Detail;
import test.bwie.com.dongjing.model.DetailModel;
import test.bwie.com.dongjing.model.IDetailModel;
import test.bwie.com.dongjing.view.IDetailView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/19 13 47
 */

public class DetailPresenter implements IDetailPresenter{
    private IDetailView  idv;
    private IDetailModel idm;

    public DetailPresenter(IDetailView idv) {
        this.idv = idv;
        idm=new DetailModel(this);
    }
    public void detail(int pid){
        idm.detaildata(pid);
    }

    @Override
    public void detail(Detail detail) {
        idv.detail(detail);
    }
}
