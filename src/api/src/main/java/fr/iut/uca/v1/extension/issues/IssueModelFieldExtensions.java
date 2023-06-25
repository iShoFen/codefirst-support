package fr.iut.uca.v1.extension.issues;

import fr.iut.uca.v1.dto.issues.IssueModelFieldDTO;
import fr.iut.uca.v1.entity.issues.IssueModelFieldEntity;
import fr.iut.uca.v1.model.issues.IssueModelField;

import java.util.ArrayList;
import java.util.List;

public abstract class IssueModelFieldExtensions {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String REQUIRED = "required";

    private IssueModelFieldExtensions() {
    }

    public static IssueModelFieldEntity modelToEntity(IssueModelField issueModelField) {
        IssueModelFieldEntity issueModelFieldEntity = new IssueModelFieldEntity();

        issueModelFieldEntity.setTitle(issueModelField.getTitle());
        issueModelFieldEntity.setDescription(issueModelField.getDescription());
        issueModelFieldEntity.setRequired(issueModelField.isRequired());

        return issueModelFieldEntity;
    }

    public static List<IssueModelFieldEntity> modelsToEntities(List<IssueModelField> issueModelFields) {
        return issueModelFields.stream().map(IssueModelFieldExtensions::modelToEntity).toList();
    }

    public static IssueModelField entityToModel(IssueModelFieldEntity issueModelFieldEntity) {
        return new IssueModelField(
                issueModelFieldEntity.getTitle(),
                issueModelFieldEntity.getDescription(),
                issueModelFieldEntity.isRequired()
        );
    }

    public static List<IssueModelField> entitiesToModels(List<IssueModelFieldEntity> issueModelFieldEntities) {
        return issueModelFieldEntities.stream().map(IssueModelFieldExtensions::entityToModel).toList();
    }

    public static IssueModelFieldDTO modelToDTO(IssueModelField field) {
        return new IssueModelFieldDTO(
                field.getTitle(),
                field.getDescription(),
                field.isRequired()
        );
    }

    public static List<IssueModelFieldDTO> modelsToDTOs(List<IssueModelField> fields) {
        return fields.stream().map(IssueModelFieldExtensions::modelToDTO).toList();
    }

    public static IssueModelField dtoToModel(IssueModelFieldDTO issueModelFieldDTO) {
        return new IssueModelField(
                issueModelFieldDTO.title(),
                issueModelFieldDTO.description(),
                issueModelFieldDTO.required()
        );
    }

    public static List<IssueModelField> dtosToModels(List<IssueModelFieldDTO> fields) {
        return fields.stream().map(IssueModelFieldExtensions::dtoToModel).toList();
    }
}
