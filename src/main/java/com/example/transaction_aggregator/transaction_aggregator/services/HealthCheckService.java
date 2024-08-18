//package com.example.transaction_aggregator.transaction_aggregator.services;
//
//import org.springframework.boot.actuate.health.Health;
//import org.springframework.boot.actuate.health.HealthIndicator;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Objects;
//
//public class HealthCheckService implements HealthIndicator {
//
//    @Override
//    public Health health() {
//
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/";
//        String healthUrl = "http://localhost:8080/actuator/health";
//        String response = restTemplate.getForObject(url, String.class);
//        String response2 = restTemplate.getForObject(healthUrl, String.class);
//        if (Objects.equals(response, "Dispatcher Servlet has been started") && response2.equals("Dispatcher Servlet has been started")) {}
//        return Health.up().build();
//    }
//}
