package com.example.api.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @see <a href="https://github.com/spring-cloud-samples/spring-cloud-circuitbreaker-demo/blob/master/spring-cloud-circuitbreaker-demo-resilience4j/src/main/java/org/springframework/cloud/circuitbreaker/demo/resilience4jcircuitbreakerdemo/HttpBinService.java">spring公式サンプル参照</a> />
 * This software includes the work that is distributed in the Apache License 2.0.
 */
@Service
public class HttpBinService {

    private RestTemplate rest;

    public HttpBinService(RestTemplate rest) {
        this.rest = rest;
    }

    public Map get() {
        return rest.getForObject("https://httpbin.org/get", Map.class);

    }

    public Map delay(int seconds) {
        return rest.getForObject("https://httpbin.org/delay/" + seconds, Map.class);
    }

    public Supplier<Map> delaySuppplier(int seconds) {
        return () -> this.delay(seconds);
    }
}