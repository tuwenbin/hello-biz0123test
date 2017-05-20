package com.biz.xsgl.test;

import com.biz.std.model.Subject;
import com.biz.std.service.StudentService;
import com.biz.std.service.SubjectService;
import com.biz.std.vo.SubjectVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by Administrator on 2017/5/5 0005.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-jpa.xml","classpath:spring-mvc.xml"})
public class SubjectVOTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;

    @Test
    public void testSave(){
        SubjectVO subjectVO = new SubjectVO();
        subjectVO.setName("数学");
        this.subjectService.saveSubjectVO(subjectVO);
    }

    @Test
    public void testFindAll(){
    }

    @Test
    public void testFindById(){
        SubjectVO subjectVO = this.subjectService.getSubjectVOById(1L);
        System.out.println(subjectVO.getName());
    }

    @Test
    public  void testDelete(){
    }
}
