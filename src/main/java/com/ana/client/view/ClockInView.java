package com.ana.client.view;

import com.ana.client.listener.ClockInListener;
import com.ana.client.listener.LogoutListener;
import com.ana.client.listener.ReturnListener;

public interface ClockInView {

    void showClockInMessage();

    void showClockOutMessage();

    void setClockInListener(ClockInListener listener);

    void addClockInListener(ClockInListener listener);

    void setReturnListener(ReturnListener listener);
}
