package com.example.hp.jinritoutiao.bean;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/9 22 11
 */

public class Beann {
    private int tupian;
    private String text;

    public Beann(int tupian, String text) {
        this.tupian = tupian;
        this.text = text;
    }

    public int getTupian() {
        return tupian;
    }

    public void setTupian(int tupian) {
        this.tupian = tupian;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Beann{" +
                "tupian=" + tupian +
                ", text='" + text + '\'' +
                '}';
    }
}
