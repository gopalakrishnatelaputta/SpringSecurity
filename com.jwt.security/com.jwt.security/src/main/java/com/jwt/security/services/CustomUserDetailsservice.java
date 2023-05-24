package com.jwt.security.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.swing.*;
import java.util.ArrayList;

public class CustomUserDetailsservice implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("Gopalakrishna"))
        {
            return new User("GopalaKrishna","GopalaKrishna",new ArrayList<>());

        }
        else {
            throw new UsernameNotFoundException("User Details Not Found!!");
        }
    }
}
