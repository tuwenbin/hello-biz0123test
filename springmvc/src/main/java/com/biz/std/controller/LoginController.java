package com.biz.std.controller;

import com.biz.std.model.User;
import com.biz.std.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
@Controller
public class LoginController {
    /**
     * 跳转到系统的主页面
     * @return
     */
    @RequestMapping("/toMainIndex")
    public ModelAndView toMainIndex(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        UserVO userVO =  (UserVO) request.getSession().getAttribute("user");
        mav.addObject("user",userVO);
        mav.setViewName("/frame/index");
        return mav;
    }
    /**
     * 跳转到top.jsp
     * @return
     */
    @RequestMapping("/toTop")
    public String toTop(){
        return "/frame/top";
    }

    /**
     * 跳转到main.jsp
     * @return
     */
    @RequestMapping("/toMain")
    public String toMain(){
        return "/frame/main";
    }
}
