package com.example.springboot.dao;

import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getAll();

    User getOne(Integer id);

    int delete(Integer id);

    int update(@Param("voucher") User user);

    int insert(User user);

    User getByName(String userName);
}
