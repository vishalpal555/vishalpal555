package com.vishal.skillvertexProject;

import java.security.SecureRandom;

import javax.sql.DataSource;

import com.vishal.skillvertexProject.User.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    // private UserService userService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user").password("password").roles("ADMIN")
            .and();
        
        auth.jdbcAuthentication().dataSource(dataSource);

   }

   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder() ;
   }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/items").permitAll()
        .antMatchers("/users/add").permitAll()
        .antMatchers("/users").hasAnyRole("USER", "ADMIN")
        .antMatchers("/users/all").hasRole("ADMIN")
        .and()
        .formLogin().permitAll()
        .and().csrf().disable();
    }

    
    
}
