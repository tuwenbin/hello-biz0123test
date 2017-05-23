package com.biz.std.service.impl;

import com.biz.std.model.Score;
import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.repositories.ScoreRepository;
import com.biz.std.repositories.StudentRepository;
import com.biz.std.repositories.SubjectRepository;
import com.biz.std.service.ScoreService;
import com.biz.std.utils.BeanUtilsBean;
import com.biz.std.vo.ScoreVO;
import com.biz.std.vo.StudentVO;
import com.biz.std.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * 保存成绩 并级联保存课程和学生
     * @param scoreVO
     */
    @Transactional
    public void saveScore(ScoreVO scoreVO) {
        Score scorePO = new Score();
        Student student = this.studentRepository.findById(scoreVO.getStudentVO().getId());
        Subject subject = this.subjectRepository.findBySid(scoreVO.getSubjectVO().getSid());
        scorePO.setSubject(subject);
        scorePO.setStudent(student);
        scorePO.setId(scoreVO.getId());
        scorePO.setMark(scoreVO.getMark());
        this.scoreRepository.save(scorePO);
    }

    public List<ScoreVO> getScoreByStu_Id(String stu_id) {
        List<Score> scoreListPO =  this.scoreRepository.findAllByStu_id(stu_id);
        List<ScoreVO> scoreListVO = new ArrayList<ScoreVO>();
        //遍历scoreListPO，将其转化为VO
        for(Score scorePO:scoreListPO){
            SubjectVO subjectVO = new SubjectVO();
            BeanUtilsBean.VOConvertPO(subjectVO,scorePO.getSubject());

            StudentVO studentVO = new StudentVO();
            BeanUtilsBean.VOConvertPO(studentVO,scorePO.getStudent());
            //保存scoreVO
            ScoreVO scoreVO = new ScoreVO();
            scoreVO.setId(scorePO.getId());
            scoreVO.setMark(scorePO.getMark());
            scoreVO.setSubjectVO(subjectVO);
            scoreVO.setStudentVO(studentVO);
            scoreListVO.add(scoreVO);
        }

        return scoreListVO;
    }

    public List<ScoreVO> getScoreBySub_Id(Long sub_id) {
        List<Score> scoreListPO =  this.scoreRepository.findAllBySub_id(sub_id);
        List<ScoreVO> scoreListVO = new ArrayList<ScoreVO>();
        //遍历scoreListPO，将其转化为VO
        for(Score scorePO:scoreListPO){
            SubjectVO subjectVO = new SubjectVO();
            BeanUtilsBean.VOConvertPO(subjectVO,scorePO.getSubject());

            StudentVO studentVO = new StudentVO();
            BeanUtilsBean.VOConvertPO(studentVO,scorePO.getStudent());
            //保存scoreVO
            ScoreVO scoreVO = new ScoreVO();
            scoreVO.setId(scorePO.getId());
            scoreVO.setMark(scorePO.getMark());
            scoreVO.setSubjectVO(subjectVO);
            scoreVO.setStudentVO(studentVO);
            scoreListVO.add(scoreVO);
        }
        return scoreListVO;
    }

    public boolean scoreExis(String id, Long sid) {
        boolean flag = false;
        Score score =  this.scoreRepository.findBySub_idANDStu_id(id, sid);
        if(score!=null){
            flag = true;
        }
        return flag;
    }

    public void deleteScore(Long id) {
        boolean flag = this.scoreRepository.exists(id);
        if(flag){
            this.scoreRepository.delete(id);
        }

    }


}
