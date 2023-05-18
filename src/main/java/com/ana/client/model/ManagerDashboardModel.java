package com.ana.client.model;

import java.util.Optional;

public interface ManagerDashboardModel {

    Optional<Long> lookupEmployee(String employeeId);

}
