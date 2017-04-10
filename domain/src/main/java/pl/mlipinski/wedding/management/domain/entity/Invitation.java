package pl.mlipinski.wedding.management.domain.entity;

import lombok.Data;
import pl.mlipinski.wedding.management.domain.enums.WhomGuestType;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Data of users.
 */
@Data()
@Entity
@Table(name = "invitation")
public class Invitation extends BaseEntity {

    private String invitationText;
    private int invitationId;
    private String email;
    private int adultCount;
    private int childrenCount;
    private Boolean delivered;
    private Boolean confirmed;
    private LocalDate confirmationDate;
    @Enumerated(EnumType.STRING)
    private WhomGuestType whomGuestType;

}
