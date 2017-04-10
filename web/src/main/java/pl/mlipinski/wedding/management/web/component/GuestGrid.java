package pl.mlipinski.wedding.management.web.component;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.components.grid.EditorSaveListener;
import com.vaadin.ui.renderers.ButtonRenderer;
import lombok.extern.slf4j.Slf4j;
import pl.mlipinski.wedding.management.domain.entity.Guest;
import pl.mlipinski.wedding.management.domain.repository.GuestRepository;
import pl.mlipinski.wedding.management.web.view.EditGuestView;

/**
 * Grid for managing guest.
 */
@Slf4j
public class GuestGrid extends Grid<Guest>{

    private static final String LAST_NAME_HEADER = "Nazwisko";
    private static final String INVITATION_ID_HEADER = "Numer Zaproszenia";
    private static final String FIRST_NAME_HEADER = "Imię";
    private static final String AGE_HEADER = "Wiek";
    private static final String IS_COMING_HEADER = "Czy będzie";


    private static final String INVITATION_ID_ID = "0";
    private static final String LAST_NAME_ID = "1";
    private static final String FIRST_NAME_ID = "2";
    private static final String AGE_ID = "3";
    private static final String IS_COMING_ID = "4";

    private GuestRepository guestRepository;

    public GuestGrid(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
        this.setSizeFull();
        prepareGrid();
    }

    private void prepareGrid() {
        prepareGritColumns();
        setGridItems();
        log.debug("Guest grid prepared");
    }

    private void prepareGritColumns() {
        this.addColumn(guest -> guest.getInvitation().getInvitationId())
                .setCaption(INVITATION_ID_HEADER)
                .setId(INVITATION_ID_ID);
        this.addColumn(Guest::getLastName)
                .setCaption(LAST_NAME_HEADER)
                .setId(LAST_NAME_ID);
        this.addColumn(Guest::getFirstName)
                .setCaption(FIRST_NAME_HEADER)
                .setId(FIRST_NAME_ID);
        this.addColumn(Guest::getAge)
                .setCaption(AGE_HEADER)
                .setId(AGE_ID);
        this.addColumn(Guest::isComing)
                .setCaption(IS_COMING_HEADER)
                .setId(IS_COMING_ID);
        this.addColumn(person -> "Delete",
                new ButtonRenderer(clickEvent -> {
                    String navigationState = EditGuestView.VIEW_NAME + "/" + ((Guest) clickEvent.getItem()).getId();
                    UI.getCurrent().getNavigator().navigateTo(navigationState);
                }));
        this.sort(INVITATION_ID_ID);
    }

    private void setGridItems() {
        this.setItems(guestRepository.findAll());
    }

}
