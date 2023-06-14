package fr.iut.uca.dto.issues.issuemodel;

import fr.iut.uca.dto.issues.CategoryDTO;
import fr.iut.uca.dto.issues.IssueModelFieldDTO;

import java.util.List;

public record IssueModelUpdateDTO(String name, String shortDescription, String description, CategoryDTO category, List<IssueModelFieldDTO> fields) {

}
