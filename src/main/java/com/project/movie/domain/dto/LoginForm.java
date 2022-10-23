package com.project.movie.domain.dto;

import lombok.Data;

@Data
public class LoginForm {
    private String username;
    private String email;
    private String password;
    private boolean active;
    private String role;
}

