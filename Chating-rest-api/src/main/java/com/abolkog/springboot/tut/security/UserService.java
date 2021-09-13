package com.abolkog.springboot.tut.security;

import com.abolkog.springboot.tut.TodoApplication;
import com.abolkog.springboot.tut.error.NotFoundException;
import com.abolkog.springboot.tut.model.entity.AppUser;
import com.abolkog.springboot.tut.model.repository.UserRepository;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 08/10/2018 9:20 PM.
 */
@Service
public class UserService implements UserDetailsService
{

    private final Log logger = LogFactory.getLog(UserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Bean
    private BCryptPasswordEncoder passwordEncoder()
    {

        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();

        // PasswordEncoder passwordEncoder2 = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return passwordEncoder1;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        // return new AppUser("khalid@abolkog.com",passwordEncoder().encode("password"), AuthorityUtils.NO_AUTHORITIES);
        AppUser user = userRepository.findByEmail(username);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        return user;
    }

    

    public void save(AppUser user)
    {

        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        String hashed2 = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));

        if (BCrypt.checkpw(user.getPassword(), hashed)) {
            System.out.println("It matches");

        } else if (BCrypt.checkpw(user.getPassword(), hashed2)) {
            System.out.println("It matches2");
        } else {
            System.out.println("It does not match");
        }

        user.setPassword(passwordEncoder().encode(user.getPassword()));
        this.userRepository.save(user);
    }

    public List<AppUser> findAll()
    {

        return userRepository.findAll();
    }
}
