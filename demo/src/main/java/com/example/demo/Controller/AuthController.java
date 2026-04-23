package com.example.demo.Controller;

import com.example.demo.pojo.LoginRequest;
import com.example.demo.pojo.LoginResponse;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.AuthService;
import com.example.demo.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Result<LoginResponse>> login(@RequestBody LoginRequest request) {
        try {
            log.info("登录请求: username={}", request.getUsername());

            LoginResponse response = authService.login(request);

            if (response.getToken() != null) {
                log.info("登录成功: username={}", request.getUsername());
                return ResponseEntity.ok(Result.success(response.getMessage(), response));
            } else {
                log.warn("登录失败: username={}", request.getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Result.error(401, response.getMessage()));
            }
        } catch (Exception e) {
            log.error("登录接口异常", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.error("系统错误，请稍后重试"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Result<String>> register(@RequestBody User user) {
        try {
            authService.register(user);
            return ResponseEntity.ok(Result.success("注册成功"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Result.error(e.getMessage()));
        } catch (Exception e) {
            log.error("注册接口异常", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.error("注册失败，请稍后重试"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Result<String>> logout() {
        return ResponseEntity.ok(Result.success("登出成功"));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(HttpServletRequest request) {
        try {
            String token = extractToken(request);
            if (token != null && jwtUtil.validateToken(token)) {
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.ok(false);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Auth Service is healthy");
    }

    // 支持OPTIONS请求
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}