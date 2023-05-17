package com.ana.client.view;

import com.ana.client.listener.LogoutListener;
import com.ana.client.listener.ClockInListener;
import com.ana.client.listener.AccessPOSListener;

public interface EmployeeDashboardView {
    void setLogoutListener(LogoutListener listener);
    void setClockInListener(ClockInListener listener);
    void setAccessPOSListener(AccessPOSListener listener);
}
