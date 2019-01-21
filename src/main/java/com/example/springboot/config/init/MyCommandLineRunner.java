package com.example.springboot.config.init;

import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Component
public class MyCommandLineRunner implements  CommandLineRunner { //InitializingBean

    @Autowired
    UserService userService;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("... boot初始化...start...");
        List<User> all = userService.getAll();
        if(CollectionUtils.isEmpty(all)){
            User user=new User();
            user.setId(1);
            user.setName("李1");
            user.setAge(1);
            User insert = userService.insert(user);
            System.out.println("... 11...插入条数：" + insert);
        }

        System.out.println("... boot初始化...end...");

    }
}
