package fr.iut.uca.extension.issues;

import fr.iut.uca.dto.issues.issuemodel.IssueModelDTO;
import fr.iut.uca.dto.issues.issuemodel.IssueModelDetailDTO;
import fr.iut.uca.entity.issues.IssueModelEntity;
import fr.iut.uca.model.issues.IssueModel;

import java.util.ArrayList;
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
        issueModelEntity.setCategory(CategoryExtensions.categoryToEntity(issueModel.getCategory()));
        issueModelEntity.setFields(IssueModelFieldExtensions.toEntities(issueModel.getFields()));

        return issueModelEntity;
    }

    public static List<IssueModelEntity> toEntities(List<IssueModel> issueModels) {
        return issueModels.stream().map(IssueModelExtensions::toEntity).toList();
    }

    public static IssueModel issueModelEntityToModel(IssueModelEntity issueModelEntity) {
        return new IssueModel(
                issueModelEntity.getId(),
                issueModelEntity.getName(),
                issueModelEntity.getShortDescription(),
                issueModelEntity.getDescription(),
                CategoryExtensions.toModel(issueModelEntity.getCategory()),
                IssueModelFieldExtensions.issueModelFieldsToModels(issueModelEntity.getFields())
        );
    }

    public static List<IssueModel> issueModelEntitiesToModels(List<IssueModelEntity> issueModelEntities) {
        return issueModelEntities.stream().map(IssueModelExtensions::issueModelEntityToModel).toList();
    }

    public static IssueModelDTO issueModelToDTO(IssueModel model) {
        return new IssueModelDTO(
                model.getId(),
                model.getName(),
                model.getShortDescription(),
                model.getDescription(),
                CategoryExtensions.categoryToDTO(model.getCategory())
        );
    }

    public static IssueModelDetailDTO issueModelToDetailDTO(IssueModel model) {
        return new IssueModelDetailDTO(
                model.getId(),
                model.getName(),
                model.getShortDescription(),
                model.getDescription(),
                CategoryExtensions.categoryToDTO(model.getCategory()),
                IssueModelFieldExtensions.issueModelFieldsToDTOs(model.getFields())
        );
    }

    public static List<IssueModelDTO> issueModelsToDTOs(List<IssueModel> issueModels) {
        List<IssueModelDTO> dtos = new ArrayList<>();
        issueModels.forEach(model -> dtos.add(issueModelToDTO(model)));
        return dtos;
    }
}
