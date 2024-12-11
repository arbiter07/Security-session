package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.User;

@Mapper
public interface UserMapper {

    List<User> findAll();
    
    @Select("SELECT * FROM users where username = #{username}")
    User findByUserName(@Param("username") String username);
    
    @Insert("INSERT INTO users(username, password, role) values (#{user.username}, #{user.password}, #{user.role} ) ")
    void save(@Param("user") User user);

}
