package fr.iut.uca.dto.issues.issuemodel;

import fr.iut.uca.dto.issues.CategoryDTO;
import fr.iut.uca.dto.issues.IssueModelFieldDTO;

import java.util.List;

public record IssueModelInsertDTO(String name, String shortDescription, String description, List<IssueModelFieldDTO> fields, List<CategoryDTO> categories) { }
