package fr.iut.uca.dto.issues.issue;

import fr.iut.uca.dto.issues.IssueStatusDTO;


public record IssueUpdateDTO(String title, IssueStatusDTO status) { }
