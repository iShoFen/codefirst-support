package fr.iut.uca.repository.mongo.surveys;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.entity.surveys.SurveyEntity;
import fr.iut.uca.extension.surveys.FeedbackExtensions;
import fr.iut.uca.extension.surveys.SurveyExtensions;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.repository.mongo.GenericRepository;
import fr.iut.uca.repository.surveys.IFeedbackRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.conversions.Bson;

import java.util.Arrays;

import static fr.iut.uca.extension.surveys.FeedbackExtensions.ID;
import static fr.iut.uca.extension.surveys.FeedbackExtensions.SURVEY_ID;

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

    @Override
    public SurveyEntity getSurveyWithFeedbacks(String feedbackId) {
        Bson matchStage = Aggregates.match(Filters.eq(ID, feedbackId));
        Bson lookupStage = Aggregates.lookup(DatabaseClient.CollectionName.SURVEYS.name(), SURVEY_ID, SurveyExtensions.ID, "survey");

        return collection.aggregate(Arrays.asList(matchStage, lookupStage), SurveyEntity.class).first();
    }
}
