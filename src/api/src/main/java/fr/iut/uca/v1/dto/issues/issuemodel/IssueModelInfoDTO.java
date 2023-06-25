package fr.iut.uca.v1.dto.issues.issuemodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IssueModelInfoDTO(String name,
                                @JsonProperty("short_description") String shortDescription,
                                String description) { }
