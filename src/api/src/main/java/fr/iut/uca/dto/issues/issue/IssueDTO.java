package fr.iut.uca.dto.issues.issue;

import fr.iut.uca.dto.issues.IssueStatusDTO;

import java.time.LocalDate;

public  record IssueDTO(String id, String title, String author, LocalDate creationDate, IssueStatusDTO status) { }