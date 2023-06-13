package fr.iut.uca.dto.issues.issue;

import fr.iut.uca.dto.issues.IssueFieldDTO;
import fr.iut.uca.dto.issues.IssueModelInfoDTO;

import java.time.LocalDate;
import java.util.List;

public record IssueInsertDTO(String title, String author, LocalDate createdAt, IssueModelInfoDTO model, List<IssueFieldDTO> fields) { }
