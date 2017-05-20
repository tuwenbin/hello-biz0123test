package com.biz.std.repositories;

import com.biz.std.model.Score;
import com.biz.std.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Long>{
    /**
     * 根据id查询
     * @return
     */
    Score findById(Long id);

    /**
     * 根据学生学号查询
     */
    @Query(value = "select s from Score s where s.student.id =:stu_id")
    List<Score> findAllByStu_id(@Param("stu_id") String stu_id);

    /**
     * 根据课程号查询
     */
    @Query(value = "select s from Score s where s.subject.sid =:sub_id")
    List<Score> findAllBySub_id(@Param("sub_id") Long sub_id);

    /**
     * 根据课程号和学生id查询
     * @param sub_id
     * @param stu_id
     * @return
     */
    @Query(value = "select s from Score s where s.student.id=:stu_id and s.subject.sid =:sub_id")
    Score findBySub_idANDStu_id(@Param("stu_id") String stu_id, @Param("sub_id") Long sub_id);

    @Query(value = "delete from Score s where s.student.id=:stu_id")
    void deleteAllByStu_id(@Param("stu_id") String stu_id);
}
