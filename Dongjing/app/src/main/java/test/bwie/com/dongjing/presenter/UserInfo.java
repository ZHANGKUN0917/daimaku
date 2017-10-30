package test.bwie.com.dongjing.presenter;

import test.bwie.com.dongjing.model.InfoIModel;
import test.bwie.com.dongjing.model.InfoModel;
import test.bwie.com.dongjing.view.InfoView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/18 14 21
 */

public class UserInfo implements IUserinfo{
    private InfoView iv;
    private InfoIModel iim;

    public UserInfo(InfoView iv) {
        this.iv = iv;
        iim=new InfoModel(this);
    }
    public void info(int uid){
       iim.Info(uid);
    }

    @Override
    public void info(String icon, String name, String nick) {
        iv.info(icon, name, nick);
    }
}
