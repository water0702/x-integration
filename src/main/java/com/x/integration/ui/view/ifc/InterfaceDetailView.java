package com.x.integration.ui.view.ifc;

import com.x.integration.domain.model.ifc.Interface;

import com.x.integration.ui.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "interfaces/:id", layout = MainView.class)
@ViewController("INTEG_Interface.detail")
@ViewDescriptor("interface-detail-view.xml")
@EditedEntityContainer("interfaceDc")
public class InterfaceDetailView extends StandardDetailView<Interface> {
}