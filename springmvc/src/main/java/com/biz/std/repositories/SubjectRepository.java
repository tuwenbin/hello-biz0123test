package com.biz.std.repositories;

import com.biz.std.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long>{
    /**
     * 根据id查询
     * @param id
     * @return
     */
    Subject findBySid(Long id);


}
