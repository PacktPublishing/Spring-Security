package com.packt.springsecurity.frontend.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan("com.packt.springsecurity.frontend.web")
public class ClientWebConfig extends WebMvcConfigurerAdapter {

    public ClientWebConfig() {
        super();
    }

    /**
     * Registers the existing View Controllers for the application
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        super.addViewControllers(registry);

        registry.addViewController("/login.html");
        registry.addViewController("/register.html");
        registry.addViewController("/authenticated.html");

        registry.addViewController("/admin/users.html");
        registry.addViewController("/admin/dev_only.html");
    }

    /**
     * Register the {@link JstlView} ViewResolver that will be used by spring
     * mvc and configure it.
     * 
     * @return a new instance of a {@link ViewResolver}
     */
    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");
        bean.setExposedContextBeanNames(new String[] { "properties" });

        return bean;
    }

}
