package fr.iut.uca.v1.service;

import fr.iut.uca.exception.InsertException;
import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.v1.dto.OperatorDTO;
import fr.iut.uca.v1.dto.issues.IssueStatusDTO;
import fr.iut.uca.v1.dto.issues.IssueWithStatusDTO;
import fr.iut.uca.v1.dto.issues.issue.*;
import fr.iut.uca.v1.entity.issues.*;
import fr.iut.uca.v1.extension.DateExtensions;
import fr.iut.uca.v1.extension.issues.IssueExtensions;
import fr.iut.uca.v1.extension.issues.IssueModelExtensions;
import fr.iut.uca.v1.extension.issues.IssueStatusExtensions;
import fr.iut.uca.v1.model.IssueWithStatus;
import fr.iut.uca.v1.model.issues.*;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.v1.repository.issues.IIssueModelRepository;
import fr.iut.uca.v1.repository.issues.IIssueRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.*;

import static fr.iut.uca.v1.extension.issues.IssueExtensions.entityToModel;
import static fr.iut.uca.v1.extension.issues.IssueExtensions.entitiesToModels;

@ApplicationScoped
public class IssueService {

    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    IIssueRepository issueRepository;

    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    IIssueModelRepository issueModelRepository;


    public List<IssueDTO> getAll(IssueGetDTO getIssueDTO) throws IllegalArgumentException {

        List<IssueEntity> issueEntities;
        IssueStatus statusModel = null;
        var status = getIssueDTO.getStatus();
        if (status != null) {
            statusModel = IssueStatusExtensions.dtoToModel(status);
        }

        var index = getIssueDTO.getIndex();
        var count = getIssueDTO.getCount();
        var createdAt = getIssueDTO.getCreatedAt();
        if (statusModel != null && createdAt == null) {
            issueEntities = issueRepository.getItemsByStatus(IssueStatusExtensions.modelToEntity(statusModel), index, count);
        } else if (createdAt != null) {
            issueEntities = getCreatedAt(index, count, status, createdAt, getIssueDTO.getEndDate(), getIssueDTO.getOperator(), statusModel);
        }
        else {
            issueEntities = issueRepository.getItems(index, count);
        }

        var issues = entitiesToModels(issueEntities);
        return IssueExtensions.modelsToDTOs(issues);
    }

    private List<IssueEntity> getCreatedAt(int index, int count, IssueStatusDTO status, Date createdAt, Date endDate, OperatorDTO operator, IssueStatus statusModel) {
        List<IssueEntity> issueEntities;
        if (status == null) throw new IllegalArgumentException("The status is required when the created_at parameter is used");
        if (operator == null) throw new IllegalArgumentException("The operator is required when the created_at parameter is used");

        if (endDate != null) {
            issueEntities = issueRepository.getIssuesByCreatedDateBetween(DateExtensions.toLocalDate(createdAt), DateExtensions.toLocalDate(endDate), IssueStatusExtensions.modelToEntity(statusModel), index, count);
        } else {
            switch (operator) {
                case BEFORE -> issueEntities = issueRepository.getIssuesByCreatedDateBefore(DateExtensions.toLocalDate(createdAt), IssueStatusExtensions.modelToEntity(statusModel), index, count);
                case AFTER -> issueEntities = issueRepository.getIssuesByCreatedDateAfter(DateExtensions.toLocalDate(createdAt), IssueStatusExtensions.modelToEntity(statusModel), index, count);
                default -> issueEntities = issueRepository.getIssuesByCreatedDate(DateExtensions.toLocalDate(createdAt), IssueStatusExtensions.modelToEntity(statusModel), index, count);
            }
        }
        return issueEntities;
    }

    public IssueDetailDTO getOne(String id)
            throws NotFoundException {

        Optional<IssueEntity> entity = issueRepository.getItemById(id);

        if (entity.isEmpty()) {
            throw new NotFoundException("The issue cannot be found");
        }

        var issue = entityToModel(entity.get());
        return IssueExtensions.modelToDetailDTO(issue);
    }

    private static List<IssueField> getIssueFields(List<? extends IssueModelField> modelFields, Map<String, String> fields, String errorMessage) throws IllegalArgumentException {
        List<IssueField> issueFields = new ArrayList<>();
        for (IssueModelField field : modelFields) {
            String value = fields.get(field.getTitle());
            if (value == null) {
                throw new IllegalArgumentException(errorMessage);
            }

            var issueField = new IssueField(field.getTitle(), field.getDescription(), field.isRequired(), value);
            issueFields.add(issueField);
        }
        return issueFields;
    }

