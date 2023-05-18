package com.ana.client.model.impl;

import com.ana.api.entity.Clock;
import com.ana.client.model.ClockModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class ClockModelImpl implements ClockModel {

    private final RestTemplate restTemplate;
    private final String baseUrl;


    public ClockModelImpl(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }


    @Override
    public Optional<Clock> getLatestClockByUserId(Long userId) {
        String url = baseUrl + "/clocks/latest?userId=" + userId;
        ResponseEntity<Clock> response = restTemplate.exchange(url, HttpMethod.GET, null, Clock.class);
        return Optional.ofNullable(response.getBody());
    }

    @Override
    public Clock createClock(Clock clock) {
        String url = baseUrl + "/clocks";
        ResponseEntity<Clock> response = restTemplate.postForEntity(url, clock, Clock.class);
        return response.getBody();
    }

    @Override
    public Clock updateClock(Clock clock) {
        String url = baseUrl + "/clocks/" + clock.getUserId();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Clock> requestEntity = new HttpEntity<>(clock, headers);
        ResponseEntity<Clock> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Clock.class);
        return response.getBody();
    }

    @Override
    public List<Clock> getAllClocksByUserId(Long userId) {
        String url = baseUrl + "/clocks?userId=" + userId;
        ResponseEntity<List<Clock>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Clock>>() {});
        return response.getBody();
    }

    @Override
    public Long getUserIdByUsername(String username) {
        String url = baseUrl + "/users/" + username;
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {});
        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("id")) {
            Object idObject = responseBody.get("id");
            if (idObject instanceof Number) {
                return ((Number) idObject).longValue();
            }
        }
        return null;
    }
}
