package fr.iut.uca.repository;

import com.mongodb.client.MongoCollection;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.model.surveys.Feedback;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FeedbackRepository {

    @Inject
    private DatabaseClient mongoClient;

    private MongoCollection<Document> getCollection() {
        return mongoClient.getCollection(DatabaseClient.CollectionName.FEEDBACKS);
    }

    public List<Feedback> getAll() {
        return getCollection().find(Feedback.class).into(new ArrayList<>());
    }
}
