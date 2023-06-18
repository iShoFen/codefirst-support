package fr.iut.uca.repository.mongo.codec.issues;

import fr.iut.uca.entity.issues.*;
import fr.iut.uca.extension.DateExtensions;
import fr.iut.uca.extension.issues.CommentExtensions;
import fr.iut.uca.extension.issues.IssueFieldExtensions;
import fr.iut.uca.extension.issues.IssueModelExtensions;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static fr.iut.uca.extension.issues.IssueExtensions.*;


public class IssueCodec implements Codec<IssueEntity> {
    @Override
    public IssueEntity decode(BsonReader bsonReader, DecoderContext decoderContext) {
        var issueEntity = new IssueEntity();

        bsonReader.readStartDocument();

        issueEntity.setId(bsonReader.readObjectId(ID).toString());
        issueEntity.setTitle(bsonReader.readString(TITLE));
        issueEntity.setModel(decodeIssueModel(bsonReader));
        issueEntity.setFields(decodeFields(bsonReader));
        issueEntity.setAuthor(bsonReader.readString(AUTHOR));
        issueEntity.setCreatedAt(DateExtensions.toLocalDate(bsonReader.readDateTime(CREATED_AT)));
        issueEntity.setStatus(IssueStatusEntity.valueOf(bsonReader.readString(STATUS).toUpperCase()));
        issueEntity.getModel().setCategory(new CategoryCodec().decode(bsonReader, decoderContext));
        issueEntity.setComments(decodeComment(bsonReader));

        bsonReader.readEndDocument();

        return issueEntity;
    }

    @Override
    public void encode(BsonWriter bsonWriter, IssueEntity issueEntity, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();

        if (issueEntity.getId() != null)
            bsonWriter.writeObjectId(ID, new ObjectId(issueEntity.getId()));

        bsonWriter.writeString(TITLE, issueEntity.getTitle());
        encodeIssueModel(bsonWriter, issueEntity.getModel());
        encodeFields(bsonWriter, issueEntity.getFields());
        bsonWriter.writeString(AUTHOR, issueEntity.getAuthor());
        bsonWriter.writeDateTime(CREATED_AT, DateExtensions.toTimestamp(issueEntity.getCreatedAt()));
        bsonWriter.writeString(STATUS, issueEntity.getStatus().name().toLowerCase());
        new CategoryCodec().encode(bsonWriter, issueEntity.getModel().getCategory(), encoderContext);
        encodeComments(bsonWriter, issueEntity.getComments());

        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<IssueEntity> getEncoderClass() {
        return IssueEntity.class;
    }

    private IssueModelEntity decodeIssueModel(BsonReader bsonReader) {
        var issueModel = new IssueModelEntity();

        bsonReader.readStartDocument();

        issueModel.setName(bsonReader.readString(IssueModelExtensions.NAME));
        issueModel.setShortDescription(bsonReader.readString(IssueModelExtensions.SHORT_DESCRIPTION));
        issueModel.setDescription(bsonReader.readString(IssueModelExtensions.DESCRIPTION));

        bsonReader.readEndDocument();

        return issueModel;
    }

    private List<CommentEntity> decodeComment(BsonReader bsonReader) {
        List<CommentEntity> comments = new ArrayList<>();

        bsonReader.readStartArray();

        while (bsonReader.readBsonType() != org.bson.BsonType.END_OF_DOCUMENT) {
            var comment = new CommentEntity();

            bsonReader.readStartDocument();

            comment.setCreatedAt(DateExtensions.toLocalDate(bsonReader.readDateTime(CommentExtensions.CREATED_AT)));
            comment.setAuthor(bsonReader.readString(CommentExtensions.AUTHOR));
            comment.setContent(bsonReader.readString(CommentExtensions.CONTENT));

            bsonReader.readEndDocument();

            comments.add(comment);
        }

        bsonReader.readEndArray();

        return comments;
    }

    private List<IssueFieldEntity> decodeFields(BsonReader bsonReader) {
        List<IssueFieldEntity> fields = new ArrayList<>();

        bsonReader.readStartArray();

        while (bsonReader.readBsonType() != org.bson.BsonType.END_OF_DOCUMENT) {
            var field = new IssueFieldEntity();

            bsonReader.readStartDocument();

            field.setTitle(bsonReader.readString(IssueFieldExtensions.TITLE));
            field.setDescription(bsonReader.readString(IssueFieldExtensions.DESCRIPTION));
            field.setRequired(bsonReader.readBoolean(IssueFieldExtensions.REQUIRED));
            field.setValue(bsonReader.readString(IssueFieldExtensions.VALUE));

            bsonReader.readEndDocument();

            fields.add(field);
        }
        bsonReader.readEndArray();

        return fields;
    }

    private void encodeIssueModel(BsonWriter bsonWriter, IssueModelEntity issueModel) {
        bsonWriter.writeStartDocument(MODEL);

        bsonWriter.writeString(IssueModelExtensions.NAME, issueModel.getName());
        bsonWriter.writeString(IssueModelExtensions.SHORT_DESCRIPTION, issueModel.getShortDescription());
        bsonWriter.writeString(IssueModelExtensions.DESCRIPTION, issueModel.getDescription());

        bsonWriter.writeEndDocument();
    }

    private void encodeComments(BsonWriter bsonWriter, List<CommentEntity> comments) {
        bsonWriter.writeStartArray(COMMENTS);

        for (var comment : comments) {
            bsonWriter.writeStartDocument();

            bsonWriter.writeString(CommentExtensions.AUTHOR, comment.getAuthor());
            bsonWriter.writeDateTime(CommentExtensions.CREATED_AT, DateExtensions.toTimestamp(comment.getCreatedAt()));
            bsonWriter.writeString(CommentExtensions.CONTENT, comment.getContent());

            bsonWriter.writeEndDocument();
        }

        bsonWriter.writeEndArray();
    }

    private void encodeFields(BsonWriter bsonWriter, List<IssueFieldEntity> issueFieldEntities) {
        bsonWriter.writeStartArray(FIELDS);

        for (var field : issueFieldEntities) {
            bsonWriter.writeStartDocument();

            bsonWriter.writeString(IssueFieldExtensions.TITLE, field.getTitle());
            bsonWriter.writeString(IssueFieldExtensions.DESCRIPTION, field.getDescription());
            bsonWriter.writeBoolean(IssueFieldExtensions.REQUIRED, field.isRequired());
            bsonWriter.writeString(IssueFieldExtensions.VALUE, field.getValue());

            bsonWriter.writeEndDocument();
        }

        bsonWriter.writeEndArray();
    }
}
