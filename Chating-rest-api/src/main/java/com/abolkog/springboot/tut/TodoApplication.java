package com.abolkog.springboot.tut;

import java.util.Iterator;
import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.abolkog.springboot.tut.model.entity.AppUser;
import com.abolkog.springboot.tut.util.FirstTimeInitializer;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 05/09/2018 3:06 AM.
 */
@SpringBootApplication
(
    exclude = { DataSourceAutoConfiguration.class, 
                HibernateJpaAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                MongoAutoConfiguration.class, 
                MongoDataAutoConfiguration.class})
@EnableTransactionManagement
public class TodoApplication implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(TodoApplication.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        String sql = "SELECT * FROM app_user";
        List<AppUser> customers = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(AppUser.class));
         
                
        for (AppUser appUser : customers) {
            
            System.out.println(appUser.getName());
        }
        
    }
}
