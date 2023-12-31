package fr.iut.uca.v1.repository.mongo.issues;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import fr.iut.uca.v1.entity.issues.IssueWithStatusEntity;
import fr.iut.uca.v1.repository.mongo.DatabaseClient;
import fr.iut.uca.v1.entity.issues.IssueEntity;
import fr.iut.uca.v1.entity.issues.IssueStatusEntity;
import fr.iut.uca.v1.extension.issues.IssueExtensions;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.v1.repository.issues.IIssueRepository;
import fr.iut.uca.v1.repository.mongo.GenericRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import static fr.iut.uca.v1.extension.issues.IssueExtensions.*;

/**
 * Repository for issues.
 */
@ApplicationScoped
@RepositoryQualifier(RepositoryType.MONGO)
public class IssueRepository extends GenericRepository<IssueEntity> implements IIssueRepository {

    /**
     * Constructor.
     */
    IssueRepository() {
        super(IssueExtensions.ID);
    }

    @Override
    @Inject
    public void setMongoClient(DatabaseClient mongoClient) {
        this.mongoClient = mongoClient;
        this.collection = mongoClient.getCollection(DatabaseClient.CollectionName.ISSUES, IssueEntity.class);
    }

    @Override
    public IssueWithStatusEntity getIssuesCountByStatus() {
        String countParam = "count";

        Bson groupStage = Aggregates.group("$" + STATUS, Accumulators.sum(countParam, 1));

        Bson projectStage = Aggregates.project(Projections.fields(Projections.excludeId(), Projections.computed(STATUS, "$" + ID), Projections.include(countParam)));
        AggregateIterable<Document> result = collection.aggregate(List.of(groupStage, projectStage), Document.class);

        List<SimpleEntry<IssueStatusEntity, Long>> issuesCountByStatus = new ArrayList<>();

        result.forEach(document -> {
            IssueStatusEntity status = IssueStatusEntity.valueOf(document.getString(STATUS).toUpperCase());
            long count = document.getInteger(countParam);
            issuesCountByStatus.add(new SimpleEntry<>(status, count));
        });

        var issueWithStatusEntity = new IssueWithStatusEntity();
        issueWithStatusEntity.setIssuesCountByStatus(issuesCountByStatus);

        return issueWithStatusEntity;
    }

    @Override
    public List<IssueEntity> getItemsByStatus(IssueStatusEntity status, int index, int count) {
        var filter = Filters.eq(STATUS, status.name().toLowerCase());
        return collection.find(IssueEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<IssueEntity> getIssuesByCreatedDate(LocalDate createdAt, IssueStatusEntity status, int index, int count) {
        var filter = Filters.and(Filters.eq(STATUS, status.name().toLowerCase()), Filters.eq(CREATED_AT, createdAt));
        return collection.find(IssueEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<IssueEntity> getIssuesByCreatedDateBefore(LocalDate createdAt, IssueStatusEntity status, int index, int count) {
        var filter = Filters.and(Filters.eq(STATUS, status.name().toLowerCase()), Filters.lt(CREATED_AT, createdAt));
        return collection.find(IssueEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<IssueEntity> getIssuesByCreatedDateAfter(LocalDate createdAt, IssueStatusEntity status, int index, int count) {
        var filter = Filters.and(Filters.eq(STATUS, status.name().toLowerCase()), Filters.gt(CREATED_AT, createdAt));
        return collection.find(IssueEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<IssueEntity> getIssuesByCreatedDateBetween(LocalDate start, LocalDate end, IssueStatusEntity status, int index, int count) {
        var filter = Filters.and(Filters.eq(STATUS, status.name().toLowerCase()), Filters.gte(CREATED_AT, start), Filters.lte(CREATED_AT, end));
        return collection.find(IssueEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<IssueEntity> getIssuesWithTitleContaining(String title, IssueStatusEntity status, int index, int count) {
        var filter = Filters.and(Filters.eq(STATUS, status.name().toLowerCase()), Filters.regex(TITLE, title));
        return collection.find(IssueEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public List<IssueEntity> getIssuesOfAuthor(String author, IssueStatusEntity status, int index, int count) {
        var filter = Filters.and(Filters.eq(STATUS, status.name().toLowerCase()), Filters.eq(AUTHOR, author));
        return collection.find(IssueEntity.class).filter(filter).skip(index).limit(count).into(new ArrayList<>());
    }
}
