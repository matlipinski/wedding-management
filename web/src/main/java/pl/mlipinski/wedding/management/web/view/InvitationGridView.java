package pl.mlipinski.wedding.management.web.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mlipinski.wedding.management.domain.repository.InvitationRepository;
import pl.mlipinski.wedding.management.web.component.InvitationGrid;

/**
 * Generate view for list of all invitations.
 */
@SpringView(name = InvitationGridView.VIEW_NAME)
public class InvitationGridView extends VerticalLayout implements View {
    
    public static final String VIEW_NAME = "invitationGridView";
    private InvitationGrid invitationGrid;

    @Autowired
    public InvitationGridView(InvitationRepository invitationRepository) {
        this.invitationGrid = new InvitationGrid(invitationRepository);
        prepareLayout();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    private void prepareLayout() {
        this.addComponentsAndExpand(invitationGrid);
        this.setSizeFull();
    }
}
