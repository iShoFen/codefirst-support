package fr.iut.uca.v1.extension.issues;

import fr.iut.uca.v1.dto.issues.issuemodel.IssueModelInfoDTO;
import fr.iut.uca.v1.entity.issues.IssueModelEntity;
import fr.iut.uca.v1.entity.issues.IssueModelInfoEntity;
import fr.iut.uca.v1.model.issues.IssueModelInfo;

import java.util.List;

public abstract class IssueModelInfoExtensions {
    public static final String NAME = "name";
    public static final String SHORT_DESCRIPTION = "short_description";
    public static final String DESCRIPTION = "description";

    private IssueModelInfoExtensions() {
    }

    public static IssueModelInfoEntity issueModelInfoToEntity(IssueModelInfo issueModelInfo) {
        IssueModelEntity issueModelEntity = new IssueModelEntity();

        issueModelEntity.setName(issueModelInfo.getName());
        issueModelEntity.setShortDescription(issueModelInfo.getShortDescription());
        issueModelEntity.setDescription(issueModelInfo.getDescription());

        return issueModelEntity;
    }

    public static List<IssueModelInfoEntity> issueModelInfoToEntities(List<IssueModelInfo> issueModelInfos) {
        return issueModelInfos.stream().map(IssueModelInfoExtensions::issueModelInfoToEntity).toList();
    }

    public static IssueModelInfo issueModelInfoEntityToModel(IssueModelInfoEntity issueModelInfoEntity) {
        return new IssueModelInfo(
                issueModelInfoEntity.getName(),
                issueModelInfoEntity.getShortDescription(),
                issueModelInfoEntity.getDescription()
        );
    }

    public static List<IssueModelInfo> issueModelEntitiesToModels(List<IssueModelInfoEntity> issueModelInfoEntities) {
        return issueModelInfoEntities.stream().map(IssueModelInfoExtensions::issueModelInfoEntityToModel).toList();
    }

    public static IssueModelInfoDTO issueModelInfoToDTO(IssueModelInfo model) {
        return new IssueModelInfoDTO(
                model.getName(),
                model.getShortDescription(),
                model.getDescription()
        );
    }

    public static List<IssueModelInfoDTO> issueModelInfosToDTOs(List<IssueModelInfo> models) {
        return models.stream().map(IssueModelInfoExtensions::issueModelInfoToDTO).toList();
    }
}
