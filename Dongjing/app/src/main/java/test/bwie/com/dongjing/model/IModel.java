package test.bwie.com.dongjing.model;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/17 19 51
 */

public interface IModel {
    void Fgetdata();
    void Fgetdata1(int position,int cid);
    void RegisterData(String phone,String pwd);
    void LoginData(String phone,String pwd);
}
