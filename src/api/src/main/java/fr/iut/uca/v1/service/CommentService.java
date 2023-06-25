package fr.iut.uca.v1.service;

import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.v1.dto.issues.CommentDTO;
import fr.iut.uca.v1.entity.issues.IssueEntity;
import fr.iut.uca.v1.extension.DateExtensions;
import fr.iut.uca.v1.extension.issues.CommentExtensions;
import fr.iut.uca.v1.extension.issues.IssueExtensions;
import fr.iut.uca.v1.repository.issues.IIssueRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.Date;
import java.util.Optional;

/**
 * Comment service
 */
@ApplicationScoped
public class CommentService {

    /**
     * Issue repository
     */
    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    IIssueRepository issueRepository;

    /**
     * Create a comment
     * @param issueId Issue id
     * @param commentDTO Comment DTO
     * @return Comment DTO
     * @throws NotFoundException if issue not found
     * @throws IllegalArgumentException if comment is not valid
     * @throws UpdateException if an error occurred while updating the issue
     */
    public CommentDTO create(String issueId, CommentDTO commentDTO) throws NotFoundException, IllegalArgumentException, UpdateException {
        Optional<IssueEntity> optionalIssueEntity = issueRepository.getItemById(issueId);

        if (optionalIssueEntity.isEmpty()) {
            throw new NotFoundException(String.format("Issue with id %s not found", issueId));
        }

        var issue = IssueExtensions.entityToModel(optionalIssueEntity.get());
        issue.addComment(CommentExtensions.dtoToModel(commentDTO));

        Optional<IssueEntity> optionalResult = issueRepository.updateItem(issue.getId(), IssueExtensions.modelToEntity(issue));

        if (optionalResult.isEmpty()) {
            throw new UpdateException("An error occurred while updating the issue");
        }
        var result = IssueExtensions.entityToModel(optionalResult.get());

        var comment =  result.getComments().get(result.getComments().size() - 1);
        return CommentExtensions.modelToDTO(comment);
    }

    /**
     * Delete a comment
     * @param issueId Issue id
     * @param author Author
     * @param createdAt Created at
     * @throws NotFoundException if issue or comment not found
     * @throws UpdateException if an error occurred while updating the issue
     */
    public void delete(String issueId, String author, Date createdAt) throws NotFoundException, UpdateException {
        Optional<IssueEntity> optionalIssueEntity = issueRepository.getItemById(issueId);

        if (optionalIssueEntity.isEmpty()) {
            throw new NotFoundException(String.format("Issue with id %s not found", issueId));
        }

        var issue = IssueExtensions.entityToModel(optionalIssueEntity.get());
        var comment = issue.getComments().stream()
                .filter(c -> c.getAuthor().equals(author) && c.getCreatedAt().equals(DateExtensions.toLocalDate(createdAt)))
                .findFirst();

        if (comment.isEmpty()) {
            throw new NotFoundException(String.format("Comment with author %s and createdAt %s not found", author, createdAt));
        }

        issue.deleteComment(comment.get());

        Optional<IssueEntity> optionalResult = issueRepository.updateItem(issue.getId(), IssueExtensions.modelToEntity(issue));

        if (optionalResult.isEmpty()) {
            throw new UpdateException("An error occurred while updating the issue");
        }
    }
}
