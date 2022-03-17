package com.example.springsecurityexample1.config;

import com.example.springsecurityexample1.entity.enums.Permission;
import com.example.springsecurityexample1.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .disable()
      .authorizeRequests()
      .antMatchers("/api/auth/register,/api/auth/login").permitAll()
      .antMatchers(HttpMethod.GET, "/api/product/*").hasAuthority(Permission.PRODUCT_GET.getPermission())
      .antMatchers("/api/product/**").hasAnyAuthority(Permission.PRODUCT_EDIT.getPermission(),
        Permission.PRODUCT_DELETE.getPermission(), Permission.PRODUCT_ADD.getPermission())
      .anyRequest()
      .authenticated()
      .and()
      .httpBasic();
  }

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
