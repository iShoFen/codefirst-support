package fr.iut.uca.repository.mongo.codec.issues;

import fr.iut.uca.entity.issues.IssueModelFieldEntity;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class IssueModelFieldCodec implements Codec<IssueModelFieldEntity> {

    @Override
    public IssueModelFieldEntity decode(BsonReader bsonReader, DecoderContext decoderContext) {
        var issueModelFieldEntity = new IssueModelFieldEntity();

        bsonReader.readStartDocument();
        issueModelFieldEntity.setTitle(bsonReader.readString("title"));
        issueModelFieldEntity.setDescription(bsonReader.readString("description"));
        issueModelFieldEntity.setRequired(bsonReader.readBoolean("required"));
        bsonReader.readEndDocument();

        return issueModelFieldEntity;
    }

    @Override
    public void encode(BsonWriter bsonWriter, IssueModelFieldEntity issueModelFieldEntity, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();
        bsonWriter.writeString("title", issueModelFieldEntity.getTitle());
        bsonWriter.writeString("description", issueModelFieldEntity.getDescription());
        bsonWriter.writeBoolean("required", issueModelFieldEntity.isRequired());
        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<IssueModelFieldEntity> getEncoderClass() {
        return IssueModelFieldEntity.class;
    }
}
