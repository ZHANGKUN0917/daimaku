package test.bwie.com.bonimusic.bean;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/16 19 27
 */

public class IBean {
    private int i;

    public IBean(int i) {
        this.i = i;
    }
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    @Override
    public String toString() {
        return "IBean{" +
                "i=" + i +
                '}';
    }
}
