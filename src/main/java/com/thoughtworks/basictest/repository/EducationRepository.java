package com.thoughtworks.basictest.repository;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 15:48
 * @Description: com.thoughtworks.basictest.repository
 * @version: 1.0
 */

import com.thoughtworks.basictest.pojo.Education;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EducationRepository {

    private List<Education> educationList = new ArrayList<>();

    public EducationRepository(){
        educationList.add(new Education(1L,2005L,"Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus.",
                "Secondary school specializing in artistic"));
        educationList.add(new Education(1L,2006L,"Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus.",
                "Secondary school specializing in artistic"));
    }

    public List<Education> getEducationList(){
        return educationList;
    }

    public void addEducation(Education education) {
        educationList.add(education);
    }
}
