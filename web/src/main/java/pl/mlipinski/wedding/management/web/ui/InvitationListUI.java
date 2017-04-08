package pl.mlipinski.wedding.management.web.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mlipinski.wedding.management.domain.entity.Invitation;
import pl.mlipinski.wedding.management.domain.repository.InvitationRepository;

/**
 * Generate view for list of whole invitations.
 */
@SpringUI
public class InvitationListUI extends UI{

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
        layout.addComponentsAndExpand(invitationGrid);
        layout.setSizeFull();
        invitationGrid.setSizeFull();
        invitationGrid.setItems(invitationRepository.findAll());
    }
}
