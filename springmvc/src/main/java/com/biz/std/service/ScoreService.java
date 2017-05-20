package com.biz.std.service;

import com.biz.std.vo.ScoreVO;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public interface ScoreService {
    //保存成绩
    public void saveScore(ScoreVO scoreVO);
    //根据学生的学号查出对应学科的成绩
    List<ScoreVO> getScoreByStu_Id(String stu_id);
    //根据课程号查询出选修了此课程学生的成绩
    List<ScoreVO> getScoreBySub_Id(Long sub_id);
    //根据学生id和课程id看成绩是否存在
    boolean scoreExis(String id, Long sid);
    //根据id删除成绩
    void deleteScore(Long id);
}
