package fr.iut.uca.v1.model;

import fr.iut.uca.v1.entity.issues.IssueStatusEntity;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IssueWithStatus {
    private final List<AbstractMap.SimpleEntry<IssueStatusEntity, Long>> issuesCountByStatus = new ArrayList<>();

    public IssueWithStatus(List<AbstractMap.SimpleEntry<IssueStatusEntity, Long>> issuesCountByStatus) {
        this.issuesCountByStatus.addAll(issuesCountByStatus);
    }

    public List<AbstractMap.SimpleEntry<IssueStatusEntity, Long>> getIssuesCountByStatus() {
        return Collections.unmodifiableList(issuesCountByStatus);
    }
}
