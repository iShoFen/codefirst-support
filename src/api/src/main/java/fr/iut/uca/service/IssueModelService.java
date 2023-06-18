package fr.iut.uca.service;

import fr.iut.uca.entity.issues.CategoryEntity;
import fr.iut.uca.entity.issues.IssueModelEntity;
import fr.iut.uca.entity.issues.IssueModelFieldEntity;
import fr.iut.uca.exception.InsertException;
import fr.iut.uca.extension.issues.CategoryExtensions;
import fr.iut.uca.extension.issues.IssueModelExtensions;
import fr.iut.uca.extension.issues.IssueModelFieldExtensions;
import fr.iut.uca.model.issues.Category;
import fr.iut.uca.model.issues.IssueModel;
import fr.iut.uca.model.issues.IssueModelField;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.repository.issues.IIssueModelRepository;
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

    public List<IssueModel> getAll(int index, int count) {
        List<IssueModelEntity> entities = issueModelRepository.getItems(index, count);

        return IssueModelExtensions.issueModelEntitiesToModels(entities);
    }

    public IssueModel getOne(String id)
            throws NotFoundException {

        Optional<IssueModelEntity> optionalIssueModel = issueModelRepository.getItemById(id);

        if (optionalIssueModel.isEmpty()) {
            throw new NotFoundException("The issue model cannot be found");
        }

        return IssueModelExtensions.issueModelEntityToModel(optionalIssueModel.get());
    }

    public IssueModel create(String name, String shortDescription, String description, Category category, List<IssueModelField> fields) throws InsertException {
        IssueModelEntity entity = new IssueModelEntity();

        entity.setName(name);
        entity.setShortDescription(shortDescription);
        entity.setDescription(description);

        CategoryEntity categoryEntity = CategoryExtensions.categoryToEntity(category);
        entity.setCategory(categoryEntity);

        List<IssueModelFieldEntity> fieldEntities = IssueModelFieldExtensions.toEntities(fields);
        entity.setFields(fieldEntities);

        Optional<IssueModelEntity> result = issueModelRepository.addItem(entity);

        if (result.isEmpty()) {
            throw new InsertException("An error occured while inserting the issue model");
        }

        return IssueModelExtensions.issueModelEntityToModel(result.get());
    }

    public void delete(String id)
            throws NotFoundException {

        boolean result = issueModelRepository.deleteItem(id);

        if (!result) {
            throw new NotFoundException("The issue model was not found");
        }
    }
}
