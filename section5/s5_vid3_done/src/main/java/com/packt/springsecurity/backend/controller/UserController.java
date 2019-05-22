package com.packt.springsecurity.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.collect.Lists;
import com.packt.springsecurity.backend.persistence.model.User;
import com.packt.springsecurity.backend.persistence.service.IUserService;

@Controller
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private IUserService service;

    public UserController() {
        super();
    }

    // API

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findAll(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return Lists.newArrayList(new User("someUser01", "somePass01", null, "someUuid01"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN') or principal.id == #userId")
    public User findOneById(@PathVariable("id") final Long userId, final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return service.findById(userId);
    }

}
