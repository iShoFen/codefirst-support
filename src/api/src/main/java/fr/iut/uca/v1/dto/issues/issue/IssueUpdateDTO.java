package fr.iut.uca.v1.dto.issues.issue;

import fr.iut.uca.v1.dto.issues.IssueStatusDTO;


public record IssueUpdateDTO(String title, IssueStatusDTO status) { }
