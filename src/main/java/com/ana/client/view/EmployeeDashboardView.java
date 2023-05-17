package com.ana.client.view;

import com.ana.client.listener.LogoutListener;
import com.ana.client.listener.ClockInListener;

public interface EmployeeDashboardView {
    void setLogoutListener(LogoutListener listener);
    void setClockInListener(ClockInListener listener);

}
