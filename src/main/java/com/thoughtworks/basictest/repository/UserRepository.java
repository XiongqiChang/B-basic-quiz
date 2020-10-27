package com.thoughtworks.basictest.repository;

import com.thoughtworks.basictest.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:04
 * @Description: com.thoughtworks.basictest.repository
 * @version: 1.0
 */

public interface UserRepository extends JpaRepository<User,Long> {

    Page<User> findAll(Pageable pageable);

    @Query(value = "update user as u set  u.name = ?2 where id = ?1",nativeQuery = true)
    @Modifying
    int updateUser(Long id, String name);
}
