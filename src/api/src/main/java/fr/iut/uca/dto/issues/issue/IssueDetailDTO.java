package fr.iut.uca.dto.issues.issue;

import fr.iut.uca.dto.issues.CategoryDTO;
import fr.iut.uca.dto.issues.CommentDTO;
import fr.iut.uca.dto.issues.IssueFieldDTO;
import fr.iut.uca.dto.issues.IssueStatusDTO;
import fr.iut.uca.dto.issues.issuemodel.IssueModelDTO;

import java.time.LocalDate;
import java.util.List;

public record IssueDetailDTO(String id, String title, String author, LocalDate createdAt, CategoryDTO category, IssueStatusDTO status, IssueModelDTO model, List<IssueFieldDTO> fields, List<CommentDTO> comments) { }
