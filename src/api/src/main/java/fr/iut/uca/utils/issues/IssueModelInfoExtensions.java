package fr.iut.uca.utils.issues;

import fr.iut.uca.entity.issues.IssueModelInfoEntity;
import fr.iut.uca.model.issues.IssueModelInfo;

import java.util.List;

public abstract class IssueModelInfoExtensions {

    public static final String NAME = "name";
    public static final String SHORT_DESCRIPTION = "short_description";
    public static final String DESCRIPTION = "description";

    public static IssueModelInfoEntity toEntity(IssueModelInfo issueModelInfo) {
        IssueModelInfoEntity issueModelInfoEntity = new IssueModelInfoEntity();

        issueModelInfoEntity.setName(issueModelInfo.getName());
        issueModelInfoEntity.setShortDescription(issueModelInfo.getShortDescription());
        issueModelInfoEntity.setDescription(issueModelInfo.getDescription());

        return issueModelInfoEntity;
    }

    public static List<IssueModelInfoEntity> toEntities(List<IssueModelInfo> issueModelInfos) {
        return issueModelInfos.stream().map(IssueModelInfoExtensions::toEntity).toList();
    }

    public static IssueModelInfo toModel(IssueModelInfoEntity issueModelInfoEntity) {
        return new IssueModelInfo(
                issueModelInfoEntity.getName(),
                issueModelInfoEntity.getShortDescription(),
                issueModelInfoEntity.getDescription()
        );
    }

    public static List<IssueModelInfo> toModels(List<IssueModelInfoEntity> issueModelInfoEntities) {
        return issueModelInfoEntities.stream().map(IssueModelInfoExtensions::toModel).toList();
    }
}
