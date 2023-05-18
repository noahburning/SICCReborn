package com.ana.client.presenter.impl;

import com.ana.api.entity.Clock;
import com.ana.client.gui.Navigator;
import com.ana.client.gui.ViewType;
import com.ana.client.listener.ClockInListener;
import com.ana.client.listener.ReturnListener;
import com.ana.client.model.ClockModel;
import com.ana.client.presenter.ClockInPresenter;
import com.ana.client.view.ClockInView;
import com.ana.client.utility.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ClockInPresenterImpl implements ClockInPresenter, ClockInListener, ReturnListener {

    private final ClockInView clockInView;
    private final Navigator navigator;

    private final ClockModel clockModel;

    public UserContext userContext;

    public String localUser;


    public ClockInPresenterImpl(ClockInView clockInView, Navigator navigator, ClockModel clockModel) {
        this.clockInView = clockInView;
        this.navigator = navigator;
        this.clockModel = clockModel; // Assign the ClockModel instance to the corresponding field
        this.clockInView.setClockInListener(this);
        this.clockInView.setReturnListener(this);
    }


    @Override
    public void clockIn() {
        // Get the current user ID
        localUser = userContext.getUsername();
        Long currentUserId = clockModel.getUserIdByUsername(localUser);
        if (currentUserId != null) {
            // Get the current date and time
            LocalDateTime now = getCurrentDateTime();

            System.out.print("Time before format" + now);

            String clockInTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            System.out.print("Time after format" + clockInTime);

            // Create a new Clock entry
            Clock clock = new Clock(currentUserId, clockInTime, null);
            Clock createdClock = clockModel.createClock(clock);

            // Show success message
            clockInView.showClockInMessage();
        } else {
            // User not found, handle the error
        }
    }

    @Override
    public void clockOut() {
        // Get the current user ID
        Long currentUserId = clockModel.getUserIdByUsername(localUser);
        Optional<Clock> latestClock = clockModel.getLatestClockByUserId(currentUserId);
        if (latestClock.isPresent()) {
            Clock clock = latestClock.get();

            // Get the current date and time
            LocalDateTime now = getCurrentDateTime();

            String clockOutTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Update the clock entry with the clockOutTime
            clock.setClockOutTime(clockOutTime);
            Clock updatedClock = clockModel.updateClock(clock);

            // Show success message
            clockInView.showClockOutMessage();
        } else {
            // No clock in entry found, handle the error
        }
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

    private LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

}
