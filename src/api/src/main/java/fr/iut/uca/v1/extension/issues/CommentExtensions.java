package fr.iut.uca.v1.extension.issues;

import fr.iut.uca.v1.dto.issues.CommentDTO;
import fr.iut.uca.v1.entity.issues.CommentEntity;
import fr.iut.uca.v1.model.issues.Comment;

import java.util.List;

public abstract class CommentExtensions {
    public static final String CREATED_AT = "created_at";
    public static final String AUTHOR = "author";
    public static final String CONTENT = "content";

    private CommentExtensions() {
    }

    public static CommentEntity modelToEntity(Comment comment) {
        var commentEntity = new CommentEntity();

        commentEntity.setAuthor(comment.getAuthor());
        commentEntity.setContent(comment.getContent());
        commentEntity.setCreatedAt(comment.getCreatedAt());

        return commentEntity;
    }

    public static List<CommentEntity> modelsToEntities(List<Comment> comments) {
        return comments.stream().map(CommentExtensions::modelToEntity).toList();
    }

    public static Comment entityToModel(CommentEntity commentEntity) {
        return new Comment(commentEntity.getCreatedAt(), commentEntity.getAuthor(), commentEntity.getContent());
    }

    public static List<Comment> entitiesToModels(List<CommentEntity> commentEntities) {
        return commentEntities.stream().map(CommentExtensions::entityToModel).toList();
    }

    public static CommentDTO modelToDTO(Comment comment) {
        return new CommentDTO(
                comment.getCreatedAt(),
                comment.getAuthor(),
                comment.getContent()
        );
    }

    public static List<CommentDTO> modelsToDTOs(List<Comment> comments) {
        return comments.stream().map(CommentExtensions::modelToDTO).toList();
    }

    public static Comment dtoToModel(CommentDTO commentDTO) {
        return new Comment(commentDTO.createdAt(), commentDTO.author(), commentDTO.content());
    }

    public static List<Comment> dtosToModels(List<CommentDTO> commentDTOs) {
        return commentDTOs.stream().map(CommentExtensions::dtoToModel).toList();
    }
}
