package fr.iut.uca.v1.extension.issues;

import fr.iut.uca.v1.dto.issues.IssueStatusDTO;
import fr.iut.uca.v1.entity.issues.IssueStatusEntity;
import fr.iut.uca.v1.model.issues.IssueStatus;

public abstract class IssueStatusExtensions {

    private IssueStatusExtensions() { }

    public static IssueStatusEntity modelToEntity(IssueStatus status) throws IllegalArgumentException {
        return IssueStatusEntity.valueOf(status.name());
    }

    public static IssueStatus entityToModel(IssueStatusEntity status) throws IllegalArgumentException {
        return IssueStatus.valueOf(status.name());
    }

    public static IssueStatusDTO modelToDTO(IssueStatus status) throws IllegalArgumentException {
        return IssueStatusDTO.valueOf(status.name());
    }

    public static IssueStatus dtoToModel(IssueStatusDTO status) throws IllegalArgumentException {
        return IssueStatus.valueOf(status.name());
    }
}
