package fr.iut.uca.dto.issues.issuemodel;

import fr.iut.uca.dto.issues.CategoryDTO;

public record IssueModelDTO(String id, String name, String shortDescription, String description, CategoryDTO category) { }
