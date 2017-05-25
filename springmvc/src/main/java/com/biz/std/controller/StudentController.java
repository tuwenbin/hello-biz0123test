package com.biz.std.controller;

import com.biz.std.service.ClassService;
import com.biz.std.service.ScoreService;
import com.biz.std.service.StudentService;
import com.biz.std.service.SubjectService;
import com.biz.std.utils.StringUtile;
import com.biz.std.vo.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ClassService classService;
    @Autowired
    private SubjectService subjectService;

    //学生ID
    private String stuId;
    //课程id
    private String sid;
    private String num;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    /**
     * 跳转到学生列表页面
     * @return
     */
    @RequestMapping("/toStudentMain")
    public ModelAndView toStudentMain(String num){
        ModelAndView mav = new ModelAndView();
        //得到分页记录
        PageStu page = this.studentService.findPageRecords(num);
        List<StudentVO> studentVOList = page.getRecords();
        for(StudentVO studentVO:studentVOList){
            //求平均分
            Float grade_avg = 0F;
           List<ScoreVO> scoreListVO =  this.scoreService.getScoreByStu_Id(studentVO.getId());
           if(scoreListVO.size()>0){
               for(ScoreVO scoreVO:scoreListVO){
                   grade_avg = grade_avg + scoreVO.getMark();
               }
               //保存成绩分均分为一位小数
               studentVO.setGrade_avg( (float)(Math.round(grade_avg/scoreListVO.size()*10))/10);
           }else {
               studentVO.setGrade_avg(grade_avg);
           }
        }
        List<SubjectVO> subjectVOList =  this.subjectService.findSubjectVOList();
        mav.addObject("page",page);
        mav.addObject("studentVOList",studentVOList);
        mav.addObject("subjectVOList",subjectVOList);
        mav.setViewName("/xsgl/studentMain");
        return mav;
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/toStudentAddForm")
    public ModelAndView addUI(){
        ModelAndView mav = new ModelAndView();
        List<ClassVO> classVOList = this.classService.findClassList();
        mav.addObject("classVOList",classVOList);
        mav.setViewName("/xsgl/studentAddForm");
        return mav;
    }

    /**
     * 添加学生
     * @param studentVO
     * @return
     */
    @RequestMapping("/addStudent")
    public ModelAndView add(StudentVO studentVO, HttpServletRequest request,@RequestParam("file") MultipartFile tmpFile){
        ModelAndView mav = new ModelAndView();
        Long cid = Long.parseLong(request.getParameter("cla"));
        ClassVO classVO =  this.classService.getClassById(cid);
        studentVO.setClassVO(classVO);

        //保存上传的图片
        if (tmpFile != null) {
            // 获取物理路径
            String className = studentVO.getClassVO().getCname();
            String studentName = studentVO.getId();
            String targetDirectory = request.getSession().getServletContext().getRealPath("/uploads")+"/"+className+"/"+studentName;

            String tmpFileName = tmpFile.getOriginalFilename(); // 上传的文件名
            int dot = tmpFileName.lastIndexOf('.');
            String ext = "";  //文件后缀名
            if ((dot > -1) && (dot < (tmpFileName.length() - 1))) {
                ext = tmpFileName.substring(dot + 1);
            }
            // 其他文件格式不处理
            if ("png".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext) || "gif".equalsIgnoreCase(ext)) {
                // 重命名上传的文件名
                String targetFileName = StringUtile.renameFileName(tmpFileName);
                // 保存的新文件
                File target = new File(targetDirectory, targetFileName);

                try {
                    // 保存文件
                    FileUtils.copyInputStreamToFile(tmpFile.getInputStream(),target);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                studentVO.setFilePath("/uploads/"+className+"/"+studentName+"/"+targetFileName);
            }
        }
        this.studentService.saveStudent(studentVO);
        mav.setViewName("redirect:/toStudentMain.action");
        return mav;
    }

    /**
     * 跳转到修改页面，并回显学生数据
     * @param stuId
     * @return
     */
    @RequestMapping("/toStudentUpdateForm")
    public ModelAndView updateUI(String stuId){
        ModelAndView mav = new ModelAndView();
        //查询出学生和班级的信息，放入ModelAndView中
        StudentVO studentVO =  this.studentService.getStudentById(stuId);
        List<ClassVO> classVOList = this.classService.findClassList();
        mav.addObject("studentVO",studentVO);
        mav.addObject("classVOList",classVOList);
        mav.setViewName("/xsgl/studentUpdateForm");
        return mav;
    }

    /**
     * 保存修改的学生数据
     * @return
     */
    @RequestMapping("/updateStudent")
    public ModelAndView update(StudentVO studentVO,HttpServletRequest request,String stuId,@RequestParam("file") MultipartFile tmpFile){
        ModelAndView mav = new ModelAndView();
        Long cid = Long.parseLong(request.getParameter("cla"));
        ClassVO classVO =  this.classService.getClassById(cid);
        studentVO.setClassVO(classVO);

        //保存上传的图片
        if (tmpFile != null) {
            // 获取物理路径
            String className = studentVO.getClassVO().getCname();
            String studentName = studentVO.getId();
            String targetDirectory = request.getSession().getServletContext().getRealPath("/uploads")+"/"+className+"/"+studentName;

            String tmpFileName = tmpFile.getOriginalFilename(); // 上传的文件名
            int dot = tmpFileName.lastIndexOf('.');
            String ext = "";  //文件后缀名
            if ((dot > -1) && (dot < (tmpFileName.length() - 1))) {
                ext = tmpFileName.substring(dot + 1);
            }
            // 其他文件格式不处理
            if ("png".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext) || "gif".equalsIgnoreCase(ext)) {
                // 重命名上传的文件名
                String targetFileName = StringUtile.renameFileName(tmpFileName);
                // 保存的新文件
                File target = new File(targetDirectory, targetFileName);

                try {
                    // 保存文件
                    FileUtils.copyInputStreamToFile(tmpFile.getInputStream(),target);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                studentVO.setFilePath("/uploads/"+className+"/"+studentName+"/"+targetFileName);
            }
        }

        this.studentService.saveStudent(studentVO);
        mav.setViewName("redirect:/toStudentMain.action");
        return mav;
    }

    /**
     * 根据学生id删除学生
     * @return
     */
    @RequestMapping("/deleteStudent")
    public String delete(String stuId){
        try {
            List<ScoreVO> scoreVOList = this.scoreService.getScoreByStu_Id(stuId);
            if(scoreVOList.size()>0){
                for(ScoreVO scoreVO:scoreVOList){
                    this.scoreService.deleteScore(scoreVO.getId());
                }
            }
            this.studentService.deleteStudent(stuId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/toStudentMain.action";
    }

    /**
     * 录入成绩
     * @return
     */
    @RequestMapping("/toScoreMain")
    public ModelAndView toScoreMain(String stuId){
        ModelAndView mav = new ModelAndView();
        //查询出该学生各个科目对应的成绩
        List<ScoreVO> scoreVOList =  this.scoreService.getScoreByStu_Id(stuId);
        mav.addObject("scoreVOList",scoreVOList);
        mav.setViewName("/xsgl/scoreMain");
        return mav;
    }

    /**
     * json对象回显成绩，没实现
     * @return
     */
    @RequestMapping("/toScore")
    @ResponseBody
    public List<ScoreVO> toScore(String stuId){
        //查询出该学生各个科目对应的成绩
        List<ScoreVO> scoreVOList =  this.scoreService.getScoreByStu_Id(stuId);
        return scoreVOList;
    }

    /**
     * 保存录入的成绩
     */
    @RequestMapping("/saveScore")
    public String saveScore(HttpServletRequest request,String stuId){
        StudentVO studentVO = this.studentService.getStudentById(stuId);
        //遍历学生所选的科目，保存科目成绩
        List<SubjectVO> subjectVOList = studentVO.getSubjectVOList();
        List<ScoreVO> scoreVOList =  this.scoreService.getScoreByStu_Id(stuId);
        for(SubjectVO subjectVO:subjectVOList){
            boolean flag = this.scoreService.scoreExis(stuId,subjectVO.getSid());
            //成绩表中有记录
            if(flag){
                for(ScoreVO scoreVO:scoreVOList){
                    //成绩表中有记录
                    if(subjectVO.getName().equals(scoreVO.getSubjectVO().getName())){
                        String mark = request.getParameter(scoreVO.getSubjectVO().getName());
                        scoreVO.setMark(Float.parseFloat(mark));
                        this.scoreService.saveScore(scoreVO);
                    }
                }
            }else {
                //成绩表中没有记录
                ScoreVO scoreVO1 = new ScoreVO();

                String mark = request.getParameter(subjectVO.getName());
                scoreVO1.setMark(Float.parseFloat(mark));
                scoreVO1.setStudentVO(studentVO);
                scoreVO1.setSubjectVO(subjectVO);
                this.scoreService.saveScore(scoreVO1);
            }
        }
        return "redirect:/toStudentMain.action";
    }

    /**
     * 选课功能
     * @param stuId
     * @param sid
     * @return
     */
    @ResponseBody
    @RequestMapping("/toSubject")
    public String toSubject(String stuId, String sid){
        Long s_id = Long.parseLong(sid);
        boolean flag = true;
        StudentVO studentVO = this.studentService.getStudentById(stuId);
        List<SubjectVO> subjectVOList = studentVO.getSubjectVOList();
        //此课程是否已经被选择
        for(SubjectVO subjectVO:subjectVOList){
            if(subjectVO.getSid()==s_id){
                flag = false;
            }
        }
        if(flag){
            //得到要保存的课程
            SubjectVO subjectVO = this.subjectService.getSubjectVOById(s_id);
            subjectVOList.add(subjectVO);
            studentVO.setSubjectVOList(subjectVOList);
            this.studentService.saveStudent(studentVO);
            return "success";
        }
        return "exisSubject";
    }
    /**
     * 取消选课
     * @param stuId
     * @param sid
     * @return
     */
    @ResponseBody
    @RequestMapping("/toNotSubject")
    public String tonotoSubject(String stuId, String sid){
        Long s_id = Long.parseLong(sid);
        Long score_id = 0L;
        StudentVO studentVO = this.studentService.getStudentById(stuId);
        List<ScoreVO> scoreVOList = this.scoreService.getScoreByStu_Id(stuId);
        List<SubjectVO> subjectVOList = studentVO.getSubjectVOList();
        if(subjectVOList.size()>0){
            if(subjectVOList.size()>0){
                for(int i=0;i<subjectVOList.size();i++){
                    SubjectVO subjectVO = subjectVOList.get(i);
                    if(subjectVO.getSid()==s_id){
                        //删除成绩记录
                        if(scoreVOList.size()>0){
                            for(ScoreVO scoreVO : scoreVOList){
                                if(scoreVO.getSubjectVO().getSid().equals(s_id)){
                                    score_id = scoreVO.getId();
                                }
                            }
                        }
                        subjectVOList.remove(i);
                    }
                }
            }
        }
        studentVO.setSubjectVOList(subjectVOList);
        this.studentService.saveStudent(studentVO);
        this.scoreService.deleteScore(score_id);
        return "success";

    }
}
