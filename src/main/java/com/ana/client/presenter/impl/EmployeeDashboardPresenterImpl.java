package com.ana.client.presenter.impl;

import com.ana.client.listener.LogoutListener;
import com.ana.client.presenter.EmployeeDashboardPresenter;
import com.ana.client.view.EmployeeDashboardView;
import com.ana.client.gui.Navigator;
import com.ana.client.gui.ViewType;
import com.ana.client.listener.ClockInListener;

public class EmployeeDashboardPresenterImpl implements EmployeeDashboardPresenter, LogoutListener, ClockInListener {

    private final EmployeeDashboardView employeeDashboardView;
    private final Navigator navigator;

    public EmployeeDashboardPresenterImpl(EmployeeDashboardView employeeDashboardView, Navigator navigator) {
        this.employeeDashboardView = employeeDashboardView;
        this.navigator = navigator;
        this.employeeDashboardView.setLogoutListener(this);
        this.employeeDashboardView.setClockInListener(this);
    }

    @Override
    public void onLogout() {
        navigator.showView(ViewType.LOGIN.toString());
    }

    @Override
    public void onClockInOut() {
        navigator.showView(ViewType.CLOCK.toString());
    }

    @Override
    public void onClockIn() {
        // Perform clock-in functionality
    }

    @Override
    public void onClockOut() {
        // Perform clock-out functionality
    }

}
