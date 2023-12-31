package fr.iut.uca.v1.repository.mongo.surveys;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import fr.iut.uca.v1.repository.mongo.DatabaseClient;
import fr.iut.uca.v1.entity.surveys.FeedbackEntity;
import fr.iut.uca.v1.entity.surveys.SurveyEntity;
import fr.iut.uca.v1.extension.surveys.FeedbackExtensions;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.v1.repository.mongo.GenericRepository;
import fr.iut.uca.v1.repository.surveys.ISurveyRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.v1.extension.surveys.SurveyExtensions.CREATED_AT;
import static fr.iut.uca.v1.extension.surveys.SurveyExtensions.PUBLISHED_AT;
import static fr.iut.uca.v1.extension.surveys.SurveyExtensions.END_AT;
import static fr.iut.uca.v1.extension.surveys.SurveyExtensions.ID;
/**
 * Repository for surveys.
 */
@ApplicationScoped
@RepositoryQualifier(RepositoryType.MONGO)
public class SurveyRepository extends GenericRepository<SurveyEntity> implements ISurveyRepository {

    /**
     * Constructor.
     */
    public SurveyRepository() {
        super(ID);
    }
    
    @Override
    @Inject
    public void setMongoClient(DatabaseClient mongoClient) {
        this.mongoClient = mongoClient;
        this.collection = mongoClient.getCollection(DatabaseClient.CollectionName.SURVEYS, SurveyEntity.class);
    }

    @Override
    public List<SurveyEntity> getSurveysCreatedAt(LocalDate createdAt, int index, int count) {
        var filter = Filters.eq(CREATED_AT, createdAt);
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<SurveyEntity> getSurveysCreatedBefore(LocalDate createdAt, int index, int count) {
        var filter = Filters.lt(CREATED_AT, createdAt);
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<SurveyEntity> getSurveysCreatedAfter(LocalDate createdAt, int index, int count) {
        var filter = Filters.gt(CREATED_AT, createdAt);
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<SurveyEntity> getSurveysCreatedBetween(LocalDate start, LocalDate end, int index, int count) {
        var filter = Filters.and(Filters.gte(CREATED_AT, start), Filters.lte(CREATED_AT, end));
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<SurveyEntity> getSurveysPublishedAt(LocalDate publishedAt, int index, int count) {
        var filter = Filters.eq(PUBLISHED_AT, publishedAt);
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<SurveyEntity> getSurveysPublishedBefore(LocalDate publishedAt, int index, int count) {
        var filter = Filters.lt(PUBLISHED_AT, publishedAt);
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<SurveyEntity> getSurveysPublishedAfter(LocalDate publishedAt, int index, int count) {
        var filter = Filters.gt(PUBLISHED_AT, publishedAt);
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<SurveyEntity> getSurveysPublishedBetween(LocalDate start, LocalDate end, int index, int count) {
        var filter = Filters.and(Filters.gte(PUBLISHED_AT, start), Filters.lte(PUBLISHED_AT, end));
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<SurveyEntity> getSurveysEndAt(LocalDate expiredAt, int index, int count) {
        var filter = Filters.eq(END_AT, expiredAt);
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<SurveyEntity> getSurveysEndBefore(LocalDate expiredAt, int index, int count) {
        var filter = Filters.lt(END_AT, expiredAt);
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }
    @Override
    public List<SurveyEntity> getSurveysEndAfter(LocalDate expiredAt, int index, int count) {
        var filter = Filters.gt(END_AT, expiredAt);
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<SurveyEntity> getSurveysEndBetween(LocalDate start, LocalDate end, int index, int count) {
        var filter = Filters.and(Filters.gte(END_AT, start), Filters.lte(END_AT, end));
        return collection.find(SurveyEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    /**
     * Delete a survey and all its feedbacks
     * @param id Id of the survey to delete
     * @return true if all the feedbacks and the survey have been deleted, false otherwise
     */
    @Override
    public boolean deleteItem(String id) {
        MongoCollection<FeedbackEntity> feedbacksCollection = mongoClient.getCollection(DatabaseClient.CollectionName.FEEDBACKS, FeedbackEntity.class);

        var feedbacksCount = feedbacksCollection.countDocuments(new Document(FeedbackExtensions.SURVEY_ID, new ObjectId(id)));
        DeleteResult feedbacksResult = mongoClient.getCollection(DatabaseClient.CollectionName.FEEDBACKS, Document.class)
                .deleteMany(new Document("surveyId", new ObjectId(id)));

        if (feedbacksResult.getDeletedCount() != feedbacksCount) {
            return false;
        }

        DeleteResult result = collection.deleteOne(new Document(ID, new ObjectId(id)));

        return result.getDeletedCount() > 0;
    }

}
