package com.ana.client.model.impl;

import com.ana.api.entity.Clock;
import com.ana.api.repository.ClockRepository;
import com.ana.client.model.ClockModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

public class ClockModelImpl implements ClockModel {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final EntityManager entityManager;
    private final ClockRepository clockRepository;


    @Autowired
    public ClockModelImpl(
            RestTemplate restTemplate,
            String baseUrl,
            EntityManager entityManager,
            ClockRepository clockRepository
    ) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.entityManager = entityManager;
        this.clockRepository = clockRepository;
    }




    @Override
    public Clock createClock(Clock clock) {
        String url = baseUrl + "/clocks";
        ResponseEntity<Clock> response = restTemplate.postForEntity(url, clock, Clock.class);
        return response.getBody();
    }

    @Override
    public void updateClockOutTimeByClockIdAndTime(Long clockId, LocalDateTime currentTime) {
        String url = baseUrl + "/clocks/" + clockId;
        ResponseEntity<Clock> response = restTemplate.getForEntity(url, Clock.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Clock clock = response.getBody();
                LocalDateTime now = getCurrentDateTime();
                clock.setClockOutTime(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                restTemplate.put(url, clock);

        }
    }

    private LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }


    @Override
    public Long getUserIdByUsername(String username) {
        String url = baseUrl + "/users/" + username;
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {
        });
        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("id")) {
            Object idObject = responseBody.get("id");
            if (idObject instanceof Number) {
                return ((Number) idObject).longValue();
            }
        }
        return null;
    }


    @Override
    public Long findLastClockIdByUserId(Long userId) {
        String url = baseUrl + "/clocks/latest?userId=" + userId;
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {});

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("clockId")) {
                Object clockIdObject = responseBody.get("clockId");
                if (clockIdObject instanceof Number) {
                    Long clockId = ((Number) clockIdObject).longValue();
                    System.out.println("Clock ID: " + clockId);
                    System.out.println("Clock ID: " + clockId);
                    System.out.println("Clock ID: " + clockId);
                    System.out.println("Clock ID: " + clockId);
                    System.out.println("Clock ID: " + clockId);
                    return clockId;
                }
            }
        }

        return null;
    }


}
