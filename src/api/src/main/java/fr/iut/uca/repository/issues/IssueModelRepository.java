package fr.iut.uca.repository.issues;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.entity.issues.IssueModelEntity;
import fr.iut.uca.model.issues.IssueModel;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.utils.issues.IssueModelExtensions.NAME;

public class IssueModelRepository {
    @Inject
    DatabaseClient mongoClient;

    private MongoCollection<IssueModelEntity> getCollection() {
        return mongoClient.getCollection(DatabaseClient.CollectionName.ISSUE_MODELS, IssueModelEntity.class);
    }

    public long getIssueModelsCount() {
        return getCollection().countDocuments();
    }

    public List<IssueModelEntity> getIssueModels(int index, int count) {
        return getCollection().find(IssueModelEntity.class).skip(index).limit(count).into(new ArrayList<>());
    }

    public List<IssueModelEntity> getIssueModelsByNameContaining(String name, int index, int count) {
        return getCollection().find(IssueModelEntity.class).filter(new Document(NAME, new Document("$regex", name)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public IssueModelEntity getIssueModelById(String id) {
        return getCollection().find(IssueModelEntity.class).filter(new Document("_id", id)).first();
    }

    public InsertOneResult addIssueModel(IssueModel issueModel) {
        return getCollection().insertOne();
    }
}
