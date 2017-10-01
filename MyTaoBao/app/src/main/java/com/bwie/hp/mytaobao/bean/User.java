package com.bwie.hp.mytaobao.bean;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/9/13 14 16
 */

public class User {
    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * error : 用户名已存在
         */

        private String error;
        private String username;
        private String userid;
        private String key;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
