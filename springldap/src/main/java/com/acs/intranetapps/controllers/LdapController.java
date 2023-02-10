package com.acs.intranetapps.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class LdapController {

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping(value = "/home")
    public String home() {
        return "welcome";
    }
    
    @RequestMapping(value = "/")
    public String home1() {
        return "welcome";
    }

}
