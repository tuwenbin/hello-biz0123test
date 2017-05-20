package com.biz.std.service;


import com.biz.std.vo.StudentVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public interface StudentService {

    //保存学生
    public void saveStudent(StudentVO student);
    //查询出所有的学生
    public List<StudentVO> findStudentList();
    //修改学生
    public void updateStudent(StudentVO student,String stuId);
    //根据id删除学生
    public void deleteStudent(String id);
    //根据id查询学生
    public StudentVO getStudentById(String id);
    //根据班级的id查出所有的学生
    List<StudentVO> findStudentListByCid(Long cid);
    //根据课程id查询出所有的学生
    List<StudentVO> findStudentListBySid(Long sid);
}
