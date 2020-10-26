package com.thoughtworks.basictest.service;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:13
 * @Description: com.thoughtworks.basictest.service
 * @version: 1.0
 */

import com.thoughtworks.basictest.dto.EducationDto;
import com.thoughtworks.basictest.dto.UserDto;
import com.thoughtworks.basictest.exception.ErrorResponse;
import com.thoughtworks.basictest.exception.UserNotExistException;
import com.thoughtworks.basictest.pojo.Education;
import com.thoughtworks.basictest.pojo.User;
import com.thoughtworks.basictest.repository.EducationRepository;
import com.thoughtworks.basictest.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final EducationRepository educationRepository;


    public UserService(UserRepository userRepository,EducationRepository educationRepository){
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }


    public User findById(long id) {
      return userRepository.findById(id).orElseThrow(() -> new UserNotExistException(ErrorResponse.USER_NOT_FOUND));

    }

    public void deleteUser(Long id) {
        if (!userRepository.findById(id).isPresent()){
            throw  new UserNotExistException(ErrorResponse.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    public User saveUser(UserDto userDto) {
        User user = User.builder().age(userDto.getAge()).name(userDto.getName()).avatar(userDto.getAvatar())
                .description(userDto.getDescription()).build();
        return  userRepository.save(user);
    }

    public List<User> getUserList(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        return userRepository.findAll(pageable);
      /* return userRepository.findAll().stream().map(item->UserDto.builder().age(item.getAge()).avatar(item.getAvatar())
       .description(item.getDescription()).name(item.getName()).build()).collect(Collectors.toList());*/
    }

    public List<EducationDto> getUserEducation(Long id) {
        if (!userRepository.findById(id).isPresent()){
            throw  new UserNotExistException(ErrorResponse.USER_NOT_FOUND);
        }
        return educationRepository.findByUserId(id).stream().map(item-> EducationDto.builder().description(item.getDescription())
        .title(item.getTitle()).userId(item.getUser().getId()).year(item.getYear()).build()).collect(Collectors.toList());
    }

    public void addEducation(Long id, EducationDto educationDto) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()){
            throw  new UserNotExistException(ErrorResponse.USER_NOT_FOUND);
        }
        Education education = Education.builder().title(educationDto.getTitle()).description(educationDto.getDescription())
                .user(byId.get()).year(educationDto.getYear()).build();
        educationRepository.save(education);
    }

    public int updateUser(Long id, String name) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()){
            throw  new UserNotExistException(ErrorResponse.USER_NOT_FOUND);
        }
        return  userRepository.updateUser(id,name);

    }
}
