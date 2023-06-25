package fr.iut.uca.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OperatorDTO {
    @JsonProperty("equals")
    EQUALS,
    @JsonProperty("before")
    BEFORE,
    @JsonProperty("after")
    AFTER;

    @JsonValue
    public String getValue() {
        return this.name().toLowerCase();
    }
}
