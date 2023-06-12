package fr.iut.uca.repository.mongo.codec.issues;


import fr.iut.uca.entity.issues.IssueModelInfoEntity;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class IssueModelInfoCodec implements Codec<IssueModelInfoEntity> {

    @Override
    public IssueModelInfoEntity decode(BsonReader bsonReader, DecoderContext decoderContext) {
        var issueModelInfoEntity = new IssueModelInfoEntity();

        bsonReader.readStartDocument();
        issueModelInfoEntity.setName(bsonReader.readString("name"));
        issueModelInfoEntity.setShortDescription(bsonReader.readString("short_description"));
        issueModelInfoEntity.setDescription(bsonReader.readString("description"));
        bsonReader.readEndDocument();

        return issueModelInfoEntity;
    }

    @Override
    public void encode(BsonWriter bsonWriter, IssueModelInfoEntity issueModelInfoEntity, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();
        bsonWriter.writeString("name", issueModelInfoEntity.getName());
        bsonWriter.writeString("short_description", issueModelInfoEntity.getShortDescription());
        bsonWriter.writeString("description", issueModelInfoEntity.getDescription());
        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<IssueModelInfoEntity> getEncoderClass() {
        return IssueModelInfoEntity.class;
    }
}
