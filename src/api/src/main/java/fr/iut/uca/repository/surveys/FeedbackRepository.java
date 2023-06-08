package fr.iut.uca.repository.surveys;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.lang.Nullable;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.model.surveys.Feedback;
import fr.iut.uca.utils.surveys.FeedbackExtensions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.utils.surveys.FeedbackExtensions.CREATED_AT;
import static fr.iut.uca.utils.surveys.FeedbackExtensions.ID;


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

    public List<FeedbackEntity> getFeedbacksCreatedAt(LocalDate createdAt, int index, int count) {
        return getCollection().find(FeedbackEntity.class).filter(new Document(CREATED_AT, createdAt))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<FeedbackEntity> getFeedbacksCreatedBefore(LocalDate createdAt, int index, int count) {
        return getCollection().find(FeedbackEntity.class).filter(new Document(CREATED_AT, new Document("$lt", createdAt)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<FeedbackEntity> getFeedbacksCreatedAfter(LocalDate createdAt, int index, int count) {
        return getCollection().find(FeedbackEntity.class).filter(new Document(CREATED_AT, new Document("$gt", createdAt)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<FeedbackEntity> getFeedbacksCreatedBetween(LocalDate start, LocalDate end, int index, int count) {
        return getCollection().find(FeedbackEntity.class).filter(new Document(CREATED_AT, new Document("$gte", start).append("$lte", end))).
                skip(index).limit(count).into(new ArrayList<>());
    }

    @Nullable
    public FeedbackEntity getFeedbackById(String id) {
        return getCollection().find(FeedbackEntity.class).filter(new Document(ID, new ObjectId(id))).first();
    }

    @Nullable
    public InsertOneResult addFeedback(Feedback feedback) {
        return getCollection().insertOne(FeedbackExtensions.toEntity(feedback));
    }

    public UpdateResult updateFeedback(Feedback feedback) {
        return getCollection().replaceOne(new Document(ID, new ObjectId(feedback.getId())), FeedbackExtensions.toEntity(feedback));
    }

    public DeleteResult deleteFeedback(String id) {
        return getCollection().deleteOne(new Document(ID, new ObjectId(id)));
    }
}
