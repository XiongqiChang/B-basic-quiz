package com.thoughtworks.basictest.repository;

import com.thoughtworks.basictest.pojo.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:04
 * @Description: com.thoughtworks.basictest.repository
 * @version: 1.0
 */

public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll(Pageable pageable);

    @Query(value = "update user as u set  u.name = ?2 where id = ?1",nativeQuery = true)
    @Modifying
    int updateUser(Long id, String name);
}
