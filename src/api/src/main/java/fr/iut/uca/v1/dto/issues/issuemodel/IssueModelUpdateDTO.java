package fr.iut.uca.v1.dto.issues.issuemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.iut.uca.v1.dto.issues.CategoryDTO;
import fr.iut.uca.v1.dto.issues.IssueModelFieldDTO;

import java.util.List;

public record IssueModelUpdateDTO(String name,
                                  @JsonProperty("short_description") String shortDescription,
                                  String description,
                                  CategoryDTO category,
                                  List<IssueModelFieldDTO> fields) {

}
