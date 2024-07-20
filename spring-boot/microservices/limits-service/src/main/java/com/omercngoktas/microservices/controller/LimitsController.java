package com.omercngoktas.microservices.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omercngoktas.microservices.bean.Configuration;
import com.omercngoktas.microservices.bean.Limits;

@RestController
public class LimitsController {

    private final Configuration configuration;

    public LimitsController(final Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }   
}
