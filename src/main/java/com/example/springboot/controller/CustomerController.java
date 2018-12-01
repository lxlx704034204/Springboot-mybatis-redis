package com.example.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entity.*;
import com.example.springboot.service.CustomersService;

@RestController
@RequestMapping("/Customers")
public class CustomerController {
	@Autowired
    private CustomersService customersService;

    @RequestMapping("/ShowCustomers")
    public List<Customers> toIndex(){
        CustomersExample example = new CustomersExample();
        example.createCriteria().andCustomeridLike("%A%");
        List<Customers> customerslist = this.customersService.selectByExample(example);
        return customerslist;
    }
}
