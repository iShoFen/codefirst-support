package fr.iut.uca.v1.repository.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.iut.uca.v1.entity.issues.CategoryEntity;
import fr.iut.uca.v1.entity.issues.IssueEntity;
import fr.iut.uca.v1.entity.issues.IssueModelEntity;
import fr.iut.uca.v1.entity.surveys.FeedbackEntity;
import fr.iut.uca.v1.entity.surveys.SurveyEntity;
import fr.iut.uca.v1.repository.mongo.codec.issues.CategoryCodec;
import fr.iut.uca.v1.repository.mongo.codec.issues.IssueCodec;
import fr.iut.uca.v1.repository.mongo.codec.issues.IssueModelCodec;
import fr.iut.uca.v1.repository.mongo.codec.surveys.FeedbackCodec;
import fr.iut.uca.v1.repository.mongo.codec.surveys.SurveyCodec;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Database client
 */
@ApplicationScoped
public class DatabaseClient {

    /**
     * Collection name
     */
    public enum CollectionName {
        FEEDBACKS("feedbacks"), ISSUES("issues"), SURVEYS("surveys"), ISSUE_MODELS("issue_models");

        /**
         * Collection name as string
         */
        private final String name;

        /**
         * Constructor
         * @param name Collection name as string
         */
        CollectionName(String name) {
            this.name = name;
        }
    }

    /**
     * Database name
     */
    @ConfigProperty(name = "codefirst-support.database.name")
    String databaseName;

    /**
     * Connection string
     */
    @ConfigProperty(name = "quarkus.mongodb.connection-string")
    String connectionString;

    /**
     * Mongo client
     */
    private MongoClient client;

    /**
     * Init mongo client
     */
    @PostConstruct
    void initMongoClient() {
        CodecRegistry defaultCodecRegistry = MongoClientSettings.getDefaultCodecRegistry();

        Codec<CategoryEntity> categoryCodec = new CategoryCodec();
        Codec<IssueEntity> issueCodec = new IssueCodec();
        Codec<IssueModelEntity> issueModelCodec = new IssueModelCodec();
        Codec<SurveyEntity> surveyCodec = new SurveyCodec();
        Codec<FeedbackEntity> feedbackCodec = new FeedbackCodec();

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromCodecs(categoryCodec, issueCodec, issueModelCodec, surveyCodec, feedbackCodec);
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(defaultCodecRegistry, pojoCodecRegistry);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .codecRegistry(codecRegistry)
                .build();

        client = MongoClients.create(settings);
    }

    /**
     * Get database
     * @return Database
     */
    private MongoDatabase database() {
        return client.getDatabase(databaseName);
    }

    /**
     * Get collection
     * @param collectionName Collection name
     * @param documentClass Document class
     * @param <TDocument> Document type
     * @return Collection
     */
    public <TDocument> MongoCollection<TDocument>  getCollection(CollectionName collectionName, Class<TDocument> documentClass) {
        return database().getCollection(collectionName.name, documentClass);
    }
}
