package pl.mlipinski.wedding.management.web.view;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import lombok.extern.slf4j.Slf4j;
import pl.mlipinski.wedding.management.domain.entity.Guest;
import pl.mlipinski.wedding.management.domain.entity.Invitation;
import pl.mlipinski.wedding.management.domain.enums.GenderType;
import pl.mlipinski.wedding.management.domain.repository.GuestRepository;
import pl.mlipinski.wedding.management.domain.repository.InvitationRepository;

/**
 * Form for editing Guest.
 */
@SpringView(name = EditGuestView.VIEW_NAME)
@Slf4j
public class EditGuestView extends GridLayout implements View {

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
        setSizeFull();
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
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
        lastNameField.setWidth(50.0f, Unit.PERCENTAGE);
        firstNameField = new TextField(LAST_NAME_CAPTION);
        firstNameField.setWidth(50.0f, Unit.PERCENTAGE);
        ageField = new TextField(AGE_CAPTION);
        ageField.setWidth(50.0f, Unit.PERCENTAGE);
        isComingBox = new CheckBox(IS_COMMING_CAPTION);
        isComingBox.setWidth(50.0f, Unit.PERCENTAGE);
        genderTypeCombo = new ComboBox<>(GENDER_CAPTION, getGenderTypes());
        genderTypeCombo.setWidth(50.0f, Unit.PERCENTAGE);
        saveButton = new Button(SAVE_CAPTION, saveGuest());
    }

    private List<GenderType> getGenderTypes() {
        return Arrays.asList(GenderType.values());
    }

    private Button.ClickListener saveGuest() {
        return (Button.ClickListener) event -> {
            if(binder.isValid()) {
                guestRepository.save(binder.getBean());
                Notification.show("Dodano nowego gościa");
                UI.getCurrent()
                      .getNavigator()
                      .navigateTo(VIEW_NAME);
            }else {
                binder.validate();
                Notification.show("Błąd walidacji");
            }
        };
    }

    private void addComponents() {
        addComponents(invitationCombo, lastNameField, firstNameField, ageField, isComingBox, genderTypeCombo,
              saveButton);
    }

    private void bindFields() {
        binder.forField(invitationCombo)
              .withValidator(new BeanValidator(Guest.class, "invitation"))
              .bind(Guest::getInvitation, Guest::setInvitation);
        binder.forField(lastNameField)
              .withValidator(new BeanValidator(Guest.class, "lastName"))
              .bind(Guest::getLastName, Guest::setLastName);
        binder.forField(firstNameField)
              .withValidator(new BeanValidator(Guest.class, "firstName"))
              .bind(Guest::getFirstName, Guest::setFirstName);
        binder.forField(ageField)
              .withConverter(new StringToIntegerConverter("To nie jest liczba"))
              .withValidator(new BeanValidator(Guest.class, "age"))
              .bind(Guest::getAge, Guest::setAge);
        binder.bind(isComingBox, Guest::isComing, Guest::setComing);
        binder.forField(genderTypeCombo)
              .withValidator(new BeanValidator(Guest.class, "gender"))
              .bind(Guest::getGender, Guest::setGender);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if(!event.getParameters()
              .isEmpty()) {
            log.info("Get parameter: {}", event.getParameters());
            final String param = event.getParameters();
            long guestId = Long.valueOf(param);
            Guest guest = guestRepository.findById(guestId);
            binder.setBean(guest);
        } else {
            binder.setBean(new Guest());
        }
    }
}
