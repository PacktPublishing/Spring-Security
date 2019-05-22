package com.packt.springsecurity.common.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath*:webSecurityConfig.xml" })
@ComponentScan({ "com.packt.springsecurity.backend.security" })
public class SecurityConfig {

    public SecurityConfig() {
        super();
    }

}
