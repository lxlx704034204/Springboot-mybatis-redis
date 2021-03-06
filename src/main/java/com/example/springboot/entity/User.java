package com.example.springboot.entity;

//import com.baomidou.mybatisplus.annotations.TableField;
//import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user")
//@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int age;

    private String name;


//    @Column(columnDefinition = "TIMESTAMP")  //数据库手动新增一条数据的时候 会自动新增“CURRENT_TIMESTAMP”
//    private Date createTime;

//    public List<User> userlist;


}
