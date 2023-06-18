package fr.iut.uca.service;

import fr.iut.uca.entity.issues.*;
import fr.iut.uca.exception.InsertException;
import fr.iut.uca.model.issues.Issue;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.repository.issues.IIssueModelRepository;
import fr.iut.uca.repository.issues.IIssueRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static fr.iut.uca.extension.issues.IssueExtensions.issueToModel;
import static fr.iut.uca.extension.issues.IssueExtensions.issuesToModels;

@ApplicationScoped
public class IssueService {

    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    IIssueRepository issueRepository;

    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    IIssueModelRepository issueModelRepository;


    public List<Issue> getAll(int index,
                              int count) throws IllegalArgumentException {

        List<IssueEntity> issueEntities = issueRepository.getItems(index, count);

        return issuesToModels(issueEntities);
    }

    public Issue getOne(String id)
            throws NotFoundException {

        Optional<IssueEntity> entity = issueRepository.getItemById(id);

        if (entity.isEmpty()) {
            throw new NotFoundException("The issue cannot be found");
        }

        return issueToModel(entity.get());
    }

    public Issue create(String title, String author, LocalDate createdAt, String modelId, Map<String, String> fields) throws InsertException, IllegalArgumentException {
        IssueEntity entity = new IssueEntity();

        Optional<IssueModelEntity> optionalIssueModel = issueModelRepository.getItemById(modelId);

        if (optionalIssueModel.isEmpty()) {
            throw new IllegalArgumentException("The model with id '" + modelId + "' does not exist");
        }

        IssueModelEntity issueModelEntity = optionalIssueModel.get();

        // create the fields that will be stored
        List<IssueFieldEntity> issueFieldEntities = new ArrayList<>();
        for (IssueModelFieldEntity field : issueModelEntity.getFields()) {
            String value = fields.get(field.getTitle());
            if (value == null) {
                throw new IllegalArgumentException("All the field values does not correspond to the issue model");
            }

            IssueFieldEntity fieldEntity = new IssueFieldEntity();
            fieldEntity.setTitle(field.getTitle());
            fieldEntity.setDescription(field.getDescription());
            fieldEntity.setRequired(field.isRequired());
            fieldEntity.setValue(value);
            issueFieldEntities.add(fieldEntity);
        }

        entity.setTitle(title);
        entity.setAuthor(author);
        entity.setCreatedAt(createdAt);
        entity.setModel(issueModelEntity);
        entity.setStatus(IssueStatusEntity.OPEN);
        entity.setFields(issueFieldEntities);

        Optional<IssueEntity> result = issueRepository.addItem(entity);

        if (result.isEmpty()) {
            throw new InsertException("An error occured while inserting the issue");
        }

        return issueToModel(result.get());
    }

    public void delete(String id)
            throws NotFoundException {

        boolean result = issueRepository.deleteItem(id);

        if (!result) {
            throw new NotFoundException("The issue was not found");
        }
    }
}
