package fr.iut.uca.repository.mongo.surveys;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import fr.iut.uca.repository.mongo.DatabaseClient;
import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.entity.surveys.SurveyEntity;
import fr.iut.uca.extension.surveys.SurveyExtensions;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.repository.mongo.GenericRepository;
import fr.iut.uca.repository.surveys.IFeedbackRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.iut.uca.extension.surveys.FeedbackExtensions.*;

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
    public SurveyEntity getSurveyWithFeedback(String feedbackId) {
        Bson matchStage = Aggregates.match(Filters.eq(ID, new ObjectId(feedbackId)));
        Bson lookupStage = Aggregates.lookup(DatabaseClient.CollectionName.SURVEYS.name().toLowerCase(), SURVEY_ID, SurveyExtensions.ID, "survey");
        Bson unwindStage = Aggregates.unwind("$survey");
//        Bson replaceRootStage = Aggregates.replaceRoot(Projections.fields(
//                Projections.include("survey"),
//                Projections.computed("feedback", "$$ROOT")
//        ));

        Bson replaceRootStage = new Document("$replaceRoot",
                new Document("newRoot",
                        new Document("$mergeObjects", Arrays.asList(
                                "$survey",
                                new Document("feedback", "$$ROOT")
                        ))
                )
        );
        Bson projectStage = Aggregates.project(Projections.exclude("feedback.survey"));

        return collection.aggregate(Arrays.asList
                (matchStage, lookupStage, unwindStage, replaceRootStage, projectStage), SurveyEntity.class).first();
    }

    @Override
    public List<FeedbackEntity> getFeedbackFromSurvey(String surveyId, int index, int count) {
        var filter = Filters.eq(SURVEY_ID, new ObjectId(surveyId));
        return collection.find().filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<FeedbackEntity> getFeedbackFromAuthor(String mail, int index, int count) {
        var filter = Filters.eq(AUTHOR, mail);
        return collection.find().filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<FeedbackEntity> getFeedbackFromSurveyAndAuthor(String surveyId, String userId, int index, int count) {
        var filter = Filters.and(Filters.eq(SURVEY_ID, new ObjectId(surveyId)), Filters.eq(AUTHOR, userId));
        return collection.find().filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<FeedbackEntity> getFeedbackFromSurveyAtDate(String surveyId, LocalDate date, int index, int count) {
        var filter = Filters.and(Filters.eq(SURVEY_ID, new ObjectId(surveyId)), Filters.eq(CREATED_AT, date));
        return collection.find().filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<FeedbackEntity> getFeedbackFromSurveyBeforeDate(String surveyId, LocalDate date, int index, int count) {
        var filter = Filters.and(Filters.eq(SURVEY_ID, new ObjectId(surveyId)), Filters.lt(CREATED_AT, date));
        return collection.find().filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<FeedbackEntity> getFeedbackFromSurveyAfterDate(String surveyId, LocalDate date, int index, int count) {
        var filter = Filters.and(Filters.eq(SURVEY_ID, new ObjectId(surveyId)), Filters.gt(CREATED_AT, date));
        return collection.find().filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<FeedbackEntity> getFeedbackFromSurveyBetweenDates(String surveyId, LocalDate date1, LocalDate date2, int index, int count) {
        var filter = Filters.and(Filters.eq(SURVEY_ID, new ObjectId(surveyId)), Filters.gte(CREATED_AT, date1), Filters.lte(CREATED_AT, date2));
        return collection.find().filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }
}
