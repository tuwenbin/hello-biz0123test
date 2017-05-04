package com.spring.mvc.xsgl.Controller;

import com.spring.mvc.xsgl.entity.Student;
import com.spring.mvc.xsgl.service.StudentService;
import com.spring.mvc.xsgl.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * Created by Administrator on 2017/4/27 0027.
 */
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/goStudentWelcome")
    public String login(){
        return "studentWelcome";
    }

    /**
     * 展示所有的学生
     * @param num
     * @return
     */
    @RequestMapping("/goStudentMain")
    public ModelAndView showStudents(String num){
        Page page = this.studentService.findPageRecords(num);
        return new ModelAndView("/studentMain","page",page);
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/goStudentAddForm")
    public  String addUI(){
        return  "studentAddForm";
    }

    /**
     * 添加学生
     * @param student
     * @return
     */
    @RequestMapping("/addStudent")
    public String add(Student student){
        String id = UUID.randomUUID().toString();
        student.setId(id);
        this.studentService.saveStudent(student);
        return  "redirect:/goStudentMain.action";
    }

    /**
     * 跳转到添加页面
     * @param deleteIds
     * @return
     */
    @RequestMapping("/goStudentUpdateForm")
    public ModelAndView updateUI(String deleteIds){
        Student student = this.studentService.getStudentById(deleteIds);
        return new ModelAndView("/studentUpdateForm","student",student);
    }

    /**
     * 修改学生
     * @param student
     * @return
     */
    @RequestMapping("/updateStudent")
    public String update(Student student){
        this.studentService.saveStudent(student);
        return "redirect:/goStudentMain.action";
    }

    /**
     * 删除一个学生数据
     * @param deleteIds
     * @return
     */
    @RequestMapping("deleteStudent")
    public String delete(String deleteIds){
        this.studentService.deleteStudent(deleteIds);
        return "redirect:/goStudentMain.action";
    }
    /**
     * 删除选中的多个学生数据
     * @param deleteIds
     * @return
     */
    @RequestMapping("deleteAllStudent")
    public String deleteAll(String deleteIds){
        String[] idss = deleteIds.split(",");
        if(idss.length>0){
            for(int i=0;i<idss.length;i++){
                String s = idss[i];
                this.studentService.deleteStudent(s);
            }
        }
        return "redirect:/goStudentMain.action";
    }
}
