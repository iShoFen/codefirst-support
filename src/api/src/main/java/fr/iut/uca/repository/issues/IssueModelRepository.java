package fr.iut.uca.repository.issues;

import com.mongodb.client.MongoCollection;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.entity.issues.IssueModelEntity;
import fr.iut.uca.model.surveys.Feedback;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class IssueModelRepository {
    @Inject
    DatabaseClient mongoClient;

    private MongoCollection<IssueModelEntity> getCollection() {
        return mongoClient.getCollection(DatabaseClient.CollectionName.ISSUE_MODELS, IssueModelEntity.class);
    }

    public List<IssueModelEntity> getIssueModels(int index, int count) {
        return getCollection().find(IssueModelEntity.class).skip(index).limit(count).into(new ArrayList<>());
    }
}
