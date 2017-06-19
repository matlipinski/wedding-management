package pl.mlipinski.wedding.management.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Attendance Day at wedding party.
 */
@Getter
@AllArgsConstructor
public enum AttendanceDay {
    FIRST("Pierwszy"),
    SECOND("Drugi"),
    BOTH("Oba");

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
