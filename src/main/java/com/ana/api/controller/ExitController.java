package com.ana.api.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExitController {

    private final ApplicationContext applicationContext;

    public ExitController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostMapping("/exit")
    public void exit() {
        ((ConfigurableApplicationContext) applicationContext).close();
    }

}
