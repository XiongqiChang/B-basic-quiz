package com.thoughtworks.basictest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 13:56
 * @Description: com.thoughtworks.basictest.pojo
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    private  String name;

    private Long age;

    private String avatar;

    private String description;

  /*  @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE, mappedBy = "user")
    private  List<Education> educationList;*/

}
