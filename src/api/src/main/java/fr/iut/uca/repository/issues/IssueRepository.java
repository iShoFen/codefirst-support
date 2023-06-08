package fr.iut.uca.repository.issues;

import com.mongodb.client.MongoCollection;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.entity.issues.IssueEntity;
import fr.iut.uca.model.surveys.Feedback;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    @Inject
    DatabaseClient mongoClient;

    private MongoCollection<IssueEntity> getCollection() {
        return mongoClient.getCollection(DatabaseClient.CollectionName.ISSUES, IssueEntity.class);
    }

    public List<IssueEntity> getIssues(int index, int count) {
        return getCollection().find(IssueEntity.class).skip(index).limit(count).into(new ArrayList<>());
    }
}
