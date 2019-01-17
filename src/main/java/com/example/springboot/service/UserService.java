package com.example.springboot.service;

import com.example.springboot.entity.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;

public interface UserService {
    User getOne(Integer id);

    User update(User user);

    int delete(Integer id);

    User insert(User user);

    //含有CachePut注解，所以执行这个方法时一定会查询数据库，及时有cacheable注解
    @Caching(
            cacheable = {@Cacheable(value="common1",key="#userName")},
            put = {
                    @CachePut(value="common1",key="#result.id"),
                    @CachePut(value="common1",key="#result.name")
            }
    )
    User getByName(String userName);

    List<User> getAll();
}
