package pl.mlipinski.wedding.management.web.component;

import com.vaadin.data.Binder;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.EditorSaveListener;

import lombok.extern.slf4j.Slf4j;
import pl.mlipinski.wedding.management.domain.entity.Invitation;
import pl.mlipinski.wedding.management.domain.repository.InvitationRepository;

/**
 * Grid for managing invitations.
 */
@Slf4j
public class InvitationGrid extends Grid<Invitation>{

    private static final String INVITATION_ID_HEADER = "Numer zaproszenia";
    private static final String INVITATION_TEXT_HEADER = "Tekst zaproszenia";
    private static final String ADULT_COUNT_HEADER = "Liczba dorosłych";
    private static final String CHILDREN_COUNT_HEADER = "Liczba dzieci";
    private static final String DELIVERED_HEADER = "Dostarczone";
    private static final String CONFIRMED_HEADER = "Potwierdzone";
    private static final String CONFIRMATION_DATE_HEADER = "Data potwierdzenia";

    private static final String INVITATION_ID_ID = "0";
    private static final String INVITATION_TEXT_ID = "1";
    private static final String ADULT_COUNT_ID = "2";
    private static final String CHILDREN_COUNT_ID = "3";
    private static final String DELIVERED_ID = "4";
    private static final String CONFIRMED_ID = "5";
    private static final String CONFIRMATION_DATE_ID = "6";
    private InvitationRepository invitationRepository;

    public InvitationGrid(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
        this.setSizeFull();
        prepareGrid();
    }

    private void prepareGrid() {
        prepareGritColumns();
        setGridItems();
        setSaveListener();
        log.debug("Invitation grid prepared");
    }

    private void prepareGritColumns() {
        Binder<Invitation> binder = this.getEditor().getBinder();

        Binder.Binding<Invitation, Boolean> deliveredBinding = binder.bind(
                new CheckBox(), Invitation::getDelivered, Invitation::setDelivered);
        Binder.Binding<Invitation, Boolean> confirmedBinding = binder.bind(
                new CheckBox(), Invitation::getConfirmed, Invitation::setConfirmed);

        this.addColumn(Invitation::getInvitationId)
                .setCaption(INVITATION_ID_HEADER)
                .setId(INVITATION_ID_ID);
        this.addColumn(Invitation::getInvitationText)
                .setCaption(INVITATION_TEXT_HEADER)
                .setId(INVITATION_TEXT_ID);
        this.addColumn(Invitation::getAdultCount)
                .setCaption(ADULT_COUNT_HEADER)
                .setId(ADULT_COUNT_ID);
        this.addColumn(Invitation::getChildrenCount)
                .setCaption(CHILDREN_COUNT_HEADER)
                .setId(CHILDREN_COUNT_ID);
        this.addColumn(Invitation::getDelivered)
                .setCaption(DELIVERED_HEADER)
                .setId(DELIVERED_ID)
                .setEditorBinding(deliveredBinding);
        this.addColumn(Invitation::getConfirmed)
                .setCaption(CONFIRMED_HEADER)
                .setId(CONFIRMED_ID)
                .setEditorBinding(confirmedBinding);
        this.addColumn(Invitation::getConfirmationDate)
                .setCaption(CONFIRMATION_DATE_HEADER)
                .setId(CONFIRMATION_DATE_ID);

        this.sort(INVITATION_ID_ID);
    }

    private void setGridItems() {
        this.setItems(invitationRepository.findAll());
    }

    private void setSaveListener() {
        this.getEditor().setEnabled(true).addSaveListener((EditorSaveListener<Invitation>) event -> {
            Invitation invitation = event.getBean();
            invitationRepository.save(invitation);
            this.getDataProvider().refreshAll();
        });
    }

}