    public IssueDetailDTO create(IssueInsertDTO issueInsertDTO) throws InsertException, IllegalArgumentException {
        Optional<IssueModelEntity> optionalIssueModel = issueModelRepository.getItemById(issueInsertDTO.modelId());

        if (optionalIssueModel.isEmpty()) {
            throw new IllegalArgumentException("The model with id '" + issueInsertDTO.modelId() + " does not exist");
        }

        IssueModel issueModel = IssueModelExtensions.entityToModel(optionalIssueModel.get());

        // map the fields received to map
        Map<String, String> fields = new HashMap<>();
        for (IssueFieldInsertDTO field : issueInsertDTO.fields()) {
            fields.put(field.title(), field.value());
        }

        List<IssueField> issueFields = getIssueFields(issueModel.getFields(), fields, "All the field values does not correspond to the issue model");

        var issue = new Issue(
                issueInsertDTO.title(),
                issueInsertDTO.author(),
                issueInsertDTO.createdAt(),
                issueModel,
                issueModel.getCategory(),
                issueFields);

        Optional<IssueEntity> result = issueRepository.addItem(IssueExtensions.modelToEntity(issue));

        if (result.isEmpty()) {
            throw new InsertException("An error occurred while inserting the issue");
        }

        var issueResult = IssueExtensions.entityToModel(result.get());
        return IssueExtensions.modelToDetailDTO(issueResult);
    }

    public IssueDetailDTO update(String id, IssueUpdateDTO issueUpdateDTO) throws NotFoundException, UpdateException {
        Optional<IssueEntity> optionalIssue = issueRepository.getItemById(id);

        if (optionalIssue.isEmpty()) {
            throw new NotFoundException("The issue with id '" + id + "' does not exist");
        }

        Issue issue = IssueExtensions.entityToModel(optionalIssue.get());

        // map the fields received to map
        Map<String, String> fields = new HashMap<>();
        for (IssueFieldInsertDTO field : issueUpdateDTO.fields()) {
            fields.put(field.title(), field.value());
        }

        var errorMessage = "All the field values does not correspond to the issue ! All fields must be updated and posses the same title";
        List<IssueField> issueFields = getIssueFields(issue.getFields(), fields, errorMessage);

        issue.setTitle(issueUpdateDTO.title());
        issueFields.forEach(issue::updateField);

        Optional<IssueEntity> result = issueRepository.updateItem(IssueExtensions.modelToEntity(issue));

        if (result.isEmpty()) {
            throw new UpdateException("An error occurred while updating the issue");
        }

        var issueResult = IssueExtensions.entityToModel(result.get());
        return IssueExtensions.modelToDetailDTO(issueResult);
    }

    public IssueDetailDTO updateStatus(String id) throws NotFoundException, UpdateException {
        Optional<IssueEntity> optionalIssue = issueRepository.getItemById(id);

        if (optionalIssue.isEmpty()) {
            throw new NotFoundException("The issue with id '" + id + "' does not exist");
        }

        Issue issue = IssueExtensions.entityToModel(optionalIssue.get());

        if (issue.getStatus() == IssueStatus.CLOSED) {
            issue.setStatus(IssueStatus.OPEN);
        } else {
            issue.setStatus(IssueStatus.CLOSED);
        }

        Optional<IssueEntity> result = issueRepository.updateItem(IssueExtensions.modelToEntity(issue));

        if (result.isEmpty()) {
            throw new UpdateException("An error occurred while updating the issue");
        }

        var issueResult = IssueExtensions.entityToModel(result.get());
        return IssueExtensions.modelToDetailDTO(issueResult);
    }

    public void delete(String id)
            throws NotFoundException {

        boolean result = issueRepository.deleteItem(id);

        if (!result) {
            throw new NotFoundException("The issue " + id + " cannot be found");
        }
    }

    public IssueWithStatusDTO getIssueWithStatus() {
        var issueWithStatus = new IssueWithStatus(issueRepository.getIssuesCountByStatus().getIssuesCountByStatus());

        return new IssueWithStatusDTO(issueWithStatus.getIssuesCountByStatus());
    }
}
