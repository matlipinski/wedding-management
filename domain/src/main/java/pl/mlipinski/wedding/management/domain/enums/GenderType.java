package pl.mlipinski.wedding.management.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Available gender types.
 */
@AllArgsConstructor
public enum GenderType {
    MALE("mezczyzna"),
    FEMALE("kobieta");

    @Getter
    private String text;

    @Override
    public String toString() {
        return text;
    }
}
