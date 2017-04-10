package pl.mlipinski.wedding.management.web.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main view of application containing buttons to navigate.
 */
@SpringView(name = StartupView.VIEW_NAME)
public class StartupView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
