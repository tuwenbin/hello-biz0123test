package com.biz.std.model;

import javax.persistence.*;

/**
 * 班级表
 */
@Table(name = "user")
@Entity
public class User {
    /**
     * 主键 班级id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false)
    private Long uid;

    /**
     * 班级名
     */
    @Column
    private String username;

    /**
     * 密码
     */
    @Column
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
