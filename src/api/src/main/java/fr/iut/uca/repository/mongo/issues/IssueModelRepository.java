package fr.iut.uca.repository.mongo.issues;

import com.mongodb.client.model.Filters;
import fr.iut.uca.entity.issues.CategoryEntity;
import fr.iut.uca.repository.mongo.DatabaseClient;
import fr.iut.uca.entity.issues.IssueModelEntity;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.repository.issues.IIssueModelRepository;
import fr.iut.uca.repository.mongo.GenericRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.extension.issues.IssueModelExtensions.*;

/**
 * Repository for issue models.
 */
@ApplicationScoped
@RepositoryQualifier(RepositoryType.MONGO)
public class IssueModelRepository extends GenericRepository<IssueModelEntity> implements IIssueModelRepository {

    /**
     * Constructor.
     */
    IssueModelRepository() {
        super(ID);
    }

    @Override
    @Inject
    public void setMongoClient(DatabaseClient mongoClient) {
        this.mongoClient = mongoClient;
        this.collection = mongoClient.getCollection(DatabaseClient.CollectionName.ISSUE_MODELS, IssueModelEntity.class);
    }

    @Override
    public List<IssueModelEntity> getIssueModelsByNameContaining(String nameFilter, int index, int count) {
        var filter = Filters.regex(NAME, nameFilter);
        return collection.find(IssueModelEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<CategoryEntity> getCategories() {
        return collection.distinct(CATEGORY, CategoryEntity.class).into(new ArrayList<>());
    }
}
