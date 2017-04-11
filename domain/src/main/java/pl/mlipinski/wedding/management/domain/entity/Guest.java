package pl.mlipinski.wedding.management.domain.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import pl.mlipinski.wedding.management.domain.enums.GenderType;

/**
 * Data of guests connected with account.
 */
@Data
@Entity
@Table(name = "guest")
public class Guest extends BaseEntity {

    @NotEmpty(message = "Pole nie może być puste")
    private String firstName;
    @NotEmpty(message = "Pole nie może być puste")
    private String lastName;
    private boolean isComing;
    @NotNull(message = "Pole nie może być puste")
    private int age;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Pole nie może być puste")
    private GenderType gender;

    @ManyToOne
    @NotNull(message = "Pole nie może być puste")
    private Invitation invitation;
}
