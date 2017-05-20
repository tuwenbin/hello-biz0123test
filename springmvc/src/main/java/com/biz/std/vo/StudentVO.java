package com.biz.std.vo;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public class StudentVO {
    private String id;
    private String name;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String filePath;
    //平均分
    private Float grade_avg;
    private ClassVO classVO;
    private Set<ScoreVO> scores;
    private List<SubjectVO> subjectVOList;

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

    public Float getGrade_avg() {
        return grade_avg;
    }

    public void setGrade_avg(Float grade_avg) {
        this.grade_avg = grade_avg;
    }

    public ClassVO getClassVO() {
        return classVO;
    }

    public Set<ScoreVO> getScores() {
        return scores;
    }

    public void setScores(Set<ScoreVO> scores) {
        this.scores = scores;
    }

    public void setClassVO(ClassVO classVO) {
        this.classVO = classVO;
    }

    public List<SubjectVO> getSubjectVOList() {
        return subjectVOList;
    }

    public void setSubjectVOList(List<SubjectVO> subjectVOList) {
        this.subjectVOList = subjectVOList;
    }
}
