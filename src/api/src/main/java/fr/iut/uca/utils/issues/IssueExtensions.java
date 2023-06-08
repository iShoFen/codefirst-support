package fr.iut.uca.utils.issues;

import fr.iut.uca.entity.issues.IssueEntity;
import fr.iut.uca.model.issues.Issue;
import org.bson.types.ObjectId;

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

    public static IssueEntity toEntity(Issue issue) {
        var entity = new IssueEntity();

        if (!issue.getId().isBlank()) {
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

}
