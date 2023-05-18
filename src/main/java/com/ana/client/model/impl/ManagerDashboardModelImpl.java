package com.ana.client.model.impl;

import com.ana.api.entity.User;
import com.ana.client.model.ManagerDashboardModel;
import com.ana.client.utility.EmployeeContext;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

public class ManagerDashboardModelImpl implements ManagerDashboardModel {

    private static final Logger logger = LoggerFactory.getLogger(ManagerDashboardModelImpl.class);

    private static final String BASE_URL = "http://localhost:8080";
    private static final String EMPLOYEE_LOOKUP_PATH = "/users?id=%d";

    private final RestTemplate restTemplate;

    public ManagerDashboardModelImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Long> lookupEmployee(String employeeId) {
        if(employeeId == null) throw new IllegalArgumentException("Employee ID cannot be null");
        if(employeeId.isEmpty()) throw new IllegalArgumentException("Employee ID cannot be empty");

        Optional<Long> id = Optional.of(employeeId).map(Long::parseLong);
        long employeeIdLong = id.orElse(0L);

        if (employeeIdLong == 0L) {
            logger.error("Invalid employee ID: {}", employeeId);
            throw new IllegalArgumentException("Invalid employee ID: " + employeeId);
        }

        ResponseEntity<String> response = null;

        try {
            final String URL = BASE_URL+String.format(EMPLOYEE_LOOKUP_PATH, employeeIdLong);
            response = restTemplate.getForEntity(URL, String.class);
            logger.info("Response: {}", response.getBody());
        } catch (Exception e) {
            logger.error("Error looking up employee: {}", e.getMessage());
            return Optional.empty();
        } finally {
            if (response != null) {
                logger.info("Response: {}", response);
            } else {
                logger.info("Response: null");
            }
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Employee found!");
        } else {
            logger.info("Employee not found!");
        }

        // using gson to parse the response body
        Gson gson = new Gson();
        User user = gson.fromJson(Objects.requireNonNull(response.getBody()), User.class);

        Optional<Long> ident = user.getId().describeConstable();
        String fname = user.getFirstName();
        String minit = user.getMiddleInitial();
        String lname = user.getLastName();
        String username = user.getUsername();
        JsonObject json = gson.fromJson(response.getBody(), JsonObject.class);
        json.get("manager").getAsBoolean();
        boolean is_mgr = json.get("manager").getAsBoolean();

        EmployeeContext.SaveEmployeeContext(ident.orElse(0L), fname, minit, lname, username, is_mgr);

        return ident;
    }

}
