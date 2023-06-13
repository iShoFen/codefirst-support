package fr.iut.uca.dto.issues;

import java.time.LocalDate;

public record CommentDTO(LocalDate createdAt, String author, String content) { }
