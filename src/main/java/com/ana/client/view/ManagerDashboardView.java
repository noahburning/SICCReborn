package com.ana.client.view;

import com.ana.client.listener.LogoutListener;
import com.ana.client.listener.LookupListener;

public interface ManagerDashboardView {

    void setLogoutListener(LogoutListener listener);

    void setLookupListener(LookupListener listener);

    void showEmployeeNotFound();

    void showEmployeeFound();

}
