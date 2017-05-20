package com.biz.std.vo;

import com.biz.std.model.Student;

import java.util.List;
import java.util.Set;

public class SubjectVO {
    /**
     * 主键
     */
    private Long sid;

    /**
     * 学科名
     */
    private String name;

    /**
     * 选修人数
     */
    private Integer take_subject_sum;

    /**
     * 选修此课程的平均分
     */
    private Float subject_mark_avg;
    /**
     * 和学科之间的一对多映射
     */
    private Set<ScoreVO> scores;
    /**
     * 和学生之间多对多映射
     */
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

    public Integer getTake_subject_sum() {
        return take_subject_sum;
    }

    public void setTake_subject_sum(Integer take_subject_sum) {
        this.take_subject_sum = take_subject_sum;
    }

    public Float getSubject_mark_avg() {
        return subject_mark_avg;
    }

    public void setSubject_mark_avg(Float subject_mark_avg) {
        this.subject_mark_avg = subject_mark_avg;
    }

    public Set<ScoreVO> getScores() {
        return scores;
    }

    public void setScores(Set<ScoreVO> scores) {
        this.scores = scores;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
