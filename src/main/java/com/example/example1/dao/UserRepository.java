package com.example.example1.dao;

import com.example.example1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Transactional
    @Modifying
    @Query("update User as u set u.email=?2 where u.name=?1")
    int updateEmailByName(String name,String email);

    @Transactional
    @Modifying
    @Query("delete from User as u where u.name=?1")
    void deleteByName(String name);
}
