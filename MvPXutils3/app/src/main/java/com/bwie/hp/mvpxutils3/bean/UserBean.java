package com.bwie.hp.mvpxutils3.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/2 08 58
 */
@Table(name="student",onCreated = "")
public class UserBean {
    @Column(name="id",isId = true,autoGen = true,property = "NOT NULL")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="pwd")
    private String pwd;

    public UserBean(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public UserBean(int id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public UserBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
