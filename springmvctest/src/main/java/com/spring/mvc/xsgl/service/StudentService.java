package com.spring.mvc.xsgl.service;

import com.spring.mvc.xsgl.entity.Student;
import com.spring.mvc.xsgl.entity.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27 0027.
 */
public  interface StudentService {
    //保存学生
    public abstract void  saveStudent(Student student);
    //删除学生
    public void deleteStudent(String id);
    //查询出所有的学生
    public List<Student> findStudentList();
    //通过id获取学生
    public Student getStudentById(String id);
    //对学生信息分页的处理
    public Page findPageRecords(String num);
}
