package test.bwie.com.bonimusic.bean;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/12 13 17
 */

public class Lj {
    private String lj;

    @Override
    public String toString() {
        return "Lj{" +
                "lj='" + lj + '\'' +
                '}';
    }

    public String getLj() {
        return lj;
    }

    public void setLj(String lj) {
        this.lj = lj;
    }

    public Lj(String lj) {

        this.lj = lj;
    }
}
