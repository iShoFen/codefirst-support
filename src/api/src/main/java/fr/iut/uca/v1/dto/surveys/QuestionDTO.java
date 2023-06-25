package fr.iut.uca.v1.dto.surveys;

import java.util.List;

public record QuestionDTO(String title, String description, QuestionTypeDTO type, List<String> choices) { }
