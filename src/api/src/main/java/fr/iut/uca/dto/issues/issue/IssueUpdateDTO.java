package fr.iut.uca.dto.issues.issue;

import fr.iut.uca.dto.issues.IssueStatusDTO;

import java.time.LocalDate;

public record IssueUpdateDTO(String id, String title, IssueStatusDTO status) { }
