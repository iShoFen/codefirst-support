package fr.iut.uca.v1.dto.surveys.survey;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.iut.uca.v1.dto.surveys.QuestionDTO;

import java.time.LocalDate;
import java.util.List;

public record SurveyUpdateDTO(@JsonProperty("published_at") LocalDate publishedAt,
                              @JsonProperty("end_at") LocalDate endAt,
                              String title,
                              String description,
                              List<QuestionDTO> questions) { }
