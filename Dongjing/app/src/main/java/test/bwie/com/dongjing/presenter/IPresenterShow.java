package test.bwie.com.dongjing.presenter;

import test.bwie.com.dongjing.bean.Show;
import test.bwie.com.dongjing.model.IModelShow;
import test.bwie.com.dongjing.model.ModelShow;
import test.bwie.com.dongjing.view.IViewShow;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/18 18 55
 */

public class IPresenterShow implements IprsenterShow{
    private IViewShow ivs;
    private IModelShow iis;

    public IPresenterShow(IViewShow ivs) {
        this.ivs = ivs;
        iis=new ModelShow(this);
    }
    public void show(int position,int pcid,int pscid){

        iis.showdata(position,pcid,pscid);
    }

    @Override
    public void show(Show db) {
        ivs.show(db);
    }
}
