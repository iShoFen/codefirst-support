package fr.iut.uca.repository.mongo.surveys;

import fr.iut.uca.DatabaseClient;
import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.repository.mongo.GenericRepository;
import fr.iut.uca.repository.surveys.IFeedbackRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import static fr.iut.uca.extension.surveys.FeedbackExtensions.ID;

/**
 * Repository for feedbacks.
 */
@ApplicationScoped
@RepositoryQualifier(RepositoryType.MONGO)
public class FeedbackRepository extends GenericRepository<FeedbackEntity> implements IFeedbackRepository {

    /**
     * Constructor.
     */
    public FeedbackRepository() {
        super(ID);
    }

    @Override
    @Inject
    public void setMongoClient(DatabaseClient mongoClient) {
        this.mongoClient = mongoClient;
        this.collection = mongoClient.getCollection(DatabaseClient.CollectionName.FEEDBACKS, FeedbackEntity.class);
    }
}
