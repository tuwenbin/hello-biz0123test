package com.biz.std.service.impl;

import com.biz.std.model.Class;
import com.biz.std.model.Score;
import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.repositories.StudentRepository;
import com.biz.std.service.StudentService;
import com.biz.std.utils.BeanUtilsBean;
import com.biz.std.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
            Set<Score> scoreSetPO = studentPO.getScores();
            for(Score scorePO:scoreSetPO){
                ScoreVO scoreVO = new ScoreVO();
                BeanUtilsBean.VOConvertPO(scoreVO,scorePO);
                //转化学科
                Subject subjectPO = scorePO.getSubject();
                SubjectVO subjectVO = new SubjectVO();
                BeanUtilsBean.VOConvertPO(subjectVO,subjectPO);
                scoreVO.setSubjectVO(subjectVO);
                scoreVOSet.add(scoreVO);
            }

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

    public PageStu findPageRecords(String num) {
        //传过来的页码
        int pageNum = 1;
        if(num != null && !num.trim().equals("")){
            pageNum = Integer.parseInt(num);
        }
        //得到总记录数
        int totalRecords =  this.studentRepository.findAll().size();
        PageStu pageStu = new PageStu(pageNum,totalRecords);

        Page<Student> studentPage = this.studentRepository.findAllStudent(new PageRequest(pageStu.getCurrentPageNum()-1,pageStu.getPageSize(),null));
        List<Student> studentList = studentPage.getContent();
        Iterator<Student> studentPOList = studentList.iterator();
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
            Set<Score> scoreSetPO = studentPO.getScores();
            for(Score scorePO:scoreSetPO){
                ScoreVO scoreVO = new ScoreVO();
                BeanUtilsBean.VOConvertPO(scoreVO,scorePO);
                //转化学科
                Subject subjectPO = scorePO.getSubject();
                SubjectVO subjectVO = new SubjectVO();
                BeanUtilsBean.VOConvertPO(subjectVO,subjectPO);
                scoreVO.setSubjectVO(subjectVO);
                scoreVOSet.add(scoreVO);
            }

            studentVO.setScores(scoreVOSet);
            studentVO.setSubjectVOList(subjectListVO);
            studentVOList.add(studentVO);
        }
        pageStu.setRecords(studentVOList);
        return pageStu;
    }

}
