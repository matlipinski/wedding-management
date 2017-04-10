package pl.mlipinski.wedding.management.domain.entity;

import lombok.Data;
import pl.mlipinski.wedding.management.domain.enums.GenderType;

import javax.persistence.*;

/**
 * Data of guests connected with account.
 */
@Data
@Entity
@Table(name = "guest")
public class Guest extends BaseEntity {
    private String firstName;
    private String lastName;
    private boolean isComing;
    private int age;
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @ManyToOne
    private Invitation invitation;
}
