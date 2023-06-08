package fr.iut.uca.utils.issues;

import fr.iut.uca.entity.issues.IssueModelFieldEntity;
import fr.iut.uca.model.issues.IssueModelField;

import java.util.List;

public abstract class IssueModelFiledExtensions {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String REQUIRED = "required";

    public static IssueModelFieldEntity toEntity(IssueModelField issueModelField) {
        IssueModelFieldEntity issueModelFieldEntity = new IssueModelFieldEntity();

        issueModelFieldEntity.setTitle(issueModelField.getTitle());
        issueModelFieldEntity.setDescription(issueModelField.getDescription());
        issueModelFieldEntity.setRequired(issueModelField.isRequired());

        return issueModelFieldEntity;
    }

    public static List<IssueModelFieldEntity> toEntities(List<IssueModelField> issueModelFields) {
        return issueModelFields.stream().map(IssueModelFiledExtensions::toEntity).toList();
    }

    public static IssueModelField toModel(IssueModelFieldEntity issueModelFieldEntity) {
        return new IssueModelField(
                issueModelFieldEntity.getTitle(),
                issueModelFieldEntity.getDescription(),
                issueModelFieldEntity.isRequired()
        );
    }

    public static List<IssueModelField> toModels(List<IssueModelFieldEntity> issueModelFieldEntities) {
        return issueModelFieldEntities.stream().map(IssueModelFiledExtensions::toModel).toList();
    }
}
