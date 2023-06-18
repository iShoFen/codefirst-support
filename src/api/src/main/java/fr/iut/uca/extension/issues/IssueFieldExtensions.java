package fr.iut.uca.extension.issues;

import fr.iut.uca.dto.issues.IssueFieldDTO;
import fr.iut.uca.entity.issues.IssueFieldEntity;
import fr.iut.uca.model.issues.IssueField;

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

    public static IssueField toModel(IssueFieldEntity issueFieldEntity) {
        return new IssueField(
                issueFieldEntity.getTitle(),
                issueFieldEntity.getDescription(),
                issueFieldEntity.isRequired(),
                issueFieldEntity.getValue()
        );
    }

    public static List<IssueField> toModels(List<IssueFieldEntity> issueFieldEntities) {
        return issueFieldEntities.stream().map(IssueFieldExtensions::toModel).toList();
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
