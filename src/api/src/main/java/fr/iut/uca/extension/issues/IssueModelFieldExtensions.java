package fr.iut.uca.extension.issues;

import fr.iut.uca.dto.issues.IssueModelFieldDTO;
import fr.iut.uca.entity.issues.IssueModelFieldEntity;
import fr.iut.uca.model.issues.IssueModelField;

import java.util.List;

public abstract class IssueModelFieldExtensions {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String REQUIRED = "required";

    private IssueModelFieldExtensions() { }

    public static IssueModelFieldEntity toEntity(IssueModelField issueModelField) {
        IssueModelFieldEntity issueModelFieldEntity = new IssueModelFieldEntity();

        issueModelFieldEntity.setTitle(issueModelField.getTitle());
        issueModelFieldEntity.setDescription(issueModelField.getDescription());
        issueModelFieldEntity.setRequired(issueModelField.isRequired());

        return issueModelFieldEntity;
    }

    public static List<IssueModelFieldEntity> toEntities(List<IssueModelField> issueModelFields) {
        return issueModelFields.stream().map(IssueModelFieldExtensions::toEntity).toList();
    }

    public static IssueModelField issueModelFieldToModel(IssueModelFieldEntity issueModelFieldEntity) {
        return new IssueModelField(
                issueModelFieldEntity.getTitle(),
                issueModelFieldEntity.getDescription(),
                issueModelFieldEntity.isRequired()
        );
    }

    public static List<IssueModelField> issueModelFieldsToModels(List<IssueModelFieldEntity> issueModelFieldEntities) {
        return issueModelFieldEntities.stream().map(IssueModelFieldExtensions::issueModelFieldToModel).toList();
    }

    public static IssueModelField issueModelFieldToModel(IssueModelFieldDTO issueModelFieldDTO) {
        return new IssueModelField(
                issueModelFieldDTO.title(),
                issueModelFieldDTO.description(),
                issueModelFieldDTO.required()
        );
    }

    public static List<IssueModelField> issueModelFieldDTOsToModels(List<IssueModelFieldDTO> issueModelFieldDTOs) {
        return issueModelFieldDTOs.stream().map(IssueModelFieldExtensions::issueModelFieldToModel).toList();
    }
}
