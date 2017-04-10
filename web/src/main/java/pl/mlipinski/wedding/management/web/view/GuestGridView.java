package pl.mlipinski.wedding.management.web.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mlipinski.wedding.management.domain.repository.GuestRepository;
import pl.mlipinski.wedding.management.domain.repository.InvitationRepository;
import pl.mlipinski.wedding.management.web.component.GuestGrid;
import pl.mlipinski.wedding.management.web.component.InvitationGrid;

/**
 * Generate view for list of all guests.
 */
@SpringView(name = GuestGridView.VIEW_NAME)
public class GuestGridView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "guestGridView";
    private GuestGrid guestGrid;
    private Button newGuestButton;

    @Autowired
    public GuestGridView(GuestRepository guestRepository) {
        this.guestGrid = new GuestGrid(guestRepository);
        this.newGuestButton = new Button("Dodaj gościa", getClickListener());
        prepareLayout();
    }

    private Button.ClickListener getClickListener() {
        return (Button.ClickListener) event -> {
            UI.getCurrent().getNavigator().navigateTo(EditGuestView.VIEW_NAME);
        };
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    private void prepareLayout() {
        this.addComponent(newGuestButton);
        this.addComponentsAndExpand(guestGrid);
        this.setSizeFull();
    }
}
