package com.biz.std.service.impl;

import com.biz.std.model.User;
import com.biz.std.repositories.UserRepository;
import com.biz.std.service.UserService;
import com.biz.std.utils.BeanUtilsBean;
import com.biz.std.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

@Autowired
private UserRepository userRepository;

    public UserVO findByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username,password);
        UserVO userVO = new UserVO();
        if(user!=null){
            BeanUtilsBean.VOConvertPO(userVO,user);
        }
        return userVO;
    }
}