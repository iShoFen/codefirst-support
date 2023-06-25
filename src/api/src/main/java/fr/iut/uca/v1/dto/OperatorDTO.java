package fr.iut.uca.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OperatorDTO {
    EQUALS,
    BEFORE,
    AFTER;

    @JsonValue
    public String getValue() {
        return this.name().toLowerCase();
    }

    public static OperatorDTO fromString(String value) {
        for (OperatorDTO operator : OperatorDTO.values()) {
            if (operator.getValue().equalsIgnoreCase(value)) {
                return operator;
            }
        }
        return null;
    }
}
