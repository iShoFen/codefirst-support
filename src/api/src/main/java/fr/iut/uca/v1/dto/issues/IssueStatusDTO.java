package fr.iut.uca.v1.dto.issues;

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
}
