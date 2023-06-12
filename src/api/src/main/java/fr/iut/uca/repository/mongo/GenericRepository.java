package fr.iut.uca.repository.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import fr.iut.uca.DatabaseClient;
import fr.iut.uca.repository.IGenericRepository;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * Generic repository for MongoDB.
 * @param <T> The type of the document.
 */
public abstract class GenericRepository<T> implements IGenericRepository<T> {

    /**
     * The MongoDB client.
     */
    protected DatabaseClient mongoClient;

    /**
     * The MongoDB collection.
     */
    protected MongoCollection<T> collection;

    /**
     * The name of the entity's id field.
     */
    private final String typeId;

    /**
     * Constructor.
     * @param typeId The name of the entity's id field.
     */
    protected GenericRepository(String typeId) {
        this.typeId = typeId;
    }

    /**
     * Set the mongo client.
     * @param mongoClient the mongo client
     */
    public abstract void setMongoClient(DatabaseClient mongoClient);

    @Override
    public long getCount() {
        return collection.countDocuments();
    }

    @Override
    public List<T> getItems(int index, int count) {
        return collection.find().skip(index).limit(count).into(new ArrayList<>());
    }

    @Override
    public Optional<T> getItemById(String id) {
        var filter = Filters.eq(typeId, new ObjectId(id));
        T result = collection.find().filter(filter).first();

        return Optional.ofNullable(result);
    }

    @Override
    public Optional<T> addItem(T item) {
        InsertOneResult insertResult = collection.insertOne(item);

        if (insertResult.getInsertedId() == null) {
            return Optional.empty();
        }

        return getItemById(Objects.requireNonNull(insertResult.getInsertedId()).toString());
    }

    @Override
    public Optional<T> updateItem(T item) {
        var filter = Filters.eq(typeId, new ObjectId(item.toString()));
        UpdateResult updateResult = collection.replaceOne(filter, item);

        if (updateResult.getModifiedCount() == 0) {
            return Optional.empty();
        }

        return getItemById(Objects.requireNonNull(updateResult.getUpsertedId()).toString());
    }

    @Override
    public boolean deleteItem(String id) {
        return collection.deleteOne(new Document(typeId, id)).getDeletedCount() == 1;
    }
}
