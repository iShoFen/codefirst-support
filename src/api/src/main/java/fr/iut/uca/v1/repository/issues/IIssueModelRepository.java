package fr.iut.uca.v1.repository.issues;

import fr.iut.uca.v1.entity.issues.CategoryEntity;
import fr.iut.uca.v1.entity.issues.IssueModelEntity;
import fr.iut.uca.v1.repository.IGenericRepository;

import java.util.List;

/**
 * Interface for issue model repository.
 * @see IGenericRepository
 */
public interface IIssueModelRepository extends IGenericRepository<IssueModelEntity> {

    /**
     * Get a list of issue models by name
     * @param nameFilter Name filter
     * @param index Index of the offset
     * @param count Number of items to get
     * @return List of issue models matching the name filter
     */
    List<IssueModelEntity> getIssueModelsByNameContaining(String nameFilter, int index, int count);

    /**
     * Get a list of all the categories
     * @return List of all the categories
     */
    List<CategoryEntity> getCategories();
}


