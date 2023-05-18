package com.ana.client.presenter.impl;

import com.ana.client.gui.Navigator;
import com.ana.client.gui.ViewType;
import com.ana.client.listener.LogoutListener;
import com.ana.client.listener.LookupListener;
import com.ana.client.model.ManagerDashboardModel;
import com.ana.client.presenter.ManagerDashboardPresenter;
import com.ana.client.utility.EmployeeContext;
import com.ana.client.view.ManagerDashboardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ManagerDashboardPresenterImpl implements LogoutListener, LookupListener, ManagerDashboardPresenter {

    private static final Logger logger = LoggerFactory.getLogger(ManagerDashboardPresenterImpl.class);

    private final Navigator navigator;

    private final ManagerDashboardModel managerDashboardModel;
    private final ManagerDashboardView managerDashboardView;

    public ManagerDashboardPresenterImpl(ManagerDashboardModel managerDashboardModel, ManagerDashboardView managerDashboardView, Navigator navigator) {
        this.navigator = navigator;
        this.managerDashboardModel = managerDashboardModel;
        this.managerDashboardView = managerDashboardView;

        addListeners();
    }

    private void addListeners() {
        managerDashboardView.setLogoutListener(this);
        managerDashboardView.setLookupListener(this);
    }

    @Override
    public void onLogout() {
        navigator.showView(ViewType.LOGIN.toString());
    }

    @Override
    public void onLookup(String employeeId) {
        lookupEmployee(employeeId);
    }

    @Override
    public void lookupEmployee(String employeeId) {
        // lookup employee in using the managerDashboardModel
        Optional<Long> employeeIdOptional = managerDashboardModel.lookupEmployee(employeeId);

        // if the employee exists, log a message
        employeeIdOptional.ifPresent(id -> {
            final String message = String.format("Employee with ID: %d found!", id);
            logger.info(message);

            // save Employee context




            managerDashboardView.showEmployeeFound();
        });

        // if the employee does not exist, log a message
        if (employeeIdOptional.isEmpty()) {
            final String message = String.format("Employee with ID: %s not found!", employeeId);
            logger.error(message);

            // show the label: "Employee not found!"
            managerDashboardView.showEmployeeNotFound();
        }



    }

}
