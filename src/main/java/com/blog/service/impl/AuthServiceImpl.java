package com.blog.service.impl;

import com.blog.DTO.LoginDTO;
import com.blog.DTO.RegisterDTO;
import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.exception.BlogPostExceptions;
import com.blog.repository.RoleRepository;
import com.blog.repository.UserRepository;
import com.blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    // login
    private AuthenticationManager authenticationManager;

    // register
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /* ------------- LOGIN SERVICE ----------- */
    @Override
    public String login(LoginDTO loginDTO) {
       /* Authentication => contiene información sobre el usuario autenticado,
        * nombre de usuario, roles y credenciales de autenticación. */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsernameOrEmail(),
                        loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "Usuario logead correctamente!!";
    }

    /* ------------- REGISTER SERVICE ----------- */
    @Override
    public String register(RegisterDTO registerDTO) {
        // 1. comprobar si el username existe en DB
        if(userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new BlogPostExceptions(HttpStatus.BAD_REQUEST, "Nombre de usuario ya registrado!!.");
        }

        // 2. compruebo si el email existe en BD
        if(userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new BlogPostExceptions(HttpStatus.BAD_REQUEST, "Email ya registrado!!.");
        }

        // 3. Registro nuevo usuario
        User newUser = new User();
        newUser.setName(registerDTO.getName());
        newUser.setUsername(registerDTO.getUsername());
        newUser.setEmail(registerDTO.getEmail());
        // Bean que viene de securityConfig para codificar password en la BD.
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        // 4. Añado role al nuevo usuario
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        newUser.setRoles(roles);

        // 4. Guardo al nuevo usuario en la BD.
        userRepository.save(newUser);

        return "Usuario registrado correctamente!!";
    }
}
