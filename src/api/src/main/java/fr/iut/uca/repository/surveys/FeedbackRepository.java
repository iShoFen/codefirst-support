package fr.iut.uca.repository.surveys;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.lang.Nullable;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.entity.surveys.QuestionEntity;
import fr.iut.uca.entity.surveys.QuestionTypeEntity;
import fr.iut.uca.model.surveys.Feedback;
import fr.iut.uca.utils.surveys.FeedbackExtensions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FeedbackRepository {

    @Inject
    DatabaseClient mongoClient;

    private MongoCollection<FeedbackEntity> getCollection() {
        return mongoClient.getCollection(DatabaseClient.CollectionName.FEEDBACKS, FeedbackEntity.class);
    }

    public long getFeedbacksCount() {
        return getCollection().countDocuments();
    }
    public List<FeedbackEntity> getFeedbacks(int index, int count) {
        return getCollection().find(FeedbackEntity.class).skip(index).limit(count).into(new ArrayList<>());
    }

    @Nullable
    public FeedbackEntity getFeedbackById(String id) {
        return getCollection().find(FeedbackEntity.class).filter(new Document("_id", new ObjectId(id))).first();
    }

    @Nullable
    public FeedbackEntity getFeedbackBySurveyId(String surveyId) {
        return getCollection().find(FeedbackEntity.class).filter(new Document("surveyId", new ObjectId(surveyId))).first();
    }

    @Nullable
    public InsertOneResult addFeedback(Feedback feedback) {
        return getCollection().insertOne(FeedbackExtensions.toEntity(feedback));
    }

    public UpdateResult updateFeedback(Feedback feedback) {
        return getCollection().replaceOne(new Document("_id", new ObjectId(feedback.getId())), FeedbackExtensions.toEntity(feedback));
    }

    public DeleteResult deleteFeedback(String id) {
        return getCollection().deleteOne(new Document("_id", new ObjectId(id)));
    }
}
