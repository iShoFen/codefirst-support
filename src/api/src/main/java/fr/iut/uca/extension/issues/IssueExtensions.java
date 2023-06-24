package fr.iut.uca.extension.issues;

import fr.iut.uca.dto.issues.CommentDTO;
import fr.iut.uca.dto.issues.IssueFieldDTO;
import fr.iut.uca.dto.issues.issue.IssueDTO;
import fr.iut.uca.dto.issues.issue.IssueDetailDTO;
import fr.iut.uca.dto.issues.issuemodel.IssueModelInfoDTO;
import fr.iut.uca.entity.issues.IssueEntity;
import fr.iut.uca.model.issues.Issue;

import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.extension.issues.CategoryExtensions.categoryToDTO;
import static fr.iut.uca.extension.issues.CommentExtensions.commentsToDTOs;
import static fr.iut.uca.extension.issues.IssueFieldExtensions.issueFieldsToDTOs;
import static fr.iut.uca.extension.issues.IssueStatusExtensions.statusToDTO;

public abstract class IssueExtensions {
    public static final String ID = "_id";
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
        entity.setStatus(IssueStatusExtensions.toEntity(issue.getStatus()));
        entity.setModel(IssueModelExtensions.toEntity(issue.getModel()));
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
                IssueStatusExtensions.toModel(entity.getStatus()),
                IssueModelExtensions.issueModelEntityToModel(entity.getModel()),
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
                categoryToDTO(issue.getModel().getCategory()),
                statusToDTO(issue.getStatus())
        );
    }

    public static IssueDetailDTO issueToDetailDTO(Issue issue) {
        IssueModelInfoDTO modelDTO = IssueModelExtensions.issueModelToShortDTO(issue.getModel());
        List<IssueFieldDTO> fieldDTOS = issueFieldsToDTOs(issue.getFields());
        List<CommentDTO> commentDTOS = commentsToDTOs(issue.getComments());

        return new IssueDetailDTO(
                issue.getId(),
                issue.getTitle(),
                issue.getAuthor(),
                issue.getCreatedAt(),
                categoryToDTO(issue.getModel().getCategory()),
                statusToDTO(issue.getStatus()),
                modelDTO,
                fieldDTOS,
                commentDTOS
        );
    }

    public static List<IssueDTO> issuesToDTOs(List<Issue> issues) {
        List<IssueDTO> dtos = new ArrayList<>();
        issues.forEach(issue -> dtos.add(issueToDTO(issue)));
        return dtos;
    }
}
