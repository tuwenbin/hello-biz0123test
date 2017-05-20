package com.biz.std.model;

import javax.persistence.*;

/**
 * 成绩表
 */
@Table(name = "score")
@Entity
public class Score{

    /**
     * 成绩表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 引用学生表
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stu_id")
    private Student student;

    /**
     * 引用课程表
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sub_id")
    private Subject subject;

    /**
     * 成绩
     */
    private Float mark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }
}