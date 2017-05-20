package com.biz.std.repositories;

import com.biz.std.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class,Long>{
    /**
     * 根据id查询
     * @return
     */
    Class findByCid(Long id);
}
