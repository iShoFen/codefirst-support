package fr.iut.uca.dto.surveys.feedback;

import java.util.List;

public record FeedbackUpdateDTO(String id, List<String> answer) { }
