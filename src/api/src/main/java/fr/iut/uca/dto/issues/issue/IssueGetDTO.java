package fr.iut.uca.dto.issues.issue;

import fr.iut.uca.dto.OperatorDTO;
import fr.iut.uca.dto.issues.IssueStatusDTO;

import java.time.LocalDate;

public record IssueGetDTO(int index, int count, IssueStatusDTO status, String title, String author, LocalDate startDate, LocalDate endDate, OperatorDTO operator) { }
