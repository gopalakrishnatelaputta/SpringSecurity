package com.jwt.security.Controller;

import com.jwt.security.model.JwtRequest;
import com.jwt.security.services.CustomUserDetailsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController{

    @Autowired
    private CustomUserDetailsservice customUserDetailsservice;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
   // private JwtUtil jwtUtil;
    @RequestMapping(value = "/token",method= RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest)
    {
        System.out.println(jwtRequest);
    return null;
    }
}
