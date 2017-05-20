package com.biz.std.service.impl;

import com.biz.std.model.Class;
import com.biz.std.model.Score;
import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.repositories.StudentRepository;
import com.biz.std.service.StudentService;
import com.biz.std.utils.BeanUtilsBean;
import com.biz.std.vo.ClassVO;
import com.biz.std.vo.ScoreVO;
import com.biz.std.vo.StudentVO;
import com.biz.std.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public void saveStudent(StudentVO student){
        Student studentPO = new Student();
        Class classPO = new Class();
        List<Subject> subjects = new ArrayList<Subject>();
        if(student.getSubjectVOList()!=null&&student.getSubjectVOList().size()>0){
            Iterator<SubjectVO> subjectVOList = student.getSubjectVOList().iterator();
            while (subjectVOList.hasNext()){
                Subject subjectPO = new Subject();
                SubjectVO subjectVO = subjectVOList.next();
                BeanUtilsBean.VOConvertPO(subjectPO,subjectVO);
                subjects.add(subjectPO);
            }
        }
        //保存班级
        if(student.getClassVO()!=null){
            BeanUtilsBean.VOConvertPO(classPO,student.getClassVO());
            studentPO.setAclass(classPO);
        }
        BeanUtilsBean.VOConvertPO(studentPO,student);
        studentPO.setSubjects(subjects);
        this.studentRepository.save(studentPO);
    }

    public List<StudentVO> findStudentList() {
        Iterator<Student> studentPOList = this.studentRepository.findAllByStudentOrderByClass().iterator();
        List<StudentVO> studentVOList = new ArrayList<StudentVO>();

        while (studentPOList.hasNext()){
            StudentVO studentVO = new StudentVO();
            ClassVO classVO = new ClassVO();
            Student studentPO = studentPOList.next();
            BeanUtilsBean.VOConvertPO(classVO,studentPO.getAclass());
            studentVO.setClassVO(classVO);
            BeanUtilsBean.VOConvertPO(studentVO,studentPO);
            List<Subject> subjectListPO =  studentPO.getSubjects();
            List<SubjectVO> subjectListVO = new ArrayList<SubjectVO>();
            for(Subject subjectPO:subjectListPO){
                SubjectVO subjectVO = new SubjectVO();
                BeanUtilsBean.VOConvertPO(subjectVO,subjectPO);
                subjectListVO.add(subjectVO);
            }
            //转化成绩
            Set<ScoreVO> scoreVOSet = new HashSet<ScoreVO>();

            studentVO.setScores(scoreVOSet);
            studentVO.setSubjectVOList(subjectListVO);
            studentVOList.add(studentVO);
        }

        return studentVOList;
    }

    @Transactional
    public void updateStudent(StudentVO student,String stuId) {
        Student studentPO = new Student();
        Class classPO = new Class();
        List<Subject> subjects = new ArrayList<Subject>();
        if(student.getSubjectVOList()!=null&&student.getSubjectVOList().size()>0){
            Iterator<SubjectVO> subjectVOList = student.getSubjectVOList().iterator();
            while (subjectVOList.hasNext()){
                Subject subjectPO = new Subject();
                SubjectVO subjectVO = subjectVOList.next();
                BeanUtilsBean.VOConvertPO(subjectPO,subjectVO);
                subjects.add(subjectPO);
            }
        }
        //保存班级
        if(student.getClassVO()!=null){
            BeanUtilsBean.VOConvertPO(classPO,student.getClassVO());
            studentPO.setAclass(classPO);
        }
        BeanUtilsBean.VOConvertPO(studentPO,student);
        studentPO.setSubjects(subjects);
        this.studentRepository.save(studentPO);
        this.studentRepository.delete(stuId);
    }

    @Transactional
    public void deleteStudent(String id) {
        this.studentRepository.delete(id);
    }

    public StudentVO getStudentById(String id) {
        Student studentPO = this.studentRepository.findById(id);
        StudentVO studentVO = new StudentVO();
        ClassVO classVO = new ClassVO();
        BeanUtilsBean.VOConvertPO(classVO,studentPO.getAclass());
        BeanUtilsBean.VOConvertPO(studentVO,studentPO);
        //转化学科
        List<Subject> subjectListPO =  studentPO.getSubjects();
        List<SubjectVO> subjectListVO = new ArrayList<SubjectVO>();
        for(Subject subjectPO:subjectListPO){
            SubjectVO subjectVO = new SubjectVO();
            BeanUtilsBean.VOConvertPO(subjectVO,subjectPO);
            subjectListVO.add(subjectVO);
        }
        studentVO.setSubjectVOList(subjectListVO);
        studentVO.setClassVO(classVO);
        return studentVO;
    }

    public List<StudentVO> findStudentListByCid(Long cid) {
        Iterator<Student> studentPOList = this.studentRepository.findAllByCid(cid).iterator();
        List<StudentVO> studentVOList = new ArrayList<StudentVO>();
        List<SubjectVO> subjectListVO = new ArrayList<SubjectVO>();

        while (studentPOList.hasNext()){
            StudentVO studentVO = new StudentVO();
            ClassVO classVO = new ClassVO();
            Student studentPO = studentPOList.next();
            BeanUtilsBean.VOConvertPO(classVO,studentPO.getAclass());
            studentVO.setClassVO(classVO);
            BeanUtilsBean.VOConvertPO(studentVO,studentPO);
            List<Subject> subjectListPO =  studentPO.getSubjects();
            for(Subject subjectPO:subjectListPO){
                SubjectVO subjectVO = new SubjectVO();
                BeanUtilsBean.VOConvertPO(subjectVO,subjectPO);
                subjectListVO.add(subjectVO);
            }
            studentVO.setSubjectVOList(subjectListVO);
            studentVOList.add(studentVO);
        }

        return studentVOList;
    }

    public List<StudentVO> findStudentListBySid(Long sid) {
        Iterator<Student> studentPOList = this.studentRepository.findAllBySubjects_sid(sid).iterator();
        List<StudentVO> studentVOList = new ArrayList<StudentVO>();
        List<SubjectVO> subjectListVO = new ArrayList<SubjectVO>();

        while (studentPOList.hasNext()){
            StudentVO studentVO = new StudentVO();
            ClassVO classVO = new ClassVO();
            Student studentPO = studentPOList.next();
            BeanUtilsBean.VOConvertPO(classVO,studentPO.getAclass());
            studentVO.setClassVO(classVO);
            BeanUtilsBean.VOConvertPO(studentVO,studentPO);
            List<Subject> subjectListPO =  studentPO.getSubjects();
            for(Subject subjectPO:subjectListPO){
                SubjectVO subjectVO = new SubjectVO();
                BeanUtilsBean.VOConvertPO(subjectVO,subjectPO);
                subjectListVO.add(subjectVO);
            }
            studentVO.setSubjectVOList(subjectListVO);
            studentVOList.add(studentVO);
        }

        return studentVOList;
    }
}
