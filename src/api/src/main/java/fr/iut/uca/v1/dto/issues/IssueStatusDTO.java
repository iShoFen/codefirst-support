package fr.iut.uca.v1.dto.issues;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum IssueStatusDTO {
    @JsonProperty("open")
    OPEN,
    @JsonProperty("closed")
    CLOSED;

    @JsonValue
    public String getValue() {
        return this.name().toLowerCase();
    }

    @JsonCreator
    public static IssueStatusDTO fromValue(String value) throws IllegalArgumentException {
        if (value == null || value.isBlank()) {
            return null;
        }

        for (IssueStatusDTO status : IssueStatusDTO.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }

        throw new  IllegalArgumentException("The value " + value + " is not a valid status");
    }

}
