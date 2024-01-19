package com.backend.project.config;

import com.backend.project.entity.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;

    // User Creation
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService(getPasswordEncoder());
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authorized)->
           authorized.requestMatchers("/auth/**").permitAll()
                   .requestMatchers("/v3/api-docs", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**","/v3/api-docs/**").permitAll()
                   .requestMatchers("/api/**").hasRole("ADMIN")
                   .anyRequest().authenticated()
        );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authFilter,UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.cors(cors->cors.disable());
        http.csrf(csrf->csrf.disable());
        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}
