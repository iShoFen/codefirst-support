package fr.iut.uca.dto.surveys.survey;

import fr.iut.uca.dto.OperatorDTO;

import java.time.LocalDate;

public record SurveyGetDTO(int index, int count, LocalDate createdAt, LocalDate publishedAt, LocalDate  endAt, LocalDate endDate, OperatorDTO operator) { }
