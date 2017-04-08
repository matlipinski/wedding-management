package pl.mlipinski.wedding.management.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    private Boolean confirmed;
    private LocalDate confirmationDate;

}
