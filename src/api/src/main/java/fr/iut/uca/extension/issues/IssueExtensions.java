package fr.iut.uca.extension.issues;

import fr.iut.uca.entity.issues.IssueEntity;
import fr.iut.uca.model.issues.Issue;
import org.bson.types.ObjectId;

import java.util.List;

public abstract class IssueExtensions {
    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String CREATED_AT = "created_at";
    public static final String STATUS = "status";
    public static final String CATEGORY = "category";
    public static final String MODEL = "model";
    public static final String COMMENTS = "comments";
    public static final String FIELDS = "fields";

    private IssueExtensions() { }

    public static IssueEntity toEntity(Issue issue) {
        var entity = new IssueEntity();

        if (issue.getId() != null) {
            entity.setId(new ObjectId(issue.getId()));
        }

        entity.setTitle(issue.getTitle());
        entity.setAuthor(issue.getAuthor());
        entity.setCreatedAt(issue.getCreatedAt());
        entity.setStatus(IssueStatusExtensions.toEntity(issue.getStatus()));
        entity.setCategory(CategoryExtensions.toEntity(issue.getCategory()));
        entity.setModel(IssueModelInfoExtensions.toEntity(issue.getModel()));
        entity.setComments(CommentExtensions.toEntities(issue.getComments()));
        entity.setFields(IssueFieldExtensions.toEntities(issue.getFields()));

        return entity;
    }

    public static List<IssueEntity> toEntities(List<Issue> issues) {
        return issues.stream().map(IssueExtensions::toEntity).toList();
    }

    public static Issue toModel(IssueEntity entity) {
        return new Issue(
                entity.getId().toString(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getCreatedAt(),
                IssueStatusExtensions.toModel(entity.getStatus()),
                CategoryExtensions.toModel(entity.getCategory()),
                IssueModelInfoExtensions.toModel(entity.getModel()),
                IssueFieldExtensions.toModels(entity.getFields()),
                CommentExtensions.toModels(entity.getComments())
        );
    }

    public static List<Issue> toModels(List<IssueEntity> entities) {
        return entities.stream().map(IssueExtensions::toModel).toList();
    }

}
