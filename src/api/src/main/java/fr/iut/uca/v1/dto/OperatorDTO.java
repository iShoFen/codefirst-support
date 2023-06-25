package fr.iut.uca.v1.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    @JsonCreator
    public static OperatorDTO fromValue(String value) throws IllegalArgumentException {
        if (value == null || value.isBlank()) {
            return null;
        }
        for (OperatorDTO operator : OperatorDTO.values()) {
            if (operator.name().equalsIgnoreCase(value)) {
                return operator;
            }
        }

        throw new IllegalArgumentException("The value " + value + " is not a valid operator");
    }


}
