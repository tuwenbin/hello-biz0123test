package com.biz.std.vo;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
public class UserVO {

    private Long uid;

    /**
     * 班级名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
