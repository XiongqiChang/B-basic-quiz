package com.thoughtworks.basictest.repository;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:04
 * @Description: com.thoughtworks.basictest.repository
 * @version: 1.0
 */

import com.thoughtworks.basictest.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    public List<User> users = new ArrayList<>();

    public UserRepository(){

        users.add(new User(1L,"xqc",20L,"https://i.dlpng.com/static/png/6681915_preview.png",
                "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"));
        users.add(new User(2L,"xqc2",21L,"https://i.dlpng.com/static/png/6681915_preview.png",
                "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"));
    }

    public Optional<User> getUserById(Long id){
        return users.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public void addUser(User user){
        users.add(user);
    }

    public List<User> getUsers(){
        return users;
    }

}
