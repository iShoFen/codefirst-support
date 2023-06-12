package fr.iut.uca.repository.mongo.issues;

import com.mongodb.client.model.Filters;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.entity.issues.IssueModelEntity;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.repository.issues.IIssueModelRepository;
import fr.iut.uca.repository.mongo.GenericRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.extension.issues.IssueModelExtensions.ID;
import static fr.iut.uca.extension.issues.IssueModelExtensions.NAME;

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
