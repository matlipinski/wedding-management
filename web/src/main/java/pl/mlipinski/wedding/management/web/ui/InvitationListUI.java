package pl.mlipinski.wedding.management.web.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mlipinski.wedding.management.web.component.MainMenu;

/**
 * .
 */
@SpringUI
public class InvitationListUI extends UI {

    private Navigator navigator;
    private SpringViewProvider springViewProvider;
    private VerticalLayout mainLayout;
    private VerticalLayout contentLayout;

    @Autowired
    public InvitationListUI(SpringViewProvider springViewProvider) {
        this.springViewProvider = springViewProvider;
        this.mainLayout = new VerticalLayout();
        this.contentLayout = new VerticalLayout();
        this.navigator = new Navigator(this, contentLayout);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        navigator.addProvider(springViewProvider);
    }

    private void setupLayout() {
        setContent(mainLayout);
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.addComponent(new MainMenu());
        mainLayout.addComponentsAndExpand(contentLayout);
    }
}
