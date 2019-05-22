package com.packt.springsecurity.frontend.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public final class WebMediatorController {

    public WebMediatorController() {
        super();
    }

    // API

    @RequestMapping(value = "/index")
    public String home() {
        return "index";
    }

}
