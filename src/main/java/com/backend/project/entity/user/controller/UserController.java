package com.backend.project.entity.user.controller;

import com.backend.project.entity.user.model.AuthRequest;
import com.backend.project.entity.user.model.UserInfo;
import com.backend.project.entity.user.model.UserInfoDetails;
import com.backend.project.entity.user.model.UserLoginResponse;
import com.backend.project.entity.user.service.UserInfoService;
import com.backend.project.jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private AuthenticationProvider authenticationProvider;


    @PostMapping("/create")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            String token = jwtTokenUtil.generateToken(authRequest.getUsername());

            return UserLoginResponse.builder()
                    .token(token)
                    .userDetails((UserInfoDetails) authentication.getPrincipal())
                    .build();
        }else{
            throw new UsernameNotFoundException("invalid user request");
        }
    }





}
