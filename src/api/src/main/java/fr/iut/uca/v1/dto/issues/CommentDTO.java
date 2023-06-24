package fr.iut.uca.v1.dto.issues;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CommentDTO(@JsonProperty("created_at") LocalDate createdAt,
                         String author,
                         String content) { }
