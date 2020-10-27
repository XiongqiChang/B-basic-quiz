package com.thoughtworks.basictest.repository;

import com.thoughtworks.basictest.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_get_user_when_id_exist(){
        entityManager.persistAndFlush(User.builder()
                .name("xqc")
                .age(24L)
                .avatar("httpxxxxx")
                .description("xxxx")
                .build());
        Optional<User> user = userRepository.findById(1L);
        assertThat(user.isPresent()).isTrue();

    }


    @Test
    void should_list_all_user(){
        entityManager.persistAndFlush(User.builder()
                .name("xqc")
                .age(24L)
                .avatar("httpxxxxx")
                .description("xxxx")
                .build());
        entityManager.persistAndFlush(User.builder()
                .name("xqc")
                .age(24L)
                .avatar("httpxxxxx")
                .description("xxxx")
                .build());
        entityManager.persistAndFlush(User.builder()
                .name("xqc")
                .age(24L)
                .avatar("httpxxxxx")
                .description("xxxx")
                .build());
        List<User> all = userRepository.findAll();
        assertThat(all.size()).isEqualTo(3);

    }
}
