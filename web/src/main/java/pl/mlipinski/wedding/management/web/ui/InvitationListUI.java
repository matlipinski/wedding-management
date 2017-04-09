package pl.mlipinski.wedding.management.web.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mlipinski.wedding.management.domain.repository.InvitationRepository;

/**
 * Generate view for list of whole invitations.
 */
@SpringUI
public class InvitationListUI extends UI {

    private InvitationGrid invitationGrid;
    private VerticalLayout layout;

    @Autowired
    public InvitationListUI(InvitationRepository invitationRepository) {
        this.invitationGrid = new InvitationGrid(invitationRepository);
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
    }


}
