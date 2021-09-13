package com.abolkog.springboot.tut.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.abolkog.springboot.tut.model.entity.AppUser;
import com.abolkog.springboot.tut.model.repository.UserRepository;

public class EncryptedGeneration
{

    @Autowired
    private UserRepository userRepository;
    
    public static void main(String[] args)
    {

        
       new EncryptedGeneration().encode("p-123456");

    }
    
    public void encode(String password)
    {

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

        System.out.println(hashed);
        
        String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12));
        
        System.out.println(hashed);

        

        
    }

}
