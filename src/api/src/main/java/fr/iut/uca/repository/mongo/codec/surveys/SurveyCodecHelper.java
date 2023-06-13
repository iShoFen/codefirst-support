package fr.iut.uca.repository.mongo.codec.surveys;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;

import java.util.ArrayList;
import java.util.List;

public abstract class SurveyCodecHelper {

    private SurveyCodecHelper() { }

    public static List<String> decodeStringArray(BsonReader bsonReader) {
        var answers = new ArrayList<String>();

        bsonReader.readStartArray();

        while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            answers.add(bsonReader.readString());
        }

        bsonReader.readEndArray();

        return answers;
    }

    public static void encodeStringArray(BsonWriter bsonWriter, List<String> array) {
        bsonWriter.writeStartArray();

        for (var string : array) {
            bsonWriter.writeString(string);
        }

        bsonWriter.writeEndArray();
    }
}
