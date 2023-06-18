package fr.iut.uca.repository.mongo.codec.issues;

import fr.iut.uca.entity.issues.IssueModelEntity;
import fr.iut.uca.entity.issues.IssueModelFieldEntity;
import fr.iut.uca.extension.issues.IssueModelExtensions;
import fr.iut.uca.extension.issues.IssueModelFieldExtensions;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class IssueModelCodec implements Codec<IssueModelEntity> {
    @Override
    public IssueModelEntity decode(BsonReader bsonReader, DecoderContext decoderContext) {
        var issueModelEntity = new IssueModelEntity();

        bsonReader.readStartDocument();

        issueModelEntity.setId(bsonReader.readObjectId(IssueModelExtensions.ID).toString());
        issueModelEntity.setName(bsonReader.readString(IssueModelExtensions.NAME));
        issueModelEntity.setShortDescription(bsonReader.readString(IssueModelExtensions.SHORT_DESCRIPTION));
        issueModelEntity.setDescription(bsonReader.readString(IssueModelExtensions.DESCRIPTION));
        issueModelEntity.setCategory(new CategoryCodec().decode(bsonReader, decoderContext));
        issueModelEntity.setFields(decodeFields(bsonReader));

        bsonReader.readEndDocument();

        return issueModelEntity;
    }

    @Override
    public void encode(BsonWriter bsonWriter, IssueModelEntity issueModelEntity, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();

        if (issueModelEntity.getId() != null)
            bsonWriter.writeObjectId(IssueModelExtensions.ID, new ObjectId(issueModelEntity.getId()));

        bsonWriter.writeString(IssueModelExtensions.NAME, issueModelEntity.getName());
        bsonWriter.writeString(IssueModelExtensions.SHORT_DESCRIPTION, issueModelEntity.getShortDescription());
        bsonWriter.writeString(IssueModelExtensions.DESCRIPTION, issueModelEntity.getDescription());
        new CategoryCodec().encode(bsonWriter, issueModelEntity.getCategory(), encoderContext);
        encodeFields(bsonWriter, issueModelEntity.getFields());

        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<IssueModelEntity> getEncoderClass() {
        return IssueModelEntity.class;
    }

    private List<IssueModelFieldEntity> decodeFields(BsonReader bsonReader) {
        List<IssueModelFieldEntity> fields = new ArrayList<>();
        bsonReader.readStartArray();

        while (bsonReader.readBsonType() != org.bson.BsonType.END_OF_DOCUMENT) {
            var field = new IssueModelFieldEntity();

            bsonReader.readStartDocument();

            field.setTitle(bsonReader.readString(IssueModelFieldExtensions.TITLE));
            field.setDescription(bsonReader.readString(IssueModelFieldExtensions.DESCRIPTION));
            field.setRequired(bsonReader.readBoolean(IssueModelFieldExtensions.REQUIRED));

            bsonReader.readEndDocument();

            fields.add(field);
        }

        bsonReader.readEndArray();

        return fields;
    }

    private void encodeFields(BsonWriter bsonWriter, List<IssueModelFieldEntity> issueModelFieldEntities) {
        bsonWriter.writeStartArray(IssueModelExtensions.FIELDS);
        for (var field : issueModelFieldEntities) {
            bsonWriter.writeStartDocument();

            bsonWriter.writeString(IssueModelFieldExtensions.TITLE, field.getTitle());
            bsonWriter.writeString(IssueModelFieldExtensions.DESCRIPTION, field.getDescription());
            bsonWriter.writeBoolean(IssueModelFieldExtensions.REQUIRED, field.isRequired());

            bsonWriter.writeEndDocument();
        }

        bsonWriter.writeEndArray();
    }
}
