package com.packt.springsecurity.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.collect.Lists;
import com.packt.springsecurity.backend.model.User;

@Controller
@RequestMapping(value = "users")
public class UserController {

    public UserController() {
        super();
    }

    // API

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return Lists.newArrayList(new User("someUser01", "somePass01", null, "someUuid01"));
    }

}
