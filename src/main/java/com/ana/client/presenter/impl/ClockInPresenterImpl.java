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
    @Autowired
    private final ClockModel clockModel;
    public UserContext userContext;

    public String localUser;


    public ClockInPresenterImpl(ClockInView clockInView, Navigator navigator, ClockModel clockModel, UserContext userContext) {
        this.clockInView = clockInView;
        this.navigator = navigator;
        this.clockModel = clockModel;
        this.userContext = userContext;
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
        String localUser = userContext.getUsername();
        Long currentUserId = clockModel.getUserIdByUsername(localUser);
        System.out.println("The current user id is: " + currentUserId + "\n\n\n");

        Long latestClockId = clockModel.findLastClockIdByUserId(currentUserId);

        System.out.println("The current clock id is: " + latestClockId + "\n\n\n");

        LocalDateTime now = getCurrentDateTime();
        if (latestClockId != null) {
            clockModel.updateClockOutTimeByClockIdAndTime(latestClockId, now);
            clockInView.showClockOutMessage();
        } else {
            // Handle the case when no clock-in entry is found
        }
    }

    @Override
    public void onClockOut() {
        LocalDateTime now = getCurrentDateTime();
        clockOut(now);
    }

    private void clockOut(LocalDateTime currentTime) {
        String localUser = userContext.getUsername();
        Long currentUserId = clockModel.getUserIdByUsername(localUser);
        System.out.println("The current user id is: " + currentUserId + "\n\n\n");

        Long latestClockId = clockModel.findLastClockIdByUserId(currentUserId);

        System.out.println("The current clock id is: " + latestClockId + "\n\n\n");

        clockModel.updateClockOutTimeByClockIdAndTime(latestClockId, currentTime);
        clockInView.showClockOutMessage();
    }



    @Override
    public void onClockIn() {
        clockIn();
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
