package pl.mlipinski.wedding.management.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Decision of comming to weedding.
 */
@AllArgsConstructor
public enum AttendanceDecision {
    YES("Tak"),
    NO("Nie"),
    NOT_DETERMINED("Brak informacji"),
    MAYBE("Moze");

    @Getter
    private String text;

    @Override
    public String toString() {
        return text;
    }
}
