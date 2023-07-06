package com.x.integration.ui.view.system;

import com.x.integration.domain.model.system.System;

import com.x.integration.ui.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "systems", layout = MainView.class)
@ViewController("INTEG_System.list")
@ViewDescriptor("system-list-view.xml")
@LookupComponent("systemsDataGrid")
@DialogMode(width = "64em")
public class SystemListView extends StandardListView<System> {
}