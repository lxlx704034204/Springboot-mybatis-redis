package com.exmple.springboot.service;

import java.util.List;

import com.example.springboot.entity.Customers;
import com.example.springboot.entity.CustomersExample;


public interface CustomersService {
	
	public List<Customers> selectByExample(CustomersExample example);

}
