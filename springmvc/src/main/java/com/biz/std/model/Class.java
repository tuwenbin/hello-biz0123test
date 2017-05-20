package com.biz.std.model;

import javax.persistence.*;
import java.util.List;

/**
 * 班级表
 */
@Table(name = "class")
@Entity
public class Class {
    /**
     * 主键 班级id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long cid;

    /**
     * 班级名
     */
    private String cname;

    /**
     * 和学生之间一对多映射
     */
    @OneToMany(cascade = CascadeType.REFRESH,mappedBy="aclass")
    private List<Student> students;//多个学生

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
