package fr.iut.uca.repository.surveys;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.lang.Nullable;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.entity.surveys.SurveyEntity;
import fr.iut.uca.model.surveys.Survey;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.utils.surveys.FeedbackExtensions.SURVEY_ID;

import static fr.iut.uca.utils.surveys.SurveyExtensions.ID;
import static fr.iut.uca.utils.surveys.SurveyExtensions.CREATED_AT;
import static fr.iut.uca.utils.surveys.SurveyExtensions.PUBLISHED_AT;
import static fr.iut.uca.utils.surveys.SurveyExtensions.END_AT;
import static fr.iut.uca.utils.surveys.SurveyExtensions.toEntity;

public class SurveyRepository {
    @Inject
    DatabaseClient mongoClient;

    private MongoCollection<SurveyEntity> getCollection() {
        return mongoClient.getCollection(DatabaseClient.CollectionName.SURVEYS, SurveyEntity.class);
    }
    
    public long getSurveysCont() {
        return getCollection().countDocuments();
    }
    public List<SurveyEntity> getSurveys(int index, int count) {
        return getCollection().find(SurveyEntity.class).skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysCreatedAt(LocalDate createdAt, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(CREATED_AT, createdAt))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysCreatedBefore(LocalDate createdAt, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(CREATED_AT, new Document("$lt", createdAt)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysCreatedAfter(LocalDate createdAt, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(CREATED_AT, new Document("$gt", createdAt)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysCreatedBetween(LocalDate start, LocalDate end, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(CREATED_AT, new Document("$gt", start).append("$lt", end)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysPublishedAt(LocalDate publishedAt, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(PUBLISHED_AT, publishedAt))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysPublishedBefore(LocalDate publishedAt, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(PUBLISHED_AT, new Document("$lt", publishedAt)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysPublishedAfter(LocalDate publishedAt, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(PUBLISHED_AT, new Document("$gt", publishedAt)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysPublishedBetween(LocalDate start, LocalDate end, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(PUBLISHED_AT, new Document("$gt", start).append("$lt", end)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysEndAt(LocalDate expiredAt, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(END_AT, expiredAt))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysEndBefore(LocalDate expiredAt, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(END_AT, new Document("$lt", expiredAt)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysEndAfter(LocalDate expiredAt, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(END_AT, new Document("$gt", expiredAt)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<SurveyEntity> getSurveysEndBetween(LocalDate start, LocalDate end, int index, int count) {
        return getCollection().find(SurveyEntity.class).filter(new Document(END_AT, new Document("$gt", start).append("$lt", end)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    @Nullable
    public SurveyEntity getSurveyById(String id) {
        return getCollection().find(SurveyEntity.class).filter(new Document(ID, new ObjectId(id))).first();
    }

    public List<FeedbackEntity> getFeedbacksBySurveyId(String surveyId) {
        return getCollection().find(FeedbackEntity.class).filter(new Document(SURVEY_ID, new ObjectId(surveyId))).into(new ArrayList<>());
    }
    
    @Nullable
    public InsertOneResult addSurvey(Survey survey) {
        return getCollection().insertOne(toEntity(survey));
    }

    public UpdateResult updateSurvey(Survey survey) {
        return getCollection().replaceOne(new Document(ID, new ObjectId(survey.getId())), toEntity(survey));
    }

    public DeleteResult deleteSurvey(String id) {
        return getCollection().deleteOne(new Document(ID, new ObjectId(id)));
    }
}
