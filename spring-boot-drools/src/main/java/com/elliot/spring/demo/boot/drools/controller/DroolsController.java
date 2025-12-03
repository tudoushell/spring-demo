package com.elliot.spring.demo.boot.drools.controller;

import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/drools")
@RequiredArgsConstructor
@RestController
public class DroolsController {

    private final KieSession kieSession;

    @GetMapping("/test")
    public String drools() {
        kieSession.fireAllRules();
        kieSession.dispose();
        return "success";
    }
}
