package com.abolkog.springboot.tut.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 09/09/2018 10:01 AM.
 */
@RestController // just handle json messages
public class HomeController {


    @RequestMapping(value = "/")// Get Request (old design)
    public String greeting() {
        
        return "Hello, Welcome to SpringBoot!!";
    }

    @GetMapping(value = "/{name}")// Get Request (New design)
    public String greetingWithName(@PathVariable  String name) {
        
        return String.format("Welcome %s to our SpringBoot App.", name);
    }
}
