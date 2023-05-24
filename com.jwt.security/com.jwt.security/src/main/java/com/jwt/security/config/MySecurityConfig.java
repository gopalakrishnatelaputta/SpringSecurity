package com.jwt.security.config;

import com.jwt.security.services.CustomUserDetailsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private CustomUserDetailsservice customUserDetailsservice;



   @Override
  public void configure(HttpSecurity http) throws Exception
  {
      http
              .csrf()
              .disable()
              .authorizeRequests()
              .antMatchers("/Token").permitAll()
              .anyRequest().authenticated()
              .and()
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
  @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
  {
      auth.userDetailsService(customUserDetailsservice);
  }

  public PasswordEncoder passwordEncoder()
  {
      return NoOpPasswordEncoder.getInstance();
  }

}
