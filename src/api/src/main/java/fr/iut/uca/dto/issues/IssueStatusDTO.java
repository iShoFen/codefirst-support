package fr.iut.uca.dto.issues;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IssueStatusDTO {
    @JsonProperty("undefined")
    UNDEFINED,
    @JsonProperty("open")
    OPEN,
    @JsonProperty("closed")
    CLOSED
}
