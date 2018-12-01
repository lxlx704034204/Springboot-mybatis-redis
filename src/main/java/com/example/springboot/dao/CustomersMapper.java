package com.example.springboot.dao;

import com.example.springboot.entity.Customers;
import com.example.springboot.entity.CustomersExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@Mapper
@CacheConfig(cacheNames = "customers")
public interface CustomersMapper {
    long countByExample(CustomersExample example);

    int deleteByExample(CustomersExample example);

    int deleteByPrimaryKey(String customerid);

    int insert(Customers record);

    int insertSelective(Customers record);
    
    @Cacheable( keyGenerator = "keyGenerator") 
    List<Customers> selectByExample(CustomersExample example);

    Customers selectByPrimaryKey(String customerid);

    int updateByExampleSelective(@Param("record") Customers record, @Param("example") CustomersExample example);

    int updateByExample(@Param("record") Customers record, @Param("example") CustomersExample example);

    int updateByPrimaryKeySelective(Customers record);

    int updateByPrimaryKey(Customers record);
}