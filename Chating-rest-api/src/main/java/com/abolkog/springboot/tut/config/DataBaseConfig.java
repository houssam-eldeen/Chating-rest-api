
package com.abolkog.springboot.tut.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.abolkog.springboot.tut.model.repository", 
                        entityManagerFactoryRef = "itrisEntityManagerFactory", 
                        transactionManagerRef = "itrisTransactionManager")
@EnableTransactionManagement
public class DataBaseConfig
{

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties itrisDataSourceProperties()
    {

        return new DataSourceProperties();
    }

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Bean(name = "dataSource")
    public DataSource dataSource()
    {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl(jdbcUrl);

        return dataSource;
    }

//     @Bean
//     public DataSource dataSource() {    
//     DataSourceProperties primaryDataSourceProperties = itrisDataSourceProperties();    
////     return DataSourceBuilder.create().driverClassName(primaryDataSourceProperties.getDriverClassName())
////     .url(primaryDataSourceProperties.getUrl()).username(primaryDataSourceProperties.getUsername())
////     .password(primaryDataSourceProperties.getPassword()).build();
//     
//     return DataSourceBuilder.create().driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
//         .url(primaryDataSourceProperties.getUrl()).username(primaryDataSourceProperties.getUsername())
//         .password(primaryDataSourceProperties.getPassword()).build();
//     }

    @Bean
    public PlatformTransactionManager itrisTransactionManager()
    {

        EntityManagerFactory factory = itrisEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    /**
     * spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true
        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect. org.hibernate.dialect.SQLServer2008Dialect

     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean itrisEntityManagerFactory()
    {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        
        try {
            factory.setDataSource(dataSource());
            factory.setPackagesToScan(new String[] {"com.abolkog.springboot.tut.model.entity"});// itris.model.entity
            factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

            Properties jpaProperties = new Properties();
            jpaProperties.put("hibernate.hbm2ddl.auto", "none");
            jpaProperties.put("spring.jpa.hibernate.dialect", "org.hibernate.dialect.SQLServer2008Dialect");
//      jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
            jpaProperties.put("spring.datasource.driver-class-name", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            jpaProperties.put("spring.jpa.hibernate.ddl-auto", "update");
            jpaProperties.put("spring.jpa.properties.hibernate.format_sql", "true");
            
            factory.setJpaProperties(jpaProperties);
        } catch (Exception e) {
            log.info("exception: " , e);
            e.printStackTrace();
        }

        return factory;

    }

    @Bean
    public DataSourceInitializer itrisDataSourceInitializer()
    {

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();

        dataSourceInitializer.setDatabasePopulator(databasePopulator);

        return dataSourceInitializer;
    }
    
    // test
    

}
