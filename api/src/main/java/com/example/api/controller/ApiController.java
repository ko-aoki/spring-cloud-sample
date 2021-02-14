package com.example.api.controller;

import com.example.api.service.HttpBinService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiController {

    @Value("${foo}")
    private String foo;

    @Value("${democonfigclient.message}")
    private String message;

    private CircuitBreakerFactory circuitBreakerFactory;

    private HttpBinService httpBin;

    public ApiController(CircuitBreakerFactory circuitBreakerFactory, HttpBinService httpBin) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.httpBin = httpBin;
    }

    @GetMapping("/test")
    String home(@RequestHeader Map<String, String> headers) {

        headers.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        return message + " by " + foo;
    }

    @GetMapping("/get")
    public Map get() {
        return httpBin.get();
    }

    @GetMapping("/delay/{seconds}")
    public Map delay(@PathVariable int seconds) {
        return circuitBreakerFactory.create("delay").run(httpBin.delaySuppplier(seconds), t -> {
            System.out.println("delay call failed error" + t.getMessage());
            Map<String, String> fallback = new HashMap<>();
            fallback.put("hello", "world");
            return fallback;
        });
    }
}
