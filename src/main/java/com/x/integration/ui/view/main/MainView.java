package com.x.integration.ui.view.main;

import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.kit.component.dropdownbutton.DropdownButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@ViewController("INTEG_MainView")
@ViewDescriptor("main-view.xml")
public class MainView extends StandardMainView {
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @ViewComponent
    private DropdownButton userBtn;

    @Subscribe
    public void onInit(final InitEvent event) {
        userBtn.setText(currentAuthentication.getUser().getUsername());
    }
}
