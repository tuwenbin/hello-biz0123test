package com.biz.std.service;

import com.biz.std.vo.UserVO;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public interface UserService {
    //根据id查询用户
    public UserVO findByUsernameAndPassword(String username,String password);


}
