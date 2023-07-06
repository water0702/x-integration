package com.x.integration.ui.view.authorization;

import com.x.integration.domain.model.authorization.Authorization;

import com.x.integration.ui.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "authorizations/:id", layout = MainView.class)
@ViewController("INTEG_Authorization.detail")
@ViewDescriptor("authorization-detail-view.xml")
@EditedEntityContainer("authorizationDc")
@DialogMode(width = "64em")
public class AuthorizationDetailView extends StandardDetailView<Authorization> {
}