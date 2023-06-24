package fr.iut.uca.dto.surveys.survey;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record SurveyDTO(String id,
                        @JsonProperty("created_at") LocalDate createdAt,
                        @JsonProperty("published_at") LocalDate publishedAt,
                        @JsonProperty("end_at") LocalDate endAt,
                        String title,
                        String description) { }
