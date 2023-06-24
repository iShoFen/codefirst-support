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

    public static List<IssueModelField> issueModelFieldDTOsToModels(List<IssueModelFieldDTO> fields) {
        List<IssueModelField> models = new ArrayList<>();
        fields.forEach(dto -> models.add(issueModelFieldToModel(dto)));
        return models;
    }

    public static IssueModelFieldDTO issueModelFieldToDTO(IssueModelField field) {
        return new IssueModelFieldDTO(
                field.getTitle(),
                field.getDescription(),
                field.isRequired()
        );
    }

    public static List<IssueModelFieldDTO> issueModelFieldsToDTOs(List<IssueModelField> fields) {
        List<IssueModelFieldDTO> dtos = new ArrayList<>();
        fields.forEach(field -> dtos.add(issueModelFieldToDTO(field)));
        return dtos;
    }
}
