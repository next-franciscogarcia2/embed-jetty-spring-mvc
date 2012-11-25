package com.digitalsanctum.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    Environment env;

    @RequestMapping(value = "/", produces = "text/plain")
    @ResponseBody
    public String handleRoot() {
        return env.getProperty("app.name") + " OK!";
    }
}
