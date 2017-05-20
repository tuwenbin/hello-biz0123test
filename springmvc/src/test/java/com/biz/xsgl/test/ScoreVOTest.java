package com.biz.xsgl.test;

import com.biz.std.model.Score;
import com.biz.std.service.ScoreService;
import com.biz.std.service.StudentService;
import com.biz.std.service.SubjectService;
import com.biz.std.vo.ScoreVO;
import com.biz.std.vo.StudentVO;
import com.biz.std.vo.SubjectVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by Administrator on 2017/5/5 0005.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-jpa.xml","classpath:spring-mvc.xml"})
public class ScoreVOTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ScoreService scoreService;


    @Test
    public void testSave(){
        ScoreVO scoreVO = new ScoreVO();

        StudentVO studentVO = this.studentService.getStudentById("001");
        SubjectVO subjectVO = this.subjectService.getSubjectVOById(1L);

        scoreVO.setStudentVO(studentVO);
        scoreVO.setSubjectVO(subjectVO);
        scoreVO.setMark(100F);

        this.scoreService.saveScore(scoreVO);

    }

    @Test
    public void testGetScoreByStu_Id(){
        List<ScoreVO> scoreVOList = this.scoreService.getScoreByStu_Id("001");
        System.out.println(scoreVOList.size());
    }

    @Test
    public void testGetScoreBySub_Id(){
        List<ScoreVO> scoreVOList = this.scoreService.getScoreBySub_Id(1L);
        System.out.println(scoreVOList.size());
    }

}
