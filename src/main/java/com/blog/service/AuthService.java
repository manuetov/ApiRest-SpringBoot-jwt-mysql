package com.blog.service;

import com.blog.DTO.LoginDTO;
import com.blog.DTO.RegisterDTO;

public interface AuthService {

    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
