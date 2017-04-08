package pl.mlipinski.wedding.management.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private Integer age;
    private String gender;

    @ManyToOne
    private Invitation invitation;
}
