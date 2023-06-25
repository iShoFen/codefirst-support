package fr.iut.uca.v1.service;

import fr.iut.uca.v1.dto.issues.issuemodel.IssueModelInsertDTO;
import fr.iut.uca.v1.dto.issues.issuemodel.IssueModelUpdateDTO;
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

@ApplicationScoped
public class IssueModelService {

    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    IIssueModelRepository issueModelRepository;

    public List<IssueModel> getAll(int index, int count, String name) throws IllegalArgumentException {
        List<IssueModelEntity> entities;
        if (name != null) {
            entities = issueModelRepository.getIssueModelsByNameContaining(name,index, count);
        } else {
            entities = issueModelRepository.getItems(index, count);
        }

        return IssueModelExtensions.entitiesToModels(entities);
    }

    public IssueModel getOne(String id) throws NotFoundException {
        Optional<IssueModelEntity> optionalIssueModel = issueModelRepository.getItemById(id);

        if (optionalIssueModel.isEmpty()) {
            throw new NotFoundException("The issue model cannot be found");
        }

        return IssueModelExtensions.entityToModel(optionalIssueModel.get());
    }

    public IssueModel create(IssueModelInsertDTO issueModelInsertDTO) throws InsertException, IllegalArgumentException {

        Category category = CategoryExtensions.categoryDTOToModel(issueModelInsertDTO.category());
        List<IssueModelField> fields = IssueModelFieldExtensions.dtosToModels(issueModelInsertDTO.fields());

        var issue = new IssueModel(
            issueModelInsertDTO.name(),
            issueModelInsertDTO.shortDescription(),
            issueModelInsertDTO.description(),
            category,
            fields
        );

        Optional<IssueModelEntity> result = issueModelRepository.addItem(IssueModelExtensions.modelToEntity(issue));

        if (result.isEmpty()) {
            throw new InsertException("An error occured while inserting the issue model");
        }

        return IssueModelExtensions.entityToModel(result.get());
    }

    public IssueModel update(String id, IssueModelUpdateDTO issueModelUpdateDTO) throws NotFoundException, IllegalArgumentException {

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

        Optional<IssueModelEntity> result = issueModelRepository.updateItem(IssueModelExtensions.modelToEntity(issueModel));

        if (result.isEmpty()) {
            throw new NotFoundException("An error occured while updating the issue model");
        }

        return IssueModelExtensions.entityToModel(result.get());
    }

    public void delete(String id)
            throws NotFoundException {

        boolean result = issueModelRepository.deleteItem(id);

        if (!result) {
            throw new NotFoundException("The issue model was not found");
        }
    }
}
