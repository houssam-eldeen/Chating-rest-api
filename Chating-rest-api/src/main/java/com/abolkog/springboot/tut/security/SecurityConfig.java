package com.abolkog.springboot.tut.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 08/10/2018 9:11 PM.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/auth/**",
            "/api/v1/tanya/**",
    };

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    AuthFilter authFilter() {
        return new AuthFilter();
    }

    /**
     * ** The issue may be related to CSRF or CORS Security Protection.
     *          1- FOR CSRF: You can disable it if the application users did not use it from browsers.
     *          2- For CORS: You can specify the origin and allow HTTP Methods.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
            // disable cors & csrf To disable cookies
            .csrf().disable()
            // Because we use JWT so, sessionPolicy will be stateless
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // In general following request will be opened without authentication (public)   
            .authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest().authenticated()
                .and()
             //   
            .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    }    
    
    
//    @Autowired
//    private UserService userService;
//    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        
//    auth.userDetailsService(new UserDetailsService() {
//        @Override
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            UserDetails userDetails = userService.loadUserByUsername(username);
//            return userService.loadUserByUsername(username);
//        }
//    }).passwordEncoder(new BCryptPasswordEncoder()).and()
//    .inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//    .withUser("admin").password("123456").roles("ADMIN","WORKER");
//    }
}
