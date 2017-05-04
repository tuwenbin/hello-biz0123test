package com.spring.mvc.xsgl.service.impl;

import com.spring.mvc.xsgl.dao.StudentDao;
import com.spring.mvc.xsgl.entity.Student;
import com.spring.mvc.xsgl.service.StudentService;
import com.spring.mvc.xsgl.entity.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/4/27 0027.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource(name="studentDao")
    private StudentDao studentDao;

    public void saveStudent(Student student) {
        this.studentDao.saveStudent(student);
    }

    public void deleteStudent(String id) {
        this.studentDao.deleteStudent(id);
    }

    public List<Student> findStudentList() {
        return this.studentDao.findStudentList();
    }

    public Student getStudentById(String id) {
        return this.studentDao.getStudentById(id);
    }

    public Page findPageRecords(String num) {
        return this.studentDao.findPageRecords(num);
    }

}
