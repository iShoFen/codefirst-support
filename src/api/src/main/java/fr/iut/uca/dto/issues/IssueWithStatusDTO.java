package fr.iut.uca.dto.issues;

import fr.iut.uca.entity.issues.IssueStatusEntity;

import java.util.AbstractMap;
import java.util.List;

public record IssueWithStatusDTO(List<AbstractMap.SimpleEntry<IssueStatusEntity, Long>> issuesCountByStatus) { }