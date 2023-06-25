package fr.iut.uca.v1.repository.mongo.codec.issues;

import fr.iut.uca.exception.UnsupportedVersionException;
import fr.iut.uca.v1.extension.DateExtensions;
import fr.iut.uca.v1.extension.issues.CommentExtensions;
import fr.iut.uca.v1.extension.issues.IssueFieldExtensions;
import fr.iut.uca.v1.extension.issues.IssueModelInfoExtensions;
import fr.iut.uca.v1.entity.issues.*;
import fr.iut.uca.v1.extension.issues.IssueExtensions;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class IssueCodec implements Codec<IssueEntity> {
    @Override
    public IssueEntity decode(BsonReader bsonReader, DecoderContext decoderContext) {
        var issueEntity = new IssueEntity();

        bsonReader.readStartDocument();

        issueEntity.setId(bsonReader.readObjectId(IssueExtensions.ID).toString());
        var version = bsonReader.readInt32(IssueExtensions.VERSION);

        if (version == 1) decodeIssue(bsonReader, decoderContext, issueEntity);
        else throw new UnsupportedVersionException("Issue version not supported");

        bsonReader.readEndDocument();

        return issueEntity;
    }

    @Override
    public void encode(BsonWriter bsonWriter, IssueEntity issueEntity, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();

        if (issueEntity.getId() != null)
            bsonWriter.writeObjectId(IssueExtensions.ID, new ObjectId(issueEntity.getId()));

        bsonWriter.writeInt32(IssueExtensions.VERSION, 1);
        bsonWriter.writeString(IssueExtensions.TITLE, issueEntity.getTitle());
        encodeIssueModel(bsonWriter, issueEntity.getModel());
        encodeFields(bsonWriter, issueEntity.getFields());
        bsonWriter.writeString(IssueExtensions.AUTHOR, issueEntity.getAuthor());
        bsonWriter.writeDateTime(IssueExtensions.CREATED_AT, DateExtensions.toTimestamp(issueEntity.getCreatedAt()));
        bsonWriter.writeString(IssueExtensions.STATUS, issueEntity.getStatus().name().toLowerCase());
        new CategoryCodec().encode(bsonWriter, issueEntity.getCategory(), encoderContext);
        encodeComments(bsonWriter, issueEntity.getComments());

        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<IssueEntity> getEncoderClass() {
        return IssueEntity.class;
    }

    private void decodeIssue(BsonReader bsonReader, DecoderContext decoderContext, IssueEntity issueEntity) {
        issueEntity.setTitle(bsonReader.readString(IssueExtensions.TITLE));
        issueEntity.setModel(decodeIssueModelInfo(bsonReader));
        issueEntity.setFields(decodeFields(bsonReader));
        issueEntity.setAuthor(bsonReader.readString(IssueExtensions.AUTHOR));
        issueEntity.setCreatedAt(DateExtensions.toLocalDate(bsonReader.readDateTime(IssueExtensions.CREATED_AT)));
        issueEntity.setStatus(IssueStatusEntity.valueOf(bsonReader.readString(IssueExtensions.STATUS).toUpperCase()));
        issueEntity.setCategory(new CategoryCodec().decode(bsonReader, decoderContext));
        issueEntity.setComments(decodeComment(bsonReader));
    }

    private IssueModelInfoEntity decodeIssueModelInfo(BsonReader bsonReader) {
        var issueModelInfo = new IssueModelInfoEntity();

        bsonReader.readStartDocument();

        issueModelInfo.setName(bsonReader.readString(IssueModelInfoExtensions.NAME));
        issueModelInfo.setShortDescription(bsonReader.readString(IssueModelInfoExtensions.SHORT_DESCRIPTION));
        issueModelInfo.setDescription(bsonReader.readString(IssueModelInfoExtensions.DESCRIPTION));

        bsonReader.readEndDocument();

        return issueModelInfo;
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

    private void encodeIssueModel(BsonWriter bsonWriter, IssueModelInfoEntity issueModel) {
        bsonWriter.writeStartDocument(IssueExtensions.MODEL);

        bsonWriter.writeString(IssueModelInfoExtensions.NAME, issueModel.getName());
        bsonWriter.writeString(IssueModelInfoExtensions.SHORT_DESCRIPTION, issueModel.getShortDescription());
        bsonWriter.writeString(IssueModelInfoExtensions.DESCRIPTION, issueModel.getDescription());

        bsonWriter.writeEndDocument();
    }

    private void encodeComments(BsonWriter bsonWriter, List<CommentEntity> comments) {
        bsonWriter.writeStartArray(IssueExtensions.COMMENTS);

        for (var comment : comments) {
            bsonWriter.writeStartDocument();

            bsonWriter.writeDateTime(CommentExtensions.CREATED_AT, DateExtensions.toTimestamp(comment.getCreatedAt()));
            bsonWriter.writeString(CommentExtensions.AUTHOR, comment.getAuthor());
            bsonWriter.writeString(CommentExtensions.CONTENT, comment.getContent());

            bsonWriter.writeEndDocument();
        }

        bsonWriter.writeEndArray();
    }

    private void encodeFields(BsonWriter bsonWriter, List<IssueFieldEntity> issueFieldEntities) {
        bsonWriter.writeStartArray(IssueExtensions.FIELDS);

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
