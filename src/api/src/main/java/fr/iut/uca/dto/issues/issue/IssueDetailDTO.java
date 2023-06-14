package fr.iut.uca.dto.issues.issue;

import fr.iut.uca.dto.issues.CommentDTO;
import fr.iut.uca.dto.issues.IssueFieldDTO;
import fr.iut.uca.dto.issues.IssueModelInfoDTO;
import fr.iut.uca.dto.issues.IssueStatusDTO;

import java.time.LocalDate;
import java.util.List;

public record IssueDetailDTO(String id, String title, String author, LocalDate creationDate, IssueStatusDTO status, IssueModelInfoDTO model, List<IssueFieldDTO> fields, List<CommentDTO> comments) { }
