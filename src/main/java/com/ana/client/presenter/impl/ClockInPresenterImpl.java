package com.ana.client.presenter.impl;

import com.ana.client.gui.Navigator;
import com.ana.client.gui.ViewType;
import com.ana.client.listener.ClockInListener;
import com.ana.client.listener.ReturnListener;
import com.ana.client.presenter.ClockInPresenter;
import com.ana.client.view.ClockInView;

public class ClockInPresenterImpl implements ClockInPresenter, ClockInListener, ReturnListener {

    private final ClockInView clockInView;
    private final Navigator navigator;

    public ClockInPresenterImpl(ClockInView clockInView, Navigator navigator) {
        this.clockInView = clockInView;
        this.navigator = navigator;
        this.clockInView.setClockInListener(this);
        this.clockInView.setReturnListener(this);
    }

    @Override
    public void clockIn() {
        // Implement the clockIn() method
    }

    @Override
    public void clockOut() {
        // Implement the clockOut() method
    }

    @Override
    public void onClockIn() {
        clockIn();
    }

    @Override
    public void onClockOut() {
        clockOut();
    }

    @Override
    public void onClockInOut() {

    }

    @Override
    public void onReturn() {
        navigator.showView(ViewType.EMPLOYEE_DASHBOARD.toString());
    }
}
