package com.thoughtworks.basictest.service;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:13
 * @Description: com.thoughtworks.basictest.service
 * @version: 1.0
 */

import com.thoughtworks.basictest.dto.EducationDto;
import com.thoughtworks.basictest.dto.UserDto;
import com.thoughtworks.basictest.exception.ErrorResult;
import com.thoughtworks.basictest.exception.UserNotExistException;
import com.thoughtworks.basictest.pojo.Education;
import com.thoughtworks.basictest.pojo.User;
import com.thoughtworks.basictest.repository.EducationRepository;
import com.thoughtworks.basictest.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EducationRepository educationRepository;


    public UserService(UserRepository userRepository,EducationRepository educationRepository){
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }

    public User getUser(Long id) {
        Optional<User> userById = userRepository.getUserById(id);
        if (!userById.isPresent()){
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
            String errorDate = simpleDateFormat.format(date);
            throw  new UserNotExistException(new ErrorResult(errorDate, HttpStatus.NOT_FOUND.value(),"not found","输入的id有误"));
        }
        return  userById.get();
    }

    public Long add(UserDto userDto) {

        int size = userRepository.getUsers().size();

        User user = User.builder().name(userDto.getName()).age(userDto.getAge())
                .avatar(userDto.getAvatar()).description(userDto.getDescription()).id((long) (size + 1)).build();
        userRepository.addUser(user);
        return user.getId();
    }

    public List<Education> getUserEducations(Long id) {
        userNotExist(id);
        return educationRepository.getEducationList().stream()
                .filter(item -> item.getUserId().equals(id)).collect(Collectors.toList());
    }

    public void addEducation(Long id, EducationDto educationDto) {
        if (!userRepository.getUserById(id).isPresent()) {
            userNotExist(id);
        }
        Education education = Education.builder().description(educationDto.getDescription())
                .title(educationDto.getTitle()).userId(id).build();
        educationRepository.addEducation(education);
    }

    private void userNotExist(Long id) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
            String errorDate = simpleDateFormat.format(date);
            throw  new UserNotExistException(new ErrorResult(errorDate, HttpStatus.NOT_FOUND.value(),"not found","输入的id有误"));
    }
}
