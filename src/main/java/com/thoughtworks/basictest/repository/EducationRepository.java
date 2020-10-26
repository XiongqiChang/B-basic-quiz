package com.thoughtworks.basictest.repository;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 15:48
 * @Description: com.thoughtworks.basictest.repository
 * @version: 1.0
 */

import com.thoughtworks.basictest.pojo.Education;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EducationRepository extends CrudRepository<Education,Long> {


    List<Education> findByUserId(Long id);
}
