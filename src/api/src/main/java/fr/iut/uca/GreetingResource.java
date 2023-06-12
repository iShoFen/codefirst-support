package fr.iut.uca;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.repository.issues.IIssueRepository;
import fr.iut.uca.repository.mongo.surveys.SurveyRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;

@Path("")
public class GreetingResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }


    @GET
    @Path("feedback")
    public String getFeedback() {
        String uri = "mongodb://localhost:27017/?directConnection=true&serverSelectionTimeoutMS=2000";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("codefirst-support");
            MongoCollection<Document> collection = database.getCollection("feedbacks");
            Document doc = collection.find(eq("author", "samuel")).first();

            if (doc != null) {
                System.out.println(doc.toJson());
                return doc.toJson();
            } else {
                System.out.println("No matching documents found.");
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return "no result";
    }

    @GET
    @Path("feedbacks")
    public Response getFeedbacks() {
        String uri = "mongodb://localhost:27017/?directConnection=true&serverSelectionTimeoutMS=2000";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("codefirst-support");
            MongoCollection<Document> collection = database.getCollection("feedbacks");

            var documents = collection.find().projection(include("_id", "author", "content", "created_at")).into(new ArrayList<>());
            System.out.println(documents);
            return Response.ok(documents).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @Inject
    DatabaseClient dbClient;

    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    SurveyRepository surveyRepository;

    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    IIssueRepository issueRepository;

    @GET
    @Path("/test1")
    public Response test1() {
        MongoCollection<FeedbackEntity> collection = dbClient.getCollection(DatabaseClient.CollectionName.FEEDBACKS, FeedbackEntity.class);
        var documents = collection.find().projection(include("_id", "content", "created_at")).into(new ArrayList<>());
        return Response.ok(documents).build();
    }

    @GET
    @Path("/test2")
    public Response test() {
        return Response.ok(issueRepository.getIssuesCountByStatus()).build();
    }

    @GET
    @Path("/test3")
    public Response test2() {
        return Response.ok(surveyRepository.getItemById("64846962f660a9babbe122d8")).build();
    }

    @GET
    @Path("/test4")
    public Response test3() {
        return Response.ok(surveyRepository.getSurveyWithFeedbacks("64846962f660a9babbe122d9", 0, 2)).build();
    }
}
