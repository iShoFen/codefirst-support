package fr.iut.uca;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class DatabaseClient {

    public enum CollectionName {
        FEEDBACKS("feedbacks"), ISSUES("issues"), SURVEYS("surveys"), ISSUE_MODELS("issue_models");

        private final String name;

        private CollectionName(String name) {
            this.name = name;
        }
    }

    @ConfigProperty(name = "codefirstsupport.database.name")
    String databaseName;

    @Inject
    MongoClient client;

    private MongoDatabase database() {
        return client.getDatabase(databaseName);
    }

    public <TDocument> MongoCollection<TDocument>  getCollection(CollectionName collectionName, Class<TDocument> documentClass) {
        return database().getCollection(collectionName.name, documentClass);
    }
}
