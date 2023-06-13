package fr.iut.uca.extension.issues;

import fr.iut.uca.entity.issues.CommentEntity;
import fr.iut.uca.model.issues.Comment;

import java.util.List;

public abstract class CommentExtensions {
    public static final String CREATED_AT = "created_at";
    public static final String AUTHOR = "author";
    public static final String CONTENT = "content";

    private CommentExtensions() { }

    public static CommentEntity toEntity(Comment comment) {
        var commentEntity = new CommentEntity();

        commentEntity.setAuthor(comment.getAuthor());
        commentEntity.setContent(comment.getContent());
        commentEntity.setCreatedAt(comment.getCreatedAt());

        return commentEntity;
    }

    public static List<CommentEntity> toEntities(List<Comment> comments) {
        return comments.stream().map(CommentExtensions::toEntity).toList();
    }

    public static Comment toModel(CommentEntity commentEntity) {
        return new Comment(commentEntity.getCreatedAt(), commentEntity.getAuthor(), commentEntity.getContent());
    }

    public static List<Comment> toModels(List<CommentEntity> commentEntities) {
        return commentEntities.stream().map(CommentExtensions::toModel).toList();
    }
}
