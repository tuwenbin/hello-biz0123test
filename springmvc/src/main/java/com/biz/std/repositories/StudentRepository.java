package com.biz.std.repositories;

import com.biz.std.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    /**
     * 根据id查询
     * @return
     */
    Student findById(String id);

    /**
     * 查询出一个班级的所有学生
     */
    @Query(value = "select s from Student s where s.aclass.cid =:cid")
    List<Student> findAllByCid(@Param("cid") Long cid);

    /**
     * 查询出一个班级的所有学生
     * @return
     */
    @Query(value = "select s from  Student  s order by s.aclass.cid")
    List<Student> findAll();

    /**
     * 查询出一个学科的学生
     * @param sid
     * @return
     */
    @Query(value = "select s from Student s join s.subjects sub where sub.sid=:sid")
    List<Student> findAllBySubjects_sid(@Param("sid") Long sid);

    @Query(value = "select s from Student s  order by s.aclass.cid")
    List<Student> findAllByStudentOrderByClass();

    /**
     * 分页显示学生
     */
    @Query(value = "select s from Student s  order by s.aclass.cid")
    Page<Student> findAllStudent(Pageable pageable);


}
