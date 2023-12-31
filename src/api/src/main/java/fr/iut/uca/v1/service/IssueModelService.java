package fr.iut.uca.v1.service;

import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.v1.dto.issues.issuemodel.*;
import fr.iut.uca.v1.entity.issues.IssueModelEntity;
import fr.iut.uca.exception.InsertException;
import fr.iut.uca.v1.extension.issues.CategoryExtensions;
import fr.iut.uca.v1.extension.issues.IssueModelExtensions;
import fr.iut.uca.v1.extension.issues.IssueModelFieldExtensions;
import fr.iut.uca.v1.model.issues.Category;
import fr.iut.uca.v1.model.issues.IssueModel;
import fr.iut.uca.v1.model.issues.IssueModelField;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.v1.repository.issues.IIssueModelRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Issue model service
 */
@ApplicationScoped
public class IssueModelService {

    /**
     * Issue model repository
     */
    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    IIssueModelRepository issueModelRepository;

    /**
     * Get all issue models with pagination and filters
     * @param issueModelGetDTO DTO with filters
     * @return List of issue models
     * @throws IllegalArgumentException if filters are invalid
     */
    public List<IssueModelDTO> getAll(IssueModelGetDTO issueModelGetDTO) throws IllegalArgumentException {
        List<IssueModelEntity> entities;
        var name = issueModelGetDTO.getName();
        var index = issueModelGetDTO.getIndex();
        var count = issueModelGetDTO.getCount();

        if (name != null) {
            entities = issueModelRepository.getIssueModelsByNameContaining(name,index, count);
        } else {
            entities = issueModelRepository.getItems(index, count);
        }

        var issueModel =  IssueModelExtensions.entitiesToModels(entities);
        return IssueModelExtensions.modelsToDTOs(issueModel);
    }

    /**
     * Get one issue model by id
     * @param id Issue model id
     * @return Issue model
     * @throws NotFoundException if the issue model cannot be found
     */
    public IssueModelDetailDTO getOne(String id) throws NotFoundException {
        Optional<IssueModelEntity> optionalIssueModel = issueModelRepository.getItemById(id);

        if (optionalIssueModel.isEmpty()) {
            throw new NotFoundException("The issue model cannot be found");
        }

        var issueModel = IssueModelExtensions.entityToModel(optionalIssueModel.get());
        return IssueModelExtensions.modelToDetailDTO(issueModel);
    }

    /**
     * Create an issue model
     * @param issueModelInsertDTO Issue model to create
     * @return Created issue model
     * @throws InsertException if an error occurred while inserting the issue model
     * @throws IllegalArgumentException if the issue model is invalid
     */
    public IssueModelDetailDTO create(IssueModelInsertDTO issueModelInsertDTO) throws InsertException, IllegalArgumentException {

        Category category = CategoryExtensions.categoryDTOToModel(issueModelInsertDTO.category());
        List<IssueModelField> fields = IssueModelFieldExtensions.dtosToModels(issueModelInsertDTO.fields());

        var issueModel = new IssueModel(
            issueModelInsertDTO.name(),
            issueModelInsertDTO.shortDescription(),
            issueModelInsertDTO.description(),
            category,
            fields
        );

        Optional<IssueModelEntity> result = issueModelRepository.addItem(IssueModelExtensions.modelToEntity(issueModel));

        if (result.isEmpty()) {
            throw new InsertException("An error occured while inserting the issue model");
        }

        var resultIssueModel = IssueModelExtensions.entityToModel(result.get());
        return IssueModelExtensions.modelToDetailDTO(resultIssueModel);
    }

    /**
     * Update an issue model
     * @param id Issue model id
     * @param issueModelUpdateDTO Issue model to update
     * @return Updated issue model
     * @throws NotFoundException if the issue model cannot be found
     * @throws IllegalArgumentException if the issue model is invalid
     * @throws UpdateException if an error occurred while updating the issue model
     */
    public IssueModelDetailDTO update(String id, IssueModelUpdateDTO issueModelUpdateDTO) throws NotFoundException, IllegalArgumentException, UpdateException {

        Optional<IssueModelEntity> optionalIssueModel = issueModelRepository.getItemById(id);

        if (optionalIssueModel.isEmpty()) {
            throw new NotFoundException("The issue model was not found");
        }

        IssueModel issueModel = IssueModelExtensions.entityToModel(optionalIssueModel.get());

        issueModel.setName(issueModelUpdateDTO.name());
        issueModel.setShortDescription(issueModelUpdateDTO.shortDescription());
        issueModel.setDescription(issueModelUpdateDTO.description());
        issueModel.setCategory(CategoryExtensions.categoryDTOToModel(issueModelUpdateDTO.category()));
        issueModelUpdateDTO.fields().forEach(field -> issueModel.updateField(IssueModelFieldExtensions.dtoToModel(field)));

        Optional<IssueModelEntity> result = issueModelRepository.updateItem(issueModel.getId(), IssueModelExtensions.modelToEntity(issueModel));

        if (result.isEmpty()) {
            throw new UpdateException("An error occured while updating the issue model");
        }

        var resultIssueModel = IssueModelExtensions.entityToModel(result.get());
        return IssueModelExtensions.modelToDetailDTO(resultIssueModel);
    }

    /**
     * Delete an issue model
     * @param id Issue model id
     * @throws NotFoundException if the issue model cannot be found
     */
    public void delete(String id)
            throws NotFoundException {

        boolean result = issueModelRepository.deleteItem(id);

        if (!result) {
            throw new NotFoundException("The issue model was not found");
        }
    }
}
