package test.bwie.com.dongjing.presenter;

import java.io.File;

import test.bwie.com.dongjing.model.UploadImodel;
import test.bwie.com.dongjing.model.UploadModel;
import test.bwie.com.dongjing.view.UploadIView;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/18 13 25
 */

public class PresenterLoad {
    private UploadIView ul;
    private UploadModel um;
    public PresenterLoad(UploadIView ul) {
        this.ul = ul;
        um=new UploadImodel();
    }
    public void upload(File file,int uid){
         um.upload(file,uid);
    }
}
