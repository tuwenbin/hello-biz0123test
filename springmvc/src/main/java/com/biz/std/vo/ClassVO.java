package com.biz.std.vo;


import java.util.List;

public class ClassVO {
    private Long cid;//班级主键
    private String cname;//班级名
    private Integer stu_sum;//学生总人数
    private Float class_mark_avg;//各个班级平均分
    private List<StudentVO> studentsVO;//多个学生

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

    public Integer getStu_sum() {
        return stu_sum;
    }

    public void setStu_sum(Integer stu_sum) {
        this.stu_sum = stu_sum;
    }

    public Float getClass_mark_avg() {
        return class_mark_avg;
    }

    public void setClass_mark_avg(Float class_mark_avg) {
        this.class_mark_avg = class_mark_avg;
    }

    public List<StudentVO> getStudentsVO() {
        return studentsVO;
    }

    public void setStudentsVO(List<StudentVO> studentsVO) {
        this.studentsVO = studentsVO;
    }
}
