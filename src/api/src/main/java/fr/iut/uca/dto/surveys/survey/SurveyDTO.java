package fr.iut.uca.dto.surveys.survey;

import java.time.LocalDate;

public record SurveyDTO(String id, LocalDate createdAt, LocalDate publishedAt, LocalDate endAt, String title, String description) { }
