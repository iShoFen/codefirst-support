package fr.iut.uca.dto.surveys.feedback;

import fr.iut.uca.dto.surveys.QuestionInfoDTO;

import java.time.LocalDate;
import java.util.List;

public record FeedbackDetailDTO(String id, String surveyId, LocalDate createdAt, String author, QuestionInfoDTO question, List<String> answer) { }
