package fr.iut.uca.dto.surveys.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record FeedbackDTO(String id,
                          @JsonProperty("survey_id") String surveyId,
                          @JsonProperty("created_at") LocalDate createdAt,
                          String author) { }
