//
// Copyright (c) 2019 Couchbase, Inc All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
package com.couchbase.travelsample.ui.view;

import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import com.couchbase.travelsample.model.Flight;
import com.couchbase.travelsample.ui.controller.UserController;
import com.couchbase.travelsample.ui.view.widgets.FlightCellRenderer;


@Singleton
public final class UserView extends Page<UserController> {
    private final static Logger LOGGER = Logger.getLogger(GuestView.class.getName());

    public static final String PAGE_NAME = "USER";

    private JPanel panel;
    private JList<Flight> flights;
    private JButton logoutButton;
    private JButton findHotelsButton;
    private JButton findFlightsButton;

    @Inject
    public UserView(UserController controller) {
        super(PAGE_NAME, controller);

        logoutButton.addActionListener(e -> logout());

        findFlightsButton.addActionListener(e -> controller.selectFlight());

        findHotelsButton.addActionListener(e -> controller.selectHotel());

        flights.setModel(controller.getFlightsModel());
        flights.setCellRenderer(new FlightCellRenderer());
    }

    @Override
    public JPanel getView() { return panel; }

    @Override
    protected void onOpen(@Nullable Page<?> prevPage) { controller.fetchFlights(); }

    @Override
    protected void onClose() { }
}