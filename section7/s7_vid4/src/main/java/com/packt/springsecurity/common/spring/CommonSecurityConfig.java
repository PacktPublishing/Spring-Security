package com.packt.springsecurity.common.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath*:webSecurityConfig.xml", "classpath*:beans-security-acl.xml" })
public class CommonSecurityConfig {

    public CommonSecurityConfig() {
        super();
    }

}
