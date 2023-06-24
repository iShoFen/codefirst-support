package fr.iut.uca.dto.issues.issue;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record IssueInsertDTO(String title,
                             String author,
                             @JsonProperty("created_At") LocalDate createdAt,
                             @JsonProperty("model") String modelId,
                             List<IssueFieldInsertDTO> fields) {
}
