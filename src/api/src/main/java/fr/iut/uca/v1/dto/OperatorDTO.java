package fr.iut.uca.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OperatorDTO {
    @JsonProperty("undefined")
    UNDEFINED,
    @JsonProperty("equals")
    EQUALS,
    @JsonProperty("before")
    BEFORE,
    @JsonProperty("after")
    AFTER,
}
