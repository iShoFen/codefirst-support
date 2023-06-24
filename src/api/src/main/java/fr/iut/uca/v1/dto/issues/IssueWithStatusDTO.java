package fr.iut.uca.v1.dto.issues;

import fr.iut.uca.v1.entity.issues.IssueStatusEntity;

import java.util.AbstractMap;
import java.util.List;

public record IssueWithStatusDTO(List<AbstractMap.SimpleEntry<IssueStatusEntity, Long>> issuesCountByStatus) { }