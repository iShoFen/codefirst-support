package fr.iut.uca.repository.mongo.codec.issues;

import fr.iut.uca.entity.issues.CategoryEntity;
import fr.iut.uca.extension.issues.CategoryExtensions;
import fr.iut.uca.extension.issues.IssueModelExtensions;
import org.bson.BsonReader;
import org.bson.BsonWriter;

public abstract class IssueCodecHelper {
    private IssueCodecHelper() { }

    public static CategoryEntity decodeCategory(BsonReader bsonReader) {
        var category = new CategoryEntity();

        bsonReader.readStartDocument();

        category.setName(bsonReader.readString(CategoryExtensions.NAME));

        bsonReader.readEndDocument();

        return category;
    }

    public static void encodeCategory(BsonWriter bsonWriter, CategoryEntity categoryEntity) {
        bsonWriter.writeStartDocument(IssueModelExtensions.CATEGORY);

        bsonWriter.writeString(CategoryExtensions.NAME, categoryEntity.getName());

        bsonWriter.writeEndDocument();
    }
}
