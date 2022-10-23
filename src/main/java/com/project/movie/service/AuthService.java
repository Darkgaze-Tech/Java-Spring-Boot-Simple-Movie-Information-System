package com.project.movie.service;

import com.project.movie.config.JwtTokenProvider;
import com.project.movie.config.SecurityConfig;
import com.project.movie.constant.AppConstant;
import com.project.movie.domain.dao.User;
import com.project.movie.domain.dto.LoginForm;
import com.project.movie.domain.dto.TokenResponse;
import com.project.movie.domain.dto.UserDto;
import com.project.movie.repository.UserRepository;
import com.project.movie.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityConfig securityConfig;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public ResponseEntity<Object> register(LoginForm form) {
        try {
            log.info("Registering...");
            if (userRepository.findByUsername(form.getUsername()) != null){
                return ResponseUtil.build(AppConstant.ResponseCode.USER_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else {
                User user = mapper.map(form, User.class);
                user.setUsername(form.getUsername());
                user.setEmail(form.getEmail());
                user.setPassword(this.bCryptPasswordEncoder.encode(form.getPassword()));
                user.setActive(form.isActive());
                user.setRole(form.getRole());
                user.setAccountNonExpired(true);
                user.setAccountNonLocked(true);
                user.setCredentialsNonExpired(true);
                userRepository.save(user);
                return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, mapper.map(user, UserDto.class), HttpStatus.OK);
            }
        }catch (Exception e){
            log.error("Got an error when saving new user. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public TokenResponse generateToken(LoginForm form) {
        try {
            Authentication authentication = securityConfig.authenticationManagerBean().authenticate(new UsernamePasswordAuthenticationToken(
                    form.getUsername(), form.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Get current user role by username
            User user = userRepository.findByUsername(form.getUsername());

            String jwt = jwtTokenProvider.generateToken(authentication, user.getRole());
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(jwt);
            return tokenResponse;
        }catch (BadCredentialsException ex){
            log.error("Bad Credentials", ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

}