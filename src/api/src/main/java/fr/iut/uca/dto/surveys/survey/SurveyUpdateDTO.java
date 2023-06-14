package fr.iut.uca.dto.surveys.survey;

import java.time.LocalDate;
import java.util.List;

public record SurveyUpdateDTO(LocalDate publishedAt, LocalDate endAt, String title, String description, List<String> questions) { }
