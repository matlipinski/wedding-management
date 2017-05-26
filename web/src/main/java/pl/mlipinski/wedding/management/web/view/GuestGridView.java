package pl.mlipinski.wedding.management.web.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mlipinski.wedding.management.domain.entity.Guest;
import pl.mlipinski.wedding.management.domain.repository.GuestRepository;
import pl.mlipinski.wedding.management.web.component.GuestGrid;

import java.util.List;

/**
 * Generate view for list of all guests.
 */
@SpringView(name = GuestGridView.VIEW_NAME)
public class GuestGridView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "guestGridView";
    private GuestGrid guestGrid;
    private GuestRepository guestRepository;
    private Button newGuestButton;
    private TextField searchTextView;
    private Button searchButton;

    @Autowired
    public GuestGridView(GuestRepository guestRepository) {
        this.guestGrid = new GuestGrid(guestRepository);
        this.guestRepository = guestRepository;
        this.newGuestButton = new Button("Dodaj gościa", newGuestButtonClick());
        this.searchTextView = new TextField();
        this.searchButton = new Button("Szukaj", searchButtonClick());
        prepareLayout();
    }

    private Button.ClickListener searchButtonClick() {
        return (Button.ClickListener) event -> {
            String searchText = searchTextView.getValue();
            List<Guest> searchResults = guestRepository.findByFirstNameOrLastName(searchText);
            guestGrid.setItems(searchResults);

        };
    }
    private Button.ClickListener newGuestButtonClick() {
        return (Button.ClickListener) event -> {
            UI.getCurrent().getNavigator().navigateTo(EditGuestView.VIEW_NAME);
        };
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    private void prepareLayout() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.addComponent(newGuestButton);
        hl.addComponent(searchTextView);
        hl.addComponent(searchButton);

        this.addComponent(hl);
        this.addComponentsAndExpand(guestGrid);
        this.setSizeFull();
    }
}
