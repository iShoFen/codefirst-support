package fr.iut.uca.extension.issues;

import fr.iut.uca.entity.issues.IssueStatusEntity;
import fr.iut.uca.model.issues.IssueStatus;

public abstract class IssueStatusExtensions {

    private IssueStatusExtensions() { }

    public static IssueStatusEntity toEntity(IssueStatus status) {
        try {
            return IssueStatusEntity.valueOf(status.name());
        } catch (Exception e) {
            return IssueStatusEntity.UNDEFINED;
        }
    }

    public static IssueStatus toModel(IssueStatusEntity status) {
        try {
            return IssueStatus.valueOf(status.name());
        } catch (Exception e) {
            return IssueStatus.UNDEFINED;
        }
    }
}
