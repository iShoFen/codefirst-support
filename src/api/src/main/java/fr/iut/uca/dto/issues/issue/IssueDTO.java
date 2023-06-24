package fr.iut.uca.dto.issues.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.iut.uca.dto.issues.CategoryDTO;
import fr.iut.uca.dto.issues.IssueStatusDTO;

import java.time.LocalDate;

public  record IssueDTO(String id,
                        String title,
                        String author,
                        @JsonProperty("created_at") LocalDate createdAt,
                        CategoryDTO category,
                        IssueStatusDTO status) { }
