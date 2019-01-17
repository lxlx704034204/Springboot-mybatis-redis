package com.example.springboot.service.impl;

import static org.junit.Assert.*;

import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void getOne() {

        User one = userService.getOne(1);

        System.out.println("--test-1---->"+ one);
//        Assert.assertEquals("李1", one.getName());
//        //可以只使用 assertThat 一个断言语句，结合 Hamcrest 提供的匹配符，就可以表达全部的测试思想
//        //引入import static org.hamcrest.CoreMatchers.*;
//        Assert.assertThat(one.getName(), is("李1"));

    }

    @Test
    public void delete() {
        int one = userService.delete(1);
        System.out.println("--test-1---->"+ one);
    }

    @Test
    public void update() {
        User one = userService.getOne(1);
        one.setAge(2);
        User res = userService.update(one);
        System.out.println("--test-1---->"+ res);
    }


    @Test
    public void insert() {
        User u = new User();
        u.setId(2);
        u.setName("李2");
        u.setAge(21);
        User insert = userService.insert(u);
        System.out.println("--test-1---->"+ insert);
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getByName() {
        User insert = userService.getByName("李2");
        System.out.println("--test-1---->"+ insert);
    }

}