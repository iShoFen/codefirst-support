package fr.iut.uca.dto.issues.issuemodel;

import fr.iut.uca.dto.issues.CategoryDTO;
import fr.iut.uca.dto.issues.IssueModelFieldDTO;

import java.util.List;

public record IssueModelDTO(String id, String name, String shortDescription, String description) { }
