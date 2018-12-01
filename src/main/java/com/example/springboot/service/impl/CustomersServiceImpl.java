package com.example.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.dao.CustomersMapper;
import com.example.springboot.entity.Customers;
import com.example.springboot.entity.CustomersExample;
import com.example.springboot.service.CustomersService;

@Service("CustomersService")
public class CustomersServiceImpl implements CustomersService {
	
	@Autowired
	private CustomersMapper customersMapper;

	@Override
	public List<Customers> selectByExample(CustomersExample example) {
		// TODO Auto-generated method stub
		return customersMapper.selectByExample(example);
	}

}
