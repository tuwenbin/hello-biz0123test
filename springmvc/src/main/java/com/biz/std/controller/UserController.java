package com.biz.std.controller;

import com.biz.std.service.StudentService;
import com.biz.std.service.UserService;
import com.biz.std.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @return
     */
    @RequestMapping("/login")
    public String login(UserVO userVO, HttpServletRequest request){
        UserVO userVO1 = this.userService.findByUsernameAndPassword(userVO.getUsername(),userVO.getPassword());
        if(userVO1!=null&&userVO1.getUid()!=null){
            request.getSession().setAttribute("user",userVO1);
            return "redirect:/toMainIndex.action";
        }
        return "/error";
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/resige")
    public String login(HttpServletRequest request){
        //注销用户
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        return "/login1";
    }
}
