package com.abolkog.springboot.tut;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.abolkog.springboot.tut.model.entity.AppUser;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 15/10/2018 11:51 PM.
 */
public abstract class BaseController {

    public AppUser getCurrentUser() {
        
        
     // Get userName:
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = (AppUser) authentication.getPrincipal();
        System.out.println("login userName: " + appUser.getName());
     //   
        return appUser;
    }
}
