package pl.mlipinski.wedding.management.web.view;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mlipinski.wedding.management.domain.entity.Guest;
import pl.mlipinski.wedding.management.domain.entity.Invitation;
import pl.mlipinski.wedding.management.domain.enums.GenderType;
import pl.mlipinski.wedding.management.domain.repository.GuestRepository;
import pl.mlipinski.wedding.management.domain.repository.InvitationRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Form for editing Guest.
 */
@SpringView(name = EditGuestView.VIEW_NAME)
@Slf4j
public class EditGuestView extends FormLayout implements View {

    public static final String VIEW_NAME = "editGuestView";
    private static final String INVITATION_CAPTION = "Zaproszenie";
    private static final String FIRST_NAME_CAPTION = "Nazwisko";
    private static final String LAST_NAME_CAPTION = "Imię";
    private static final String AGE_CAPTION = "Wiek";
    private static final String IS_COMMING_CAPTION = "Czy będzie";
    private static final String GENDER_CAPTION = "Płeć";
    private static final String SAVE_CAPTION = "Zapisz";

    private ComboBox<Invitation> invitationCombo;
    private TextField lastNameField;
    private TextField firstNameField;
    private TextField ageField;
    private CheckBox isComingBox;
    private ComboBox<GenderType> genderTypeCombo;
    private Button saveButton;
    private Binder<Guest> binder;

    private InvitationRepository invitationRepository;
    private GuestRepository guestRepository;

    @Autowired
    public EditGuestView(InvitationRepository invitationRepository, GuestRepository guestRepository) {
        this.invitationRepository = invitationRepository;
        this.guestRepository = guestRepository;
        prepareFormControls();
    }

    private void prepareFormControls() {
        binder = new Binder<>();
        initiateControls();
        addComponents();
        bindFields();
    }

    private void initiateControls() {
        invitationCombo = new ComboBox<>(INVITATION_CAPTION, invitationRepository.findAll());
        invitationCombo.setItemCaptionGenerator(Invitation::getInvitationText);
        invitationCombo.setWidth(50.0f, Unit.PERCENTAGE);
        lastNameField = new TextField(FIRST_NAME_CAPTION);
        firstNameField = new TextField(LAST_NAME_CAPTION);
        ageField = new TextField(AGE_CAPTION);
        isComingBox = new CheckBox(IS_COMMING_CAPTION);
        genderTypeCombo = new ComboBox<>(GENDER_CAPTION, getGenderTypes());
        saveButton = new Button(SAVE_CAPTION, saveGuest());
    }

    private List<GenderType> getGenderTypes() {
        return Arrays.asList(GenderType.values());
    }

    private Button.ClickListener saveGuest() {
        return (Button.ClickListener) event -> {
            guestRepository.save(binder.getBean());
            Notification.show("Dodano nowego gościa");
            UI.getCurrent().getNavigator().navigateTo(VIEW_NAME);

        };
    }

    private void addComponents() {
        addComponents(invitationCombo, lastNameField, firstNameField, ageField,
                isComingBox, genderTypeCombo, saveButton);
    }

    private void bindFields() {
        binder.setBean(new Guest());
        binder.bind(invitationCombo, Guest::getInvitation, Guest::setInvitation);
        binder.bind(lastNameField, Guest::getLastName, Guest::setLastName);
        binder.bind(firstNameField, Guest::getFirstName, Guest::setFirstName);
        binder.forMemberField(ageField)
                .withConverter(new StringToIntegerConverter(
                        "To nie jest liczba"))
                .bind(Guest::getAge, Guest::setAge);
        binder.bind(isComingBox, Guest::isComing, Guest::setComing);
        binder.bind(genderTypeCombo, Guest::getGender, Guest::setGender);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if(!event.getParameters().isEmpty()){
            log.info("Get parameter: {}", event.getParameters());
        }
    }
}
