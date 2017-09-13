package com.example.hp.jinritoutiao.bean;

import java.io.Serializable;
import java.util.List;

public class DataBean implements Serializable{

    private String title;
    private List<imgBean> image_list;
    private Video_detail_info video_detail_info;
    private String source;
      //视频 "share_url": "http://toutiao.com/a6452804463399797262/?iid=3642583580&app=news_article",

    private String share_url;

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Video_detail_info getVideo_detail_info() {
        return video_detail_info;
    }

    public void setVideo_detail_info(Video_detail_info video_detail_info) {
        this.video_detail_info = video_detail_info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<imgBean> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<imgBean> image_list) {
        this.image_list = image_list;
    }
}
