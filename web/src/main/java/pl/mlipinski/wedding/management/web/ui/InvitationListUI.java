package pl.mlipinski.wedding.management.web.ui;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.EditorSaveEvent;
import com.vaadin.ui.components.grid.EditorSaveListener;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mlipinski.wedding.management.domain.entity.Invitation;
import pl.mlipinski.wedding.management.domain.repository.InvitationRepository;

/**
 * Generate view for list of whole invitations.
 */
@SpringUI
public class InvitationListUI extends UI {

    private InvitationRepository invitationRepository;
    private Grid<Invitation> invitationGrid;
    private VerticalLayout layout;


    @Autowired
    public InvitationListUI(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
        this.invitationGrid = new Grid<>(Invitation.class);
        this.layout = new VerticalLayout();
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(layout);
        prepareLayout();
    }

    private void prepareLayout() {
        layout.addComponentsAndExpand(invitationGrid);
        layout.setSizeFull();
        prepareGrid();
    }

    private void prepareGrid() {
        invitationGrid.setSizeFull();
        prepareGritColumns();
        setGridColumns();
        setGridItems();
        setSaveListener();
    }

    private void prepareGritColumns() {
        Binder<Invitation> binder = invitationGrid.getEditor().getBinder();

        Binder.Binding<Invitation, Boolean> deliveredBinding = binder.bind(
                new CheckBox(), Invitation::getDelivered, Invitation::setDelivered);
        Binder.Binding<Invitation, Boolean> confirmedBinding = binder.bind(
                new CheckBox(), Invitation::getConfirmed, Invitation::setConfirmed);

        invitationGrid.addColumn(Invitation::getInvitationId).setCaption("Numer zaproszenia").setId("0");
        invitationGrid.addColumn(Invitation::getInvitationText).setCaption("Tekst zaproszenia").setId("1");
        invitationGrid.addColumn(Invitation::getAdultCount).setCaption("Liczba dorosłych").setId("2");
        invitationGrid.addColumn(Invitation::getChildrenCount).setCaption("Liczba dzieci").setId("3");
        invitationGrid.addColumn(Invitation::getDelivered)
                .setCaption("Dostarczone")
                .setId("4")
                .setEditorBinding(deliveredBinding);

        invitationGrid.addColumn(Invitation::getConfirmed)
                .setCaption("Potwierdzone")
                .setId("5")
                .setEditorBinding(confirmedBinding);
        invitationGrid.addColumn(Invitation::getConfirmationDate).setCaption("Data potwierdzenia").setId("6");

        invitationGrid.sort("0");
    }

    private void setGridColumns() {
        invitationGrid.setColumns("0", "1", "2", "3", "4", "5", "6");
    }

    private void setGridItems() {
        invitationGrid.setItems(invitationRepository.findAll());
    }

    private void setSaveListener() {
        invitationGrid.getEditor().setEnabled(true).addSaveListener((EditorSaveListener<Invitation>) event -> {
            Invitation invitation = event.getBean();
            invitationRepository.save(invitation);
            invitationGrid.getDataProvider().refreshAll();
        });
    }
}
