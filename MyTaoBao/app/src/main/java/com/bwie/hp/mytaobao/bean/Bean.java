package com.bwie.hp.mytaobao.bean;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/4 16 57
 */

public class Bean {
    private int tupian;
    private String title;

    public Bean(int tupian, String title) {
        this.tupian = tupian;
        this.title = title;
    }

    public int getTupian() {
        return tupian;
    }

    public void setTupian(int tupian) {
        this.tupian = tupian;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "tupian=" + tupian +
                ", title='" + title + '\'' +
                '}';
    }
}
