package fr.iut.uca.repository.issues;

import fr.iut.uca.entity.issues.IssueModelEntity;
import fr.iut.uca.repository.IGenericRepository;

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
}


