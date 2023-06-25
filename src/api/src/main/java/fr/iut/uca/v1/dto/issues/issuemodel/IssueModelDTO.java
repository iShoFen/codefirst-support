package fr.iut.uca.v1.dto.issues.issuemodel;

import fr.iut.uca.v1.dto.issues.CategoryDTO;

public record IssueModelDTO(String id, String name, String shortDescription, String description, CategoryDTO category) { }
