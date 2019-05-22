package com.packt.springsecurity.backend.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({ "com.packt.springsecurity.backend.persistence" })
@EnableWebMvc
public class BackendPersistenceConfig {

    public BackendPersistenceConfig() {
        super();
    }

    // bean

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/sec_dev?createDatabaseIfNotExist=true");
        dataSource.setUsername("secuser");
        dataSource.setPassword("secpass");
        return dataSource;
    }

}
