package com.packt.springsecurity.frontend.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource({ "classpath:web-${clientWebTarget:dev}.properties", "classpath:ui.properties" })
@ComponentScan("com.packt.springsecurity.frontend.common")
public class ClientContextConfig {

    @Value("${clientWebTarget:dev}")
    String webTarget;

    public ClientContextConfig() {
        super();
    }

    /**
     * Used to register a property sources placeholder support, to give a way to
     * inject properties from PropertySource via @Value annotation.
     * 
     * @return the bean instance.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Creates and register the {@link ReloadableResourceBundleMessageSource}
     * bean
     * 
     * @return a new instance of a {@link ReloadableResourceBundleMessageSource}
     */
    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
        bean.setBasename("i18n/messages");
        bean.setCacheSeconds(10);

        return bean;
    }

    /**
     * Re-initializes the ui.properties and the web-<target>.properties. This is
     * used so that the JSPs have access to the properties via this bean.
     * 
     * @return a new instance of a {@link PropertiesFactoryBean}
     */
    @Bean
    public PropertiesFactoryBean properties() {
        final PropertiesFactoryBean factory = new PropertiesFactoryBean();
        factory.setLocations(new Resource[] { new ClassPathResource("ui.properties"), new ClassPathResource("web-" + webTarget + ".properties") });

        return factory;
    }

}
