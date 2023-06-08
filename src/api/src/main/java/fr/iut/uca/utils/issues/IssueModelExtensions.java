package fr.iut.uca.utils.issues;

import fr.iut.uca.entity.issues.IssueModelEntity;
import fr.iut.uca.model.issues.IssueModel;
import org.bson.types.ObjectId;

import java.util.List;

public abstract class IssueModelExtensions {
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String SHORT_DESCRIPTION = "short_description";
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY = "category";
    public static final String FIELDS = "fields";

    public static IssueModelEntity toEntity(IssueModel issueModel) {
        IssueModelEntity issueModelEntity = new IssueModelEntity();

        issueModelEntity.setId(new ObjectId(issueModel.getId()));
        issueModelEntity.setName(issueModel.getName());
        issueModelEntity.setShortDescription(issueModel.getShortDescription());
        issueModelEntity.setDescription(issueModel.getDescription());
        issueModelEntity.setCategory(CategoryExtensions.toEntity(issueModel.getCategory()));
        issueModelEntity.setFields(IssueModelFiledExtensions.toEntities(issueModel.getFields()));

        return issueModelEntity;
    }

    public static List<IssueModelEntity> toEntities(List<IssueModel> issueModels) {
        return issueModels.stream().map(IssueModelExtensions::toEntity).toList();
    }

    public static IssueModel toModel(IssueModelEntity issueModelEntity) {
        return new IssueModel(
                issueModelEntity.getId().toString(),
                issueModelEntity.getName(),
                issueModelEntity.getShortDescription(),
                issueModelEntity.getDescription(),
                CategoryExtensions.toModel(issueModelEntity.getCategory()),
                IssueModelFiledExtensions.toModels(issueModelEntity.getFields())
        );
    }

    public static List<IssueModel> toModels(List<IssueModelEntity> issueModelEntities) {
        return issueModelEntities.stream().map(IssueModelExtensions::toModel).toList();
    }
}
