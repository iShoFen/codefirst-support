package fr.iut.uca.v1.extension.issues;

import fr.iut.uca.v1.dto.issues.issue.IssueDTO;
import fr.iut.uca.v1.dto.issues.issue.IssueDetailDTO;
import fr.iut.uca.v1.entity.issues.IssueEntity;
import fr.iut.uca.v1.model.issues.Issue;

import java.util.List;

public abstract class IssueExtensions {
    public static final String ID = "_id";
    public static final String VERSION = "version";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String CREATED_AT = "created_at";
    public static final String STATUS = "status";
    public static final String MODEL = "model";
    public static final String COMMENTS = "comments";
    public static final String FIELDS = "fields";

    private IssueExtensions() {
    }

    public static IssueEntity toEntity(Issue issue) {
        var entity = new IssueEntity();

        entity.setId(issue.getId());
        entity.setTitle(issue.getTitle());
        entity.setAuthor(issue.getAuthor());
        entity.setCreatedAt(issue.getCreatedAt());
        entity.setStatus(IssueStatusExtensions.modelToEntity(issue.getStatus()));
        entity.setModel(IssueModelInfoExtensions.issueModelInfoToEntity(issue.getModel()));
        entity.setComments(CommentExtensions.toEntities(issue.getComments()));
        entity.setFields(IssueFieldExtensions.toEntities(issue.getFields()));

        return entity;
    }

    public static List<IssueEntity> toEntities(List<Issue> issues) {
        return issues.stream().map(IssueExtensions::toEntity).toList();
    }

    public static Issue issueToModel(IssueEntity entity) {
        return new Issue(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getCreatedAt(),
                IssueStatusExtensions.entityToModel(entity.getStatus()),
                IssueModelInfoExtensions.issueModelInfoEntityToModel(entity.getModel()),
                CategoryExtensions.toModel(entity.getCategory()),
                IssueFieldExtensions.issueFieldEntitiesToModels(entity.getFields()),
                CommentExtensions.toModels(entity.getComments())
        );
    }

    public static List<Issue> issuesToModels(List<IssueEntity> entities) {
        return entities.stream().map(IssueExtensions::issueToModel).toList();
    }

    public static IssueDTO issueToDTO(Issue issue) {
        return new IssueDTO(
                issue.getId(),
                issue.getTitle(),
                issue.getAuthor(),
                issue.getCreatedAt(),
                CategoryExtensions.categoryToDTO(issue.getCategory()),
                IssueStatusExtensions.modelToDTO(issue.getStatus())
        );
    }

    public static List<IssueDTO> issuesToDTOs(List<Issue> issues) {
        return issues.stream().map(IssueExtensions::issueToDTO).toList();
    }

    public static IssueDetailDTO issueToDetailDTO(Issue issue) {
        return new IssueDetailDTO(
                issue.getId(),
                issue.getTitle(),
                issue.getAuthor(),
                issue.getCreatedAt(),
                CategoryExtensions.categoryToDTO(issue.getCategory()),
                IssueStatusExtensions.modelToDTO(issue.getStatus()),
                IssueModelInfoExtensions.issueModelInfoToDTO(issue.getModel()),
                IssueFieldExtensions.issueFieldsToDTOs(issue.getFields()),
                CommentExtensions.commentsToDTOs(issue.getComments())
        );
    }

    public static List<IssueDetailDTO> issuesToDetailDTOs(List<Issue> issues) {
        return issues.stream().map(IssueExtensions::issueToDetailDTO).toList();
    }
}
