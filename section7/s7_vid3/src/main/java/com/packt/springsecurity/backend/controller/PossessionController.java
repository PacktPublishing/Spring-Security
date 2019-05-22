package com.packt.springsecurity.backend.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.packt.springsecurity.backend.model.Possession;
import com.packt.springsecurity.backend.persistence.service.IPossessionService;

@Controller
@RequestMapping(value = "possessions")
public class PossessionController {

    @Autowired
    private IPossessionService service;

    public PossessionController() {
        super();
    }

    // API

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Possession findOne(@PathVariable("id") final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return service.findById(id);
    }

}
