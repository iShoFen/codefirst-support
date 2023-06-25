
package fr.iut.uca.v1.model.issues;

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

    public void addIssueCountByStatus(AbstractMap.SimpleEntry<IssueStatusEntity, Long> issueCountByStatus) {
        this.issuesCountByStatus.add(issueCountByStatus);
    }

    public void addIssueCountByStatus(IssueStatusEntity issueStatus, Long count) {
        this.issuesCountByStatus.add(new AbstractMap.SimpleEntry<>(issueStatus, count));
    }

    public boolean removeIssueCountByStatus(AbstractMap.SimpleEntry<IssueStatusEntity, Long> issueCountByStatus) {
        return this.issuesCountByStatus.remove(issueCountByStatus);
    }

    public boolean removeIssueCountByStatus(IssueStatusEntity issueStatus) {
        return this.issuesCountByStatus.removeIf(issueCountByStatus -> issueCountByStatus.getKey().equals(issueStatus));
    }
}
