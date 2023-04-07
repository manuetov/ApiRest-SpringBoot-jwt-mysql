package com.blog.controller;

import com.blog.DTO.LoginDTO;
import com.blog.DTO.RegisterDTO;
import com.blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // inyecto servicio en el constructor como una dependencia
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping( value = {"/login", "/signin"})
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {

        String response = authService.login(loginDTO);

        return ResponseEntity.ok(response);
    }

    @RequestMapping( value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {

        return new ResponseEntity<>(authService.register(registerDTO), HttpStatus.CREATED);
    }

}
