package test.bwie.com.bonimusic.activity;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/11 17 40
 */

public class BdBean {
    private int tp;
    private String info;

    public BdBean(int tp, String info) {
        this.tp = tp;
        this.info = info;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "tp=" + tp +
                ", info='" + info + '\'' +
                '}';
    }
}
