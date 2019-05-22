package com.packt.springsecurity.backend.security;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.packt.springsecurity.backend.model.Account;
import com.packt.springsecurity.backend.model.Permission;
import com.packt.springsecurity.backend.model.Role;
import com.packt.springsecurity.backend.persistence.service.IAccountService;

@Component
public class SecUserService implements UserDetailsService {

    @Autowired
    private IAccountService accountService;

    public SecUserService() {
        super();
    }

    // API

    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Account foundByUsername = accountService.findByName(username);
        final Set<Role> roles = foundByUsername.getRoles();
        final List<Permission> permissions = Lists.newArrayList();
        for (final Role role : roles) {
            permissions.addAll(role.getPermissions());
        }

        return new org.springframework.security.core.userdetails.User(username, foundByUsername.getPassword(), permissions);
    }

}
