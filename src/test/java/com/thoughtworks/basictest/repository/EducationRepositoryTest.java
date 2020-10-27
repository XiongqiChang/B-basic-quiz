package com.thoughtworks.basictest.repository;

import com.thoughtworks.basictest.pojo.Education;
import com.thoughtworks.basictest.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class EducationRepositoryTest {
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private TestEntityManager entityManager;


    @Test
    void findByUserId(){
        entityManager.persistAndFlush(User.builder()
                .name("xqc")
                .age(24L)
                .avatar("httpxxxxx")
                .description("xxxx")
                .build());
        entityManager.persistAndFlush(Education.builder().user(User.builder().description("测试").avatar("ce").name("xqc").id(1L).age(25L).build())
                .title("test1").description("这是测试").year(2005L).build());
        List<Education> byUserId = educationRepository.findByUserId(1L);
        assertThat(byUserId.size()).isEqualTo(1);
    }

}
