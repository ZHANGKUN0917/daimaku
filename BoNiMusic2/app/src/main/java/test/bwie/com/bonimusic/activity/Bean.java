package test.bwie.com.bonimusic.activity;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/16 18 42
 */

public class Bean {
    private int tp;
    private String info;

    public Bean(int tp, String info) {
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
        return "Bean{" +
                "tp=" + tp +
                ", info='" + info + '\'' +
                '}';
    }
}
