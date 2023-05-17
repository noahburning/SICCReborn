package com.ana.client.gui;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that manages the navigation between views.
 * The views are stored in a {@link Map} and are displayed in a {@link JPanel}.
 * The {@link Navigator} is used to switch between views.
 * <p>
 * {@code @copyright}  Copyright (c) 2023
 *
 * @author Ali Ahmed
 */

@Component
public class Navigator {

    /**
     * The {@link JPanel} that contains the views.
     */
    private final JPanel container;

    /**
     * The {@link CardLayout} that is used to display the views.
     */
    private final CardLayout cardLayout;

    /**
     * A {@link Map} that stores the views.
     * The key is the name of the view and the value is the {@link JPanel} that contains the view.
     */
    private final Map<String, JPanel> views;

    /**
     * Creates a new {@link Navigator} and adds it to the {@code parentContainer}.
     *
     * @param parentContainer The {@link Container} that the {@link Navigator} will be added to.
     */
    public Navigator(Container parentContainer) {
        this.container = new JPanel();
        this.cardLayout = new CardLayout();
        this.container.setLayout(cardLayout);
        views = new HashMap<>();
        parentContainer.add(container);
    }

    /**
     * Adds a view to the {@link Navigator}.
     *
     * @param viewName The name of the view.
     * @param view     The {@link JPanel} that contains the view.
     */
    public void addView(String viewName, JPanel view) {
        views.put(viewName, view);
        container.add(view, viewName);
    }

    /**
     * Shows the view with the specified name.
     *
     * @param viewName The name of the view.
     */
    public void showView(String viewName) {
        cardLayout.show(container, viewName);
    }

    /**
     * Removes the view with the specified name.
     *
     * @param viewName The name of the view.
     */
    public void removeView(String viewName) {
        views.remove(viewName);
        container.remove(views.get(viewName));
    }

    /**
     * Removes all views from the {@link Navigator}.
     */
    public void removeAllViews() {
        views.clear();
        container.removeAll();
    }

}
