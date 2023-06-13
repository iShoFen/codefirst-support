package fr.iut.uca.dto.surveys.feedback;

import java.time.LocalDate;

public record FeedbackDTO(String id, String surveyId, LocalDate createdAt, String author) { }
