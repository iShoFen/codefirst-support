package fr.iut.uca.v1.dto.issues;

import com.fasterxml.jackson.annotation.JsonValue;

public enum IssueStatusDTO {
    OPEN,
    CLOSED;

    @JsonValue
    public String getValue() {
        return this.name().toLowerCase();
    }

    public static IssueStatusDTO fromString(String value) {
        for (IssueStatusDTO status : IssueStatusDTO.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }
}
