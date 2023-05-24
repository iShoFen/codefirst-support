package fr.iut.uca;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

@Path("/api")
public class GreetingResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/feedbacks")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFeedbacks() {
        String uri = "mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("codefirst-support");
            MongoCollection<Document> collection = database.getCollection("feedback");
            Document doc = collection.find(eq("author", "samuel")).first();

            if (doc != null) {
                System.out.println(doc.toJson());
                var createdAt = doc.get("createdAt");
                return doc.toJson();
            } else {
                System.out.println("No matching documents found.");
            }
        }
        catch (Exception e) {
        }

        return null;
    }

}
