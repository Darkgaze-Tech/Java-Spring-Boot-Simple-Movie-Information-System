package com.project.movie.controller;

import com.project.movie.constant.AppConstant;
import com.project.movie.domain.dao.User;
import com.project.movie.domain.dto.LoginForm;
import com.project.movie.service.AuthService;
import com.project.movie.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody LoginForm form){
       return userService.register(form);
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToken (@RequestBody LoginForm form){
        return ResponseEntity.ok(userService.generateToken(form));
    }
}
