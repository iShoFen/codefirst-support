package fr.iut.uca.extension.issues;

import fr.iut.uca.dto.issues.issuemodel.IssueModelDTO;
import fr.iut.uca.entity.issues.IssueModelEntity;
import fr.iut.uca.model.issues.IssueModel;

import java.util.List;

public abstract class IssueModelExtensions {
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String SHORT_DESCRIPTION = "short_description";
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY = "category";
    public static final String FIELDS = "fields";

    private IssueModelExtensions() {
    }

    public static IssueModelEntity toEntity(IssueModel issueModel) {
        IssueModelEntity issueModelEntity = new IssueModelEntity();

        issueModelEntity.setId(issueModel.getId());
        issueModelEntity.setName(issueModel.getName());
        issueModelEntity.setShortDescription(issueModel.getShortDescription());
        issueModelEntity.setDescription(issueModel.getDescription());
        issueModelEntity.setCategory(CategoryExtensions.toEntity(issueModel.getCategory()));
        issueModelEntity.setFields(IssueModelFieldExtensions.toEntities(issueModel.getFields()));

        return issueModelEntity;
    }

    public static List<IssueModelEntity> toEntities(List<IssueModel> issueModels) {
        return issueModels.stream().map(IssueModelExtensions::toEntity).toList();
    }

    public static IssueModel toModel(IssueModelEntity issueModelEntity) {
        return new IssueModel(
                issueModelEntity.getId(),
                issueModelEntity.getName(),
                issueModelEntity.getShortDescription(),
                issueModelEntity.getDescription(),
                CategoryExtensions.toModel(issueModelEntity.getCategory()),
                IssueModelFieldExtensions.issueModelFieldsToModels(issueModelEntity.getFields())
        );
    }

    public static List<IssueModel> toModels(List<IssueModelEntity> issueModelEntities) {
        return issueModelEntities.stream().map(IssueModelExtensions::toModel).toList();
    }

    public static IssueModelDTO issueModelToDTO(IssueModel model) {
        return new IssueModelDTO(
                model.getId(),
                model.getName(),
                model.getShortDescription(),
                model.getDescription()
        );
    }
}
