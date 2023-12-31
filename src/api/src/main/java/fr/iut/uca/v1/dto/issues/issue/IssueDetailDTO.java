package fr.iut.uca.v1.dto.issues.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.iut.uca.v1.dto.issues.CategoryDTO;
import fr.iut.uca.v1.dto.issues.CommentDTO;
import fr.iut.uca.v1.dto.issues.IssueFieldDTO;
import fr.iut.uca.v1.dto.issues.IssueStatusDTO;
import fr.iut.uca.v1.dto.issues.issuemodel.IssueModelInfoDTO;

import java.time.LocalDate;
import java.util.List;

public record IssueDetailDTO(String id,
                             String title,
                             String author,
                             @JsonProperty("created_at") LocalDate createdAt,
                             CategoryDTO category,
                             IssueStatusDTO status,
                             IssueModelInfoDTO model,
                             List<IssueFieldDTO> fields,
                             List<CommentDTO> comments) { }
