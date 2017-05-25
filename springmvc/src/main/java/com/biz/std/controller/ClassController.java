package com.biz.std.controller;

import com.biz.std.service.ClassService;
import com.biz.std.service.ScoreService;
import com.biz.std.service.StudentService;
import com.biz.std.vo.ClassVO;
import com.biz.std.vo.ScoreVO;
import com.biz.std.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ClassService classService;

    private String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * 跳转到学生列表页面
     * @return
     */
    @RequestMapping("/toClassMain")
    public ModelAndView toClassMain(){
        List<ClassVO> classListVO = this.classService.findClassList();
        //遍历所有班级，根据班级cid,查询出所有的学生，再统计学生总数
        for(ClassVO classVO:classListVO){
            List<StudentVO> studentVOList = this.studentService.findStudentListByCid(classVO.getCid());
            //设置学生总数
            classVO.setStu_sum(studentVOList.size());
            //求出这个班级总分
            Float class_sum = 0F;
            //求出每个学生的总分
            for(StudentVO studentVO:studentVOList){
                //此学生总分
                Float grade_sum = 0F;
                List<ScoreVO> scoreListVO =  this.scoreService.getScoreByStu_Id(studentVO.getId());
                for(ScoreVO scoreVO:scoreListVO){
                    grade_sum = grade_sum + scoreVO.getMark();
                }
                class_sum = class_sum + grade_sum;
            }
            //设置班级平均分
            if(class_sum>0){
                classVO.setClass_mark_avg((float)(Math.round(class_sum/studentVOList.size()*10))/10);
            }else {
                classVO.setClass_mark_avg((float)(Math.round(class_sum/studentVOList.size()*10))/10);
            }

        }
        return new ModelAndView("/bjgl/classMain","classListVO",classListVO);
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping("/toClassAddForm")
    public ModelAndView addUI(){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("/bjgl/classAddForm");
        return mav;
    }

    /**
     * 新增班级
     * @param classVO
     * @return
     */
    @RequestMapping("/saveClass")
    public ModelAndView add(ClassVO classVO){
        ModelAndView mav = new ModelAndView();
        this.classService.saveClass(classVO);
        mav.setViewName("redirect:/toClassMain.action");
        return mav;
    }

    /**
     * 跳转到修改页面
     * @param cid
     * @return
     */
    @RequestMapping("/toClassUpdateForm")
    public ModelAndView updateUI(String cid){
        ModelAndView mav = new ModelAndView();
        ClassVO classVO = this.classService.getClassById(Long.parseLong(cid));
        mav.addObject("classVO",classVO);
        mav.setViewName("/bjgl/classUpdateForm");
        return mav;
    }

    /**
     * 修改班级
     * @param classVO
     * @return
     */
    @RequestMapping("/updateClass")
    public ModelAndView update(ClassVO classVO){
        ModelAndView mav = new ModelAndView();
        this.classService.updateClass(classVO);
        mav.setViewName("redirect:/toClassMain.action");
        return mav;
    }

    /**
     * 删除学科
     * @param cid
     * @return
     */
    @RequestMapping("/deleteClass")
    public ModelAndView delete(String cid){
        ModelAndView mav = new ModelAndView();
        //班级和学生之前有关联，报异常！
        try {
            this.classService.deleteClass(Long.parseLong(cid));
            mav.setViewName("redirect:/toClassMain.action");
            return mav;
        }catch (Exception e){
            List<String> stringList = new ArrayList<String>();
            String error = "请先删除该班级的学生";
            stringList.add(error);
            mav.addObject("stringList",stringList);
            mav.addObject("error",error);
            mav.setViewName("redirect:/toClassMain.action");
            return mav;
        }
    }
}
