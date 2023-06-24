package fr.iut.uca.dto.surveys;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QuestionTypeDTO {
    @JsonProperty("none")
    NONE,
    @JsonProperty("text")
    TEXT,
    @JsonProperty("single_choice")
    SINGLE_CHOICE,
    @JsonProperty("multi_choice")
    MULTI_CHOICE
}
