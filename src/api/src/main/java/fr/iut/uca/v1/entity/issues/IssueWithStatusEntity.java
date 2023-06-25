package fr.iut.uca.v1.entity.issues;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IssueWithStatusEntity {
    private final List<AbstractMap.SimpleEntry<IssueStatusEntity, Long>> issuesCountByStatus = new ArrayList<>();

    public List<AbstractMap.SimpleEntry<IssueStatusEntity, Long>> getIssuesCountByStatus() {
        return Collections.unmodifiableList(issuesCountByStatus);
    }

    public void setIssuesCountByStatus(List<AbstractMap.SimpleEntry<IssueStatusEntity, Long>> issuesCountByStatus) {
        this.issuesCountByStatus.clear();
        this.issuesCountByStatus.addAll(issuesCountByStatus);
    }
}
