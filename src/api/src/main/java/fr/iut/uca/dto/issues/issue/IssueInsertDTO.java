package fr.iut.uca.dto.issues.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.iut.uca.dto.issues.IssueFieldDTO;

import java.time.LocalDate;
import java.util.List;

public record IssueInsertDTO(String title,
                             String author,
                             LocalDate createdAt,
                             @JsonProperty("model") String modelId,
                             List<IssueFieldDTO> fields) {
}
