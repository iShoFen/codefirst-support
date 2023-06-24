package fr.iut.uca.v1.dto.surveys.survey;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record SurveyInsertDTO(@JsonProperty("created_at") LocalDate createdAt,
                              @JsonProperty("published_at") LocalDate publishedAt,
                              @JsonProperty("end_at") LocalDate endAt,
                              String title,
                              String description,
                              List<String> questions) { }
