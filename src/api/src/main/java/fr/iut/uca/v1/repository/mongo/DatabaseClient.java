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

@ApplicationScoped
public class DatabaseClient {

    public enum CollectionName {
        FEEDBACKS("feedbacks"), ISSUES("issues"), SURVEYS("surveys"), ISSUE_MODELS("issue_models");

        private final String name;

        CollectionName(String name) {
            this.name = name;
        }
    }

    @ConfigProperty(name = "codefirst-support.database.name")
    String databaseName;

    @ConfigProperty(name = "%dev.quarkus.mongodb.connection-string")
    String connectionString;

    private MongoClient client;

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

    private MongoDatabase database() {
        return client.getDatabase(databaseName);
    }

    public <TDocument> MongoCollection<TDocument>  getCollection(CollectionName collectionName, Class<TDocument> documentClass) {
        return database().getCollection(collectionName.name, documentClass);
    }
}
