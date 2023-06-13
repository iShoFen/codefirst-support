package fr.iut.uca.dto.surveys.survey;

import fr.iut.uca.dto.surveys.QuestionDTO;

import java.time.LocalDate;
import java.util.List;

public record SurveyDetailDTO(String id, LocalDate createdAt, LocalDate publishedAt, LocalDate endAt, String title, String description, List<QuestionDTO> questions) {
}
