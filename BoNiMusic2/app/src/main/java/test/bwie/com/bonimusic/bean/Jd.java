package test.bwie.com.bonimusic.bean;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/12 10 44
 */

public class Jd {
    private int progress;


     @Override
    public String toString() {
        return "Jd{" +
                "progress=" + progress +
                '}';
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Jd(int progress) {

        this.progress = progress;
    }


}
