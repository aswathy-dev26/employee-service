package com.employeemanagement.employeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/test")
    public String test() {
        return "Employee Service is running successfully";
    }
}
