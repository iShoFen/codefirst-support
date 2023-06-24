package fr.iut.uca.v1.repository.issues;

import fr.iut.uca.v1.entity.issues.IssueEntity;
import fr.iut.uca.v1.entity.issues.IssueWithStatusEntity;
import fr.iut.uca.v1.repository.IGenericRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for issue repository.
 * @see IGenericRepository
 */
public interface IIssueRepository extends IGenericRepository<IssueEntity> {

    /**
     * Get a list of pair of issue status and count of issues by status
     * @return  List of a pair of issue status and count of issues by status
     */
    IssueWithStatusEntity getIssuesCountByStatus();

    /**
     * Get a list of issue models by category
     * @param category Category
     * @param index Index of the offset
     * @param count Number of items to get
     * @return List of issue models matching the category filter
     */
    List<IssueEntity> getItemsByStatus(String category, int index, int count);

    /**
     * Get a list of issue models by created date
     * @param createdAt Created date
     * @param status Status
     * @param index Index of the offset
     * @param count Number of items to get
     * @return List of issue models matching the created date filter
     */
    List<IssueEntity> getIssuesByCreatedDate(LocalDate createdAt, String status, int index, int count);

    /**
     * Get a list of issue models by created date before
     * @param createdAt Created date
     * @param status Status
     * @param index Index of the offset
     * @param count Number of items to get
     * @return List of issue models matching the created date filter
     */
    List<IssueEntity> getIssuesByCreatedDateBefore(LocalDate createdAt, String status, int index, int count);

    /**
     *  Get a list of issue models by created date after
     * @param createdAt Created date
     * @param status Status
     * @param index Index of the offset
     * @param count Number of items to get
     * @return List of issue models matching the created date filter
     */
    List<IssueEntity> getIssuesByCreatedDateAfter(LocalDate createdAt, String status, int index, int count);

    /**
     *  Get a list of issue models by created date between
     * @param start Start date
     * @param status Status
     * @param end End date
     * @param index Index of the offset
     * @param count Number of items to get
     * @return List of issue models matching the created date filter
     */
    List<IssueEntity> getIssuesByCreatedDateBetween(LocalDate start, String status, LocalDate end, int index, int count);

    /**
     * Get a list of issue models by title
     * @param title Title
     * @param status Status
     * @param index Index of the offset
     * @param count Number of items to get
     * @return List of issue models matching the title filter
     */
    List<IssueEntity> getIssuesWithTitleContaining(String title, String status, int index, int count);

    /**
     * Get a list of issue models by author
      * @param author Author
     * @param status Status
     * @param index Index of the offset
     * @param count Number of items to get
     * @return List of issue models matching the author filter
     */
    List<IssueEntity> getIssuesOfAuthor(String author, String status, int index, int count);
}
