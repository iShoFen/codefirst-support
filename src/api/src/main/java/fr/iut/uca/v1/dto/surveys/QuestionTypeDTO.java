package fr.iut.uca.v1.dto.surveys;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum QuestionTypeDTO {
    NONE,
    TEXT,
    SINGLE_CHOICE,
    MULTI_CHOICE;

    @JsonValue
    public String getValue() {
        return this.name().toLowerCase();
    }

    public static QuestionTypeDTO fromString(String value) {
        for (QuestionTypeDTO type : QuestionTypeDTO.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
