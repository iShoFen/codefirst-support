package fr.iut.uca.repository.surveys;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.lang.Nullable;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.entity.surveys.SurveyEntity;
import fr.iut.uca.entity.surveys.SurveyEntity;
import fr.iut.uca.model.surveys.Survey;
import fr.iut.uca.model.surveys.Survey;
import fr.iut.uca.utils.surveys.SurveyExtensions;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class SurveyRepository {    @Inject
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

    @Nullable
    public SurveyEntity getSurveyById(String id) {
        return getCollection().find(SurveyEntity.class).filter(new Document("_id", new ObjectId(id))).first();
    }

    public List<FeedbackEntity> getFeedbacksBySurveyId(String surveyId) {
        return getCollection().find(FeedbackEntity.class).filter(new Document("survey_id", new ObjectId(surveyId))).into(new ArrayList<>());
    }
    
    @Nullable
    public InsertOneResult addSurvey(Survey survey) {
        return getCollection().insertOne(SurveyExtensions.toEntity(survey));
    }

    public UpdateResult updateSurvey(Survey survey) {
        return getCollection().replaceOne(new Document("_id", new ObjectId(survey.getId())), SurveyExtensions.toEntity(survey));
    }

    public DeleteResult deleteSurvey(String id) {
        return getCollection().deleteOne(new Document("_id", new ObjectId(id)));
    }
}
