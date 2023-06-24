package fr.iut.uca.dto.surveys.survey;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.iut.uca.dto.surveys.QuestionDTO;

import java.time.LocalDate;
import java.util.List;

public record SurveyDetailDTO(String id,
                              @JsonProperty("created_at") LocalDate createdAt,
                              @JsonProperty("published_at") LocalDate publishedAt,
                              @JsonProperty("end_at") LocalDate endAt,
                              String title,
                              String description,
                              List<QuestionDTO> questions) {
}
