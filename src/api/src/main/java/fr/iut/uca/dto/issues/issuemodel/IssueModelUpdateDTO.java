package fr.iut.uca.dto.issues.issuemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.iut.uca.dto.issues.CategoryDTO;
import fr.iut.uca.dto.issues.IssueModelFieldDTO;

import java.util.List;

public record IssueModelUpdateDTO(String name,
                                  @JsonProperty("short_description") String shortDescription,
                                  String description,
                                  CategoryDTO category, List<IssueModelFieldDTO> fields) {

}
