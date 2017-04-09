package pl.mlipinski.wedding.management.web.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * .
 */
@SpringUI
public class InvitationListUI extends UI {

    private Navigator navigator;
    private SpringViewProvider springViewProvider;

    @Autowired
    public InvitationListUI(SpringViewProvider springViewProvider) {
        this.springViewProvider = springViewProvider;
        this.navigator = new Navigator(this, this);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        navigator.addProvider(springViewProvider);
    }
}
