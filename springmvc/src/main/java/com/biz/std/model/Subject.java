package com.biz.std.model;


import javax.persistence.*;
import java.util.List;

/**
 * 课程表
 */
@Table(name = "subject")
@Entity
public class Subject {
    /**
     * 主键 课程号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private Long sid;

    /**
     * 学科名
     */
    @Column(length = 20)
    private String name;



    /**
     * 和学生之间多对多映射
     */
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "subjects")
    private List<Student> students;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
