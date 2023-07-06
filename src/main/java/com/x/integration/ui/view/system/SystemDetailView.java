package com.x.integration.ui.view.system;

import com.x.integration.domain.model.system.System;

import com.x.integration.ui.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "systems/:id", layout = MainView.class)
@ViewController("INTEG_System.detail")
@ViewDescriptor("system-detail-view.xml")
@EditedEntityContainer("systemDc")
public class SystemDetailView extends StandardDetailView<System> {
}