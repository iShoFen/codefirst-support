package fr.iut.uca.repository.issues;

import com.mongodb.client.MongoCollection;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.model.surveys.Feedback;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    @Inject
    DatabaseClient mongoClient;

    private MongoCollection<Document> getCollection() {
        return mongoClient.getCollection(DatabaseClient.CollectionName.ISSUES);
    }

    public List<Feedback> getAll() {
        return getCollection().find(Feedback.class).into(new ArrayList<>());
    }
}
