package pl.mlipinski.wedding.management.web.component;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import pl.mlipinski.wedding.management.web.view.GuestGridView;
import pl.mlipinski.wedding.management.web.view.InvitationGridView;

/**
 * Base Menu Bar.
 */
public class MainMenu extends MenuBar {

    public MainMenu() {
        this.addItem("Lista zaproszeń", navigateToCommand(InvitationGridView.VIEW_NAME));
        this.addItem("Lista gości", navigateToCommand(GuestGridView.VIEW_NAME));
    }

    private Command navigateToCommand(String viewName) {
        return (Command) selectedItem -> UI.getCurrent().getNavigator().navigateTo(viewName);
    }
}
