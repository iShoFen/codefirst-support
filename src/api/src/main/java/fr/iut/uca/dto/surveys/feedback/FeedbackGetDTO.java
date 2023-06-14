package fr.iut.uca.dto.surveys.feedback;

import fr.iut.uca.dto.OperatorDTO;

import java.time.LocalDate;

public record FeedbackGetDTO(int index, int count, String nameFilter, LocalDate startDate, LocalDate endDate, OperatorDTO operator) { }
