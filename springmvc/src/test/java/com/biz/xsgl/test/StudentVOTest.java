package com.biz.xsgl.test;

import com.biz.std.model.Score;
import com.biz.std.service.StudentService;
import com.biz.std.vo.ClassVO;
import com.biz.std.vo.ScoreVO;
import com.biz.std.vo.StudentVO;
import com.biz.std.vo.SubjectVO;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentVOTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("/spring-jpa.xml");
    StudentService studentService = (StudentService) context.getBean("studentService");
    @Test
    public void testSave(){
        StudentVO studentVO;
        studentVO = new StudentVO();
        studentVO.setId("005");
        studentVO.setName("zs");
        studentVO.setSex("男");
        SimpleDateFormat  dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        try {
            studentVO.setBirthday(dateFormat.parse("2011-09-12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        this.studentService.saveStudent(studentVO);
    }

    @Test
    public void testFindAll(){
        List<StudentVO> studentVOList = this.studentService.findStudentList();
        System.out.print(studentVOList.size());
    }

    @Test
    public void testFindById(){
        StudentVO studentVO = this.studentService.getStudentById("001");
        Set<ScoreVO> scoreVOSet = studentVO.getScores();
        Iterator<ScoreVO> it = scoreVOSet.iterator();

        System.out.println(studentVO.getId());
    }


    @Test
    public void testFindStudentListByCid(){
        List<StudentVO> studentVOList = this.studentService.findStudentListByCid(4L);
        System.out.print(studentVOList.size());
    }



    @Test
    public  void testDelete(){
        this.studentService.deleteStudent("001");
    }

}
