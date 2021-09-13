package com.abolkog.springboot.tut.security;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abolkog.springboot.tut.TodoApplication;

/**
 * Enable user To login
 */
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private final Log log = LogFactory.getLog(AuthController.class);
    
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = {"","/"})
    public JwtResponse signIn(@RequestBody SignInRequest signInRequest) {

        System.out.println(signInRequest.getUsername() + " === " + signInRequest.getPassword());
        
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            
            log.info("Bad Password");
            e.printStackTrace();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
        String token = tokenUtil.generateToken(userDetails);
        JwtResponse response = new JwtResponse(token);
        
        return response;
    }
}
