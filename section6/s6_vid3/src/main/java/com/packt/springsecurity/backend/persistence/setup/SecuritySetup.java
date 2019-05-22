package com.packt.springsecurity.backend.persistence.setup;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.packt.springsecurity.backend.persistence.model.Authority;
import com.packt.springsecurity.backend.persistence.model.User;
import com.packt.springsecurity.backend.persistence.service.IAuthorityService;
import com.packt.springsecurity.backend.persistence.service.IUserService;

@Component
public class SecuritySetup implements ApplicationListener<ContextRefreshedEvent> {
    static final Logger logger = LoggerFactory.getLogger(SecuritySetup.class);
    private boolean setupDone;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthorityService authorityService;

    public SecuritySetup() {
        super();
    }

    //

    public final void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!setupDone) {
            logger.info("Executing Setup");

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin_setup_principal", "pass", Lists.newArrayList(new SimpleGrantedAuthority("ROLE_ADMIN"))));

            createAuthorities();
            createUsers();

            SecurityContextHolder.getContext().setAuthentication(null);

            setupDone = true;
            logger.info("Setup Done");
        }
    }

    // Authority

    private void createAuthorities() {
        createAuthorityIfNotExisting("ROLE_ADMIN");
        createAuthorityIfNotExisting("ROLE_USER");
    }

    final void createAuthorityIfNotExisting(final String name) {
        final Authority entityByName = authorityService.findByName(name);
        if (entityByName == null) {
            final Authority entity = new Authority(name);
            authorityService.create(entity);
        }
    }

    // Principal/User

    final void createUsers() {
        final Authority authorityUser = authorityService.findByName("ROLE_USER");
        final Authority authorityAdmin = authorityService.findByName("ROLE_ADMIN");

        createUserIfNotExisting(SecurityConstants.ADMIN1_USERNAME, SecurityConstants.ADMIN1_PASS, Sets.<Authority> newHashSet(authorityUser, authorityAdmin));
        createUserIfNotExisting(SecurityConstants.ADMIN2_USERNAME, SecurityConstants.ADMIN2_PASS, Sets.<Authority> newHashSet(authorityUser, authorityAdmin));
        createUserIfNotExisting(SecurityConstants.USER1_USERNAME, SecurityConstants.USER1_PASS, Sets.<Authority> newHashSet(authorityUser));
    }

    final void createUserIfNotExisting(final String username, final String pass, final Set<Authority> authorities) {
        final User entityByName = userService.findByName(username);
        if (entityByName == null) {
            final User entity = new User(username, pass, authorities);
            userService.create(entity);
        }
    }

}
