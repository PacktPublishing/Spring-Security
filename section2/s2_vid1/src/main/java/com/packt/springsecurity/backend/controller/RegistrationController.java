package com.packt.springsecurity.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.packt.springsecurity.backend.dto.RegistrationDto;

@Controller
public class RegistrationController {

    @Autowired
    private UserDetailsManager userDetailsManager;

    public RegistrationController() {
        super();
    }

    // API

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerUser(@RequestBody final RegistrationDto registration) {
        final List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        userDetailsManager.createUser(new User(registration.getUsername(), registration.getPassword(), authorities));
    }

}
