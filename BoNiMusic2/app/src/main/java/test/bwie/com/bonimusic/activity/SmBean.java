package test.bwie.com.bonimusic.activity;

import java.io.Serializable;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/11 19 06
 */

public class SmBean implements Serializable{

    private String name;
    private String author;
    private int duration;
    private String album;
    private String url;
    private String title;
    private int size;

    public SmBean(String name, String author, int duration, String album, String url, String title, int size) {
        this.name = name;
        this.author = author;
        this.duration = duration;
        this.album = album;
        this.url = url;
        this.title = title;
        this.size = size;
    }

    public SmBean() {
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SmBean{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", duration=" + duration +
                ", album='" + album + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }
}
