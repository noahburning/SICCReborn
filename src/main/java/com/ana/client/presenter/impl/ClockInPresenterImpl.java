package com.ana.client.presenter.impl;

import com.ana.api.entity.Clock;
import com.ana.api.entity.User;
import com.ana.api.service.ClockService;
import com.ana.api.service.UserService;
import com.ana.client.gui.Navigator;
import com.ana.client.gui.ViewType;
import com.ana.client.listener.ClockInListener;
import com.ana.client.listener.ReturnListener;
import com.ana.client.presenter.ClockInPresenter;
import com.ana.client.view.ClockInView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ClockInPresenterImpl implements ClockInPresenter, ClockInListener, ReturnListener {

    private final ClockInView clockInView;
    private final Navigator navigator;
    private final UserService userService;
    private final ClockService clockService;



    public ClockInPresenterImpl(ClockInView clockInView, Navigator navigator, UserService userService, ClockService clockService) {
        this.clockInView = clockInView;
        this.navigator = navigator;
        this.userService = userService;
        this.clockService = clockService;
        this.clockInView.setClockInListener(this);
        this.clockInView.setReturnListener(this);
    }


    @Override
    public void clockIn() {
        // Get the current user ID
        Optional<User> currentUser = userService.getByUsername("currentUsername"); // Replace "currentUsername" with the actual username of the logged-in user
        if (currentUser.isPresent()) {
            Long userId = currentUser.get().getId();

            // Get the current date and time
            LocalDateTime now = getCurrentDateTime();
            String clockInTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Create a new Clock entry
            Clock clock = new Clock(userId, clockInTime, null);
            Clock createdClock = clockService.createClock(clock);

            // Show success message
            clockInView.showClockInMessage();
        } else {
            // User not found, handle the error
        }
    }

    @Override
    public void clockOut() {
        // Get the current user ID
        Optional<User> currentUser = userService.getByUsername("currentUsername"); // Replace "currentUsername" with the actual username of the logged-in user
        if (currentUser.isPresent()) {
            Long userId = currentUser.get().getId();

            // Get the current date and time
            LocalDateTime now = getCurrentDateTime();
            String clockOutTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Find the latest Clock entry for the user and update the clockOutTime
            Optional<Clock> latestClock = clockService.getLatestClockByUserId(userId);
            if (latestClock.isPresent()) {
                Clock clock = latestClock.get();
                clock.setClockOutTime(clockOutTime);
                Clock updatedClock = clockService.updateClock(clock);

                // Show success message
                clockInView.showClockOutMessage();
            } else {
                // No clock in entry found, handle the error
            }
        } else {
            // User not found, handle the error
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
