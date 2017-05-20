package com.biz.std.service;

import com.biz.std.vo.ClassVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public interface ClassService {
    //保存班级
    public void saveClass(ClassVO classVO);
    //查询出所有的班级
    public List<ClassVO> findClassList();
    //修改班级
    public void updateClass(ClassVO classVO);
    //根据id删除班级
    public void deleteClass(Long id);
    //根据id查询班级
    public ClassVO getClassById(Long id);
}
