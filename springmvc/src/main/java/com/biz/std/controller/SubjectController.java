package com.biz.std.controller;

import com.biz.std.service.ClassService;
import com.biz.std.service.ScoreService;
import com.biz.std.service.StudentService;
import com.biz.std.service.SubjectService;
import com.biz.std.vo.ScoreVO;
import com.biz.std.vo.StudentVO;
import com.biz.std.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SubjectController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ClassService classService;
    @Autowired
    private SubjectService subjectService;

    private String sid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 跳转到课程列表页面
     * @return
     */
    @RequestMapping("/toSubjectMain")
    public ModelAndView toSubjectMain(){
        List<SubjectVO> subjectVOList = this.subjectService.findSubjectVOList();
        for(SubjectVO subjectVO:subjectVOList){
            //根据课程id查询出所有的学生
            List<StudentVO> studentVOList = this.studentService.findStudentListBySid(subjectVO.getSid());
            //得到选修本课程的人数
            subjectVO.setTake_subject_sum(studentVOList.size());
            //选修此课程的总分
            Float sub_sum = 0F;
            //根据课程id查询所有的分数
            List<ScoreVO> scoreVOList = this.scoreService.getScoreBySub_Id(subjectVO.getSid());
            for(ScoreVO scoreVO:scoreVOList){
                sub_sum = sub_sum + scoreVO.getMark();
            }
            //设置课程平均分
            subjectVO.setSubject_mark_avg((float)(Math.round(sub_sum/studentVOList.size()*10))/10);
        }
        return new ModelAndView("/kcgl/subjectMain","subjectVOList",subjectVOList);
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping("/toSubjectAddForm")
    public ModelAndView addUI(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("/kcgl/subjectAddForm");
        return mav;
    }

    /**
     * 新增学科
     * @param subjectVO
     * @return
     */
    @RequestMapping("/saveSubject")
    public ModelAndView add(SubjectVO subjectVO){
        ModelAndView mav = new ModelAndView();
        this.subjectService.saveSubjectVO(subjectVO);
        mav.setViewName("redirect:/toSubjectMain.action");
        return mav;
    }

    /**
     * 跳转到修改页面
     * @param sid
     * @return
     */
    @RequestMapping("/toSubjectUpdateForm")
    public ModelAndView updateUI(String sid){
        ModelAndView mav = new ModelAndView();
        SubjectVO subjectVO = this.subjectService.getSubjectVOById(Long.parseLong(sid));
        mav.addObject("subjectVO",subjectVO);
        mav.setViewName("/kcgl/subjectUpdateForm");
        return mav;
    }

    /**
     * 修改学科
     * @param subjectVO
     * @return
     */
    @RequestMapping("/updateSubject")
    public ModelAndView update(SubjectVO subjectVO){
        ModelAndView mav = new ModelAndView();
        this.subjectService.updateSubjectVO(subjectVO);
        mav.setViewName("redirect:/toSubjectMain.action");
        return mav;
    }

    /**
     * 删除学科
     * @param sid
     * @return
     */
    @RequestMapping("/deleteSubject")
    public ModelAndView delete(String sid){
        ModelAndView mav = new ModelAndView();
        try {
            this.subjectService.deleteSubjectVO(Long.parseLong(sid));
            mav.setViewName("redirect:/toSubjectMain.action");
            return mav;
        }catch (Exception e){
            mav.setViewName("redirect:/toSubjectMain.action");
            return mav;
        }
    }
}
