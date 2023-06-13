package fr.iut.uca.dto.surveys;

import java.util.List;

public record QuestionDTO(String title, String description, QuestionTypeDTO type, List<String> choices) { }
