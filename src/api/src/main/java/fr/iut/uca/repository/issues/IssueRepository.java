package fr.iut.uca.repository.issues;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.entity.issues.IssueEntity;
import fr.iut.uca.model.issues.Issue;
import jakarta.inject.Inject;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.utils.issues.IssueExtensions.*;

public class IssueRepository {
    @Inject
    DatabaseClient mongoClient;

    private MongoCollection<IssueEntity> getCollection() {
        return mongoClient.getCollection(DatabaseClient.CollectionName.ISSUES, IssueEntity.class);
    }

    public long getIssuesCount() {
        return getCollection().countDocuments();
    }

    public List<IssueEntity> getIssues(int index, int count, String status) {
        return getCollection().find(IssueEntity.class).filter(new Document(STATUS, status)).skip(index).limit(count).into(new ArrayList<>());
    }

    public List<IssueEntity> getIssuesByCreatedDate(LocalDate createdAt, int index, int count, String status) {
        return getCollection().find(IssueEntity.class).filter(new Document(CREATED_AT, createdAt).append(STATUS, status))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<IssueEntity> getIssuesByCreatedDateBefore(LocalDate createdAt, int index, int count, String status) {
        return getCollection().find(IssueEntity.class).filter(new Document(STATUS, status).append(CREATED_AT, new Document("$lt", createdAt)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<IssueEntity> getIssuesByCreatedDateAfter(LocalDate createdAt, int index, int count, String status) {
        return getCollection().find(IssueEntity.class).filter(new Document(STATUS, status).append(CREATED_AT, new Document("$gt", createdAt)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<IssueEntity> getIssuesByCreatedDateBetween(LocalDate start, LocalDate end, int index, int count, String status) {
        return getCollection().find(IssueEntity.class).filter(new Document(STATUS, status).append(CREATED_AT, new Document("$gt", start).append("$lt", end)))
                .skip(index).limit(count).into(new ArrayList<>());
    }

    public List<IssueEntity> getIssuesWithTitleContaining(String title, int index, int count, String status) {
        return getCollection().find(IssueEntity.class).filter(new Document(STATUS, status).append(TITLE, new Document("$regex", title)))
                .skip(index).limit(count).into(new ArrayList<>());
    }
    public InsertOneResult addIssue(Issue issue) {
        return getCollection().insertOne(toEntity(issue));
    }

    public UpdateResult updateIssue(Issue issue) {
        return getCollection().replaceOne(new Document("_id", issue.getId()), toEntity(issue));
    }

    public DeleteResult deleteIssue(IssueEntity issue) {
        return getCollection().deleteOne(new Document("_id", issue.getId()));
    }
}
