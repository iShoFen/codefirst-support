package fr.iut.uca.v1.repository.mongo.issues;

import com.mongodb.client.model.Filters;
import fr.iut.uca.v1.repository.mongo.DatabaseClient;
import fr.iut.uca.v1.entity.issues.IssueModelEntity;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.v1.repository.issues.IIssueModelRepository;
import fr.iut.uca.v1.repository.mongo.GenericRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.v1.extension.issues.IssueModelExtensions.*;

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
}
