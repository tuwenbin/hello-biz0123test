package com.biz.std.vo;


import com.biz.std.model.Subject;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class ScoreVO {
    private Long id;
    private StudentVO studentVO;
    private SubjectVO subjectVO;
    private Float mark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentVO getStudentVO() {
        return studentVO;
    }

    public void setStudentVO(StudentVO studentVO) {
        this.studentVO = studentVO;
    }

    public SubjectVO getSubjectVO() {
        return subjectVO;
    }

    public void setSubjectVO(SubjectVO subjectVO) {
        this.subjectVO = subjectVO;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }
}
