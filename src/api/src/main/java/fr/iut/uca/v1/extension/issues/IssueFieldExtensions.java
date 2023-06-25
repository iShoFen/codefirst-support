package fr.iut.uca.v1.extension.issues;

import fr.iut.uca.v1.dto.issues.IssueFieldDTO;
import fr.iut.uca.v1.entity.issues.IssueFieldEntity;
import fr.iut.uca.v1.model.issues.IssueField;

import java.util.ArrayList;
import java.util.List;

public abstract class IssueFieldExtensions {
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String REQUIRED = "required";
    public static final String VALUE = "value";

    private IssueFieldExtensions() {
    }

    public static IssueFieldEntity toEntity(IssueField issueField) {
        IssueFieldEntity issueFieldEntity = new IssueFieldEntity();

        issueFieldEntity.setTitle(issueField.getTitle());
        issueFieldEntity.setDescription(issueField.getDescription());
        issueFieldEntity.setRequired(issueField.isRequired());
        issueFieldEntity.setValue(issueField.getValue());

        return issueFieldEntity;
    }

    public static List<IssueFieldEntity> toEntities(List<IssueField> issueFields) {
        return issueFields.stream().map(IssueFieldExtensions::toEntity).toList();
    }

    public static IssueField issueFieldEntityToModel(IssueFieldEntity issueFieldEntity) {
        return new IssueField(
                issueFieldEntity.getTitle(),
                issueFieldEntity.getDescription(),
                issueFieldEntity.isRequired(),
                issueFieldEntity.getValue()
        );
    }

    public static List<IssueField> issueFieldEntitiesToModels(List<IssueFieldEntity> issueFieldEntities) {
        return issueFieldEntities.stream().map(IssueFieldExtensions::issueFieldEntityToModel).toList();
    }

    public static IssueField issueFieldDTOToModel(IssueFieldDTO issueFieldDTO) {
        return new IssueField(
                issueFieldDTO.title(),
                issueFieldDTO.description(),
                issueFieldDTO.required(),
                issueFieldDTO.value()
        );
    }

    public static List<IssueField> issueFieldDTOsToModels(List<IssueFieldDTO> issueFieldEntities) {
        return issueFieldEntities.stream().map(IssueFieldExtensions::issueFieldDTOToModel).toList();
    }

    public static IssueFieldDTO issueFieldToDTO(IssueField field) {
        return new IssueFieldDTO(
                field.getTitle(),
                field.getDescription(),
                field.isRequired(),
                field.getValue()
        );
    }

    public static List<IssueFieldDTO> issueFieldsToDTOs(List<IssueField> fields) {
        List<IssueFieldDTO> dtos = new ArrayList<>();
        fields.forEach(field -> dtos.add(issueFieldToDTO(field)));
        return dtos;
    }
}
