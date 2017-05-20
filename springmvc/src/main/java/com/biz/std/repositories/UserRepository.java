package com.biz.std.repositories;

import com.biz.std.model.Class;
import com.biz.std.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    @Query(value = "select u from User u where u.username=:username and u.password=:password")
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
