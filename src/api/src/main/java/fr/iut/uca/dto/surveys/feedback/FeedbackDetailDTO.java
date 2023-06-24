package fr.iut.uca.dto.surveys.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.iut.uca.dto.surveys.QuestionInfoDTO;

import java.time.LocalDate;
import java.util.List;

public record FeedbackDetailDTO(String id,
                                @JsonProperty("survey_id") String surveyId,
                                @JsonProperty("created_at") LocalDate createdAt,
                                String author,
                                QuestionInfoDTO question,
                                List<String> answer) { }
