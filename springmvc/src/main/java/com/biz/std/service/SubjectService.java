package com.biz.std.service;

import com.biz.std.vo.SubjectVO;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public interface SubjectService {
    //保存学科
    public void saveSubjectVO(SubjectVO subjectVO);
    //查询出所有的学科
    public List<SubjectVO> findSubjectVOList();
    //修改学科
    public void updateSubjectVO(SubjectVO subjectVO);
    //根据id删除学科
    public void deleteSubjectVO(Long id);
    //根据id查询学科
    public SubjectVO getSubjectVOById(Long id);

}
