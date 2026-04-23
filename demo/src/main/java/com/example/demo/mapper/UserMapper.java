package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE username = #{username} AND is_active = true")
    User findByUsername(String username);

    @Insert("INSERT INTO users(username, password, email, role, created_time) " +
            "VALUES(#{username}, #{password}, #{email}, #{role}, #{createdTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE users SET last_login = #{lastLogin} WHERE id = #{id}")
    void updateLastLogin(User user);

    @Select("SELECT COUNT(*) FROM users WHERE username = #{username}")
    int existsByUsername(String username);
}