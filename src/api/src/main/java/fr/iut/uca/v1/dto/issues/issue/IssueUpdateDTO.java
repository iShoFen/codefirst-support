package fr.iut.uca.v1.dto.issues.issue;

import fr.iut.uca.v1.dto.issues.IssueStatusDTO;

import java.util.List;


public record IssueUpdateDTO(String title, IssueStatusDTO status, List<IssueFieldInsertDTO> fields) { }
