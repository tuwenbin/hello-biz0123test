package com.biz.xsgl.test;

import com.biz.std.model.*;
import com.biz.std.model.Class;
import com.biz.std.repositories.*;
import com.biz.std.service.ClassService;
import com.biz.std.service.StudentService;
import com.biz.std.vo.ClassVO;
import com.biz.std.vo.StudentVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-jpa.xml","classpath:spring-mvc.xml"})
public class ResiTest {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave(){
        Student student = new Student();
        student.setId("002");
        student.setName("张三");

        List<Subject> subjects = new ArrayList<Subject>();
        Subject subject = new Subject();
        subject.setSid(1L);
        subject.setName("语文");
        subjects.add(subject);

        Class classs = new Class();
        classs.setCid(1L);
        classs.setCname("语文班");

        student.setAclass(classs);
        student.setSubjects(subjects);

        this.studentRepository.save(student);
    }

    @Test
    public void testSaveSubject(){


        Subject subject = new Subject();
        subject.setSid(1L);
        subject.setName("英语");

        List<Student> studentList = new ArrayList<Student>();
        Student student = new Student();
        student.setId("003");
        student.setName("李四");
        studentList.add(student);

        Class classs = new Class();
        classs.setCid(1L);
        classs.setCname("英语班");
        student.setAclass(classs);

        subject.setStudents(studentList);
        this.subjectRepository.save(subject);
    }

    @Test
    public  void  saveScore(){
        Score score = new Score();
        score.setId(1L);

        Student student = this.studentRepository.findById("002");
        Subject subject = this.subjectRepository.findBySid(1L);

        score.setStudent(student);
        score.setSubject(subject);
        score.setMark(90F);

        this.scoreRepository.save(score);

    }

    @Test
    public  void getScoreByStu_Id(){
        List<Score> scoreList=  this.scoreRepository.findAllByStu_id("001");
        System.out.println(scoreList.size());
    }

    @Test
    public  void getScoreBySub_Id(){
        List<Score> scoreList=  this.scoreRepository.findAllBySub_id(1L);
        System.out.println(scoreList.size());
    }

    @Test
    public  void testfindClassList(){
        System.out.println(this.classRepository.findAll().size());
    }

    @Test
    public  void testfindStudentList(){
        List<Student> studentList = this.studentRepository.findAll();
        System.out.println(studentList.size());
    }

    @Test
    public  void testfindStudentListByCid(){
        List<Student> studentList = this.studentRepository.findAllByCid(4L);
        System.out.println(studentList.size());
    }

    @Test
    public  void testfindAllSubjects(){
        List<Student> studentList = this.studentRepository.findAllBySubjects_sid(1L);
        System.out.println(studentList.size());
    }

    @Test
    public void testFindSubjectById(){
        Subject subject = this.subjectRepository.findBySid(1L);
        System.out.println(subject);
    }

    @Test
    public void testfindBySub_idANDStu_id(){
        Score score = this.scoreRepository.findBySub_idANDStu_id("0010",2L);
        System.out.println(score);
    }

    @Test
    public void testfindUser(){
        User user = this.userRepository.findByUsernameAndPassword("1","1");
        System.out.println(user);
    }

    @Test
    public void findAllByStudentOrderByClass(){
        List<Student> studentList = this.studentRepository.findAllByStudentOrderByClass();
        System.out.println(studentList);
    }

    @Test
    public void deleteByStu_id(){
        this.scoreRepository.delete(1L);
    }
}
