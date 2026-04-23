package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.LoginRequest;
import com.example.demo.pojo.LoginResponse;
import com.example.demo.pojo.User;
import com.example.demo.service.AuthService;
import com.example.demo.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            // 查找用户
            User user = userMapper.findByUsername(request.getUsername());
            if (user == null) {
                log.warn("用户不存在: {}", request.getUsername());
                return new LoginResponse("用户名或密码错误");
            }

            // 验证密码
            if (!validatePassword(request.getPassword(), user.getPassword())) {
                log.warn("密码错误: {}", request.getUsername());
                return new LoginResponse("用户名或密码错误");
            }

            // 更新最后登录时间
            user.setLastLogin(LocalDateTime.now());
            userMapper.updateLastLogin(user);

            // 生成token
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

            log.info("用户登录成功: {}", user.getUsername());
            return new LoginResponse(token, user.getUsername(), user.getRole(), "登录成功");

        } catch (Exception e) {
            log.error("登录过程发生错误", e);
            return new LoginResponse("登录失败，请稍后重试");
        }
    }

    @Override
    public void register(User user) {
        // 检查用户名是否已存在
        if (userMapper.existsByUsername(user.getUsername()) > 0) {
            throw new RuntimeException("用户名已存在");
        }

        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 设置默认角色和创建时间
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        user.setCreatedTime(LocalDateTime.now());
        user.setIsActive(true);

        userMapper.insert(user);
        log.info("用户注册成功: {}", user.getUsername());
    }

    @Override
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}