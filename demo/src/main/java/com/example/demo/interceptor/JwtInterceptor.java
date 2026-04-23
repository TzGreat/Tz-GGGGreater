package com.example.demo.interceptor;

import com.example.demo.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行登录和注册接口
        String requestURI = request.getRequestURI();
        log.info("请求路径:{}", requestURI);
        return true;
//        if (requestURI.startsWith("/auth/") || requestURI.equals("/error")) {
//            return true;
//        }
//
//        // 检查token
//        String token = extractToken(request);
//        if (token == null || !jwtUtil.validateToken(token)) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("{\"code\":401,\"message\":\"未授权访问\"}");
//            return false;
//        }
//
//        // 将用户信息存入请求属性，方便后续使用
//        String username = jwtUtil.getUsernameFromToken(token);
//        String role = jwtUtil.getRoleFromToken(token);
//        request.setAttribute("username", username);
//        request.setAttribute("role", role);
//
//        return true;
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}