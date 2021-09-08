package com.abolkog.springboot.tut.util;


import com.abolkog.springboot.tut.model.entity.AppUser;
import com.abolkog.springboot.tut.security.UserService;

import javax.validation.constraints.NotEmpty;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 08/10/2018 9:41 PM.
 */
@Component
public class FirstTimeInitializer implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);

    @Autowired
    private UserService userService;

    @Override
    public void run(String... strings) throws Exception {

        if (userService.findAll().isEmpty()) {
            logger.info("No Users accounts found. Creating some users");

            /** AppUser(@NotEmpty String email, @NotEmpty String password, @NotEmpty String name)  */
            AppUser user = new AppUser("khalid@abolkog.com", "password", "Khalid");
            userService.save(user);
        }
    }
}
