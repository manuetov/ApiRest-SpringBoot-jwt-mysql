package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // se usa para personalizar accesos a rutas y roles
public class SecurityConfig {

    // inyecto el interface que recupera los detalles del usuario en el contructor como dependencia
    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // interfaz proporcionada por Spring Security para cifrar y descifrar contraseñas
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /* interface para autenticar usuarios. Usará UserDetailsService() para traer los datos del usuario desde la BD.
    *  y también usará PasswordEncoder() para la contraseña */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /* interface define cadena de filtros de seguridad para permitir o denegar solicitudes.
        * cambia el login en forma de pop up */
        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        // authorize.anyRequest().authenticated())
                        authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                .requestMatchers( "/api/auth/**").permitAll()
                                .anyRequest().authenticated()
                );

                /*).httpBasic(Customizer.withDefaults());*/

        return httpSecurity.build();
    }



/*    // creo dos usuarios en memoria con diferentes roles
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails manu = User.builder()
                .username("manu")
                .password(passwordEncoder().encode("manu"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(manu, admin);
    }*/

}
