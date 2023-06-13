package fr.iut.uca.repository.mongo.codec.issues;

import fr.iut.uca.entity.issues.CategoryEntity;
import fr.iut.uca.extension.issues.CategoryExtensions;
import fr.iut.uca.extension.issues.IssueModelExtensions;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import static fr.iut.uca.extension.issues.CategoryExtensions.NAME;

public class CategoryCodec implements Codec<CategoryEntity> {
    @Override
    public CategoryEntity decode(BsonReader bsonReader, DecoderContext decoderContext) {
        var categoryEntity = new CategoryEntity();

        bsonReader.readStartDocument();

        categoryEntity.setName(bsonReader.readString(NAME));

        bsonReader.readEndDocument();

        return categoryEntity;
    }

    @Override
    public void encode(BsonWriter bsonWriter, CategoryEntity categoryEntity, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument(IssueModelExtensions.CATEGORY);

        bsonWriter.writeString(CategoryExtensions.NAME, categoryEntity.getName());

        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<CategoryEntity> getEncoderClass() {
        return CategoryEntity.class;
    }
}
