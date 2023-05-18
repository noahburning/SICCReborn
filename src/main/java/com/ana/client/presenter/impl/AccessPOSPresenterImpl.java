package com.ana.client.presenter.impl;

import com.ana.client.gui.Navigator;
import com.ana.client.gui.ViewType;
import com.ana.client.listener.ReturnListener;
import com.ana.client.presenter.AccessPOSPresenter;
import com.ana.client.view.AccessPOSView;

public class AccessPOSPresenterImpl implements AccessPOSPresenter, ReturnListener {

    private final AccessPOSView accessPOSView;
    private final Navigator navigator;
    public AccessPOSPresenterImpl(AccessPOSView accessPOSView, Navigator navigator) {
        this.navigator = navigator;
        this.accessPOSView = accessPOSView;
        this.accessPOSView.setReturnListener(this);
    }

    @Override
    public void onReturn() {
        navigator.showView(ViewType.EMPLOYEE_DASHBOARD.toString());
    }

}
