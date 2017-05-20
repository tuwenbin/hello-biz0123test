package com.biz.xsgl.test;

import com.biz.std.model.Student;
import com.biz.std.service.ClassService;
import com.biz.std.service.StudentService;
import com.biz.std.vo.ClassVO;
import com.biz.std.vo.StudentVO;
import javassist.ClassPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-jpa.xml","classpath:spring-mvc.xml"})
public class ClassVOTest {
    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;


    @Test
    public void testSave(){
        ClassVO classVO = new ClassVO();
        classVO.setCname("8班");
        List<StudentVO> studentVOList = this.studentService.findStudentList();
        classVO.setStudentsVO(studentVOList);
        this.classService.saveClass(classVO);
    }

    @Test
    public void testFindAll(){
        List<ClassVO> classVOList = this.classService.findClassList();
        System.out.println(classVOList.size());
    }

    @Test
    public void testFindById(){
    }

    @Test
    public  void testDelete(){
    }
}
