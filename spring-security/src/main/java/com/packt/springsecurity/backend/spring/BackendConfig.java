package com.packt.springsecurity.backend.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({ "com.packt.springsecurity.backend" })
@EnableWebMvc
public class BackendConfig {

    public BackendConfig() {
        super();
    }

}
