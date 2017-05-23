package com.biz.std.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 学生表
 */
@Table(name = "student")
@Entity
public class Student{

    /**
     * 主键 学号
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 姓名
     */
    @Column(length = 20)
    private String name;

    /**
     * 性别
     */
    @Column(length = 2)
    private String sex;

    /**
     * 学生图片的路径
     */
    @Column
    private String filePath;

    /**
     * 和班级的关联
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name = "class_id")
    private Class aclass;

    /**
     * 和成绩的关联
     */
    @OneToMany(cascade = CascadeType.MERGE,mappedBy="student",fetch = FetchType.EAGER)
    private Set<Score> scores;

    /**
     * 出生日期
     */
    @Column(length = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;


    /**
     * 和学科的关联
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_subject",joinColumns = {
            @JoinColumn(name = "student_id",referencedColumnName = "id")},inverseJoinColumns = {
            @JoinColumn(name = "subject_id",referencedColumnName = "sid")
    })
    private List<Subject> subjects;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Class getAclass() {
        return aclass;
    }

    public void setAclass(Class aclass) {
        this.aclass = aclass;
    }
}
