package com.x.integration.ui.view.authorization;

import com.x.integration.domain.model.authorization.Authorization;

import com.x.integration.ui.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "authorizations", layout = MainView.class)
@ViewController("INTEG_Authorization.list")
@ViewDescriptor("authorization-list-view.xml")
@LookupComponent("authorizationsDataGrid")
@DialogMode(width = "64em")
public class AuthorizationListView extends StandardListView<Authorization> {
}