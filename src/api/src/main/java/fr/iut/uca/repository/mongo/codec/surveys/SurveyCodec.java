package fr.iut.uca.repository.mongo.codec.surveys;

import fr.iut.uca.entity.surveys.QuestionEntity;
import fr.iut.uca.entity.surveys.QuestionTypeEntity;
import fr.iut.uca.entity.surveys.SurveyEntity;
import fr.iut.uca.extension.surveys.QuestionExtensions;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static fr.iut.uca.extension.DateExtensions.toLocalDate;
import static fr.iut.uca.extension.surveys.SurveyExtensions.*;

public class SurveyCodec implements Codec<SurveyEntity> {
    @Override
    public SurveyEntity decode(BsonReader bsonReader, DecoderContext decoderContext) {
        var surveyEntity = new SurveyEntity();

        bsonReader.readStartDocument();

        surveyEntity.setId(bsonReader.readObjectId(ID).toString());
        surveyEntity.setCreatedAt(toLocalDate(new Date(bsonReader.readDateTime(CREATED_AT))));
        surveyEntity.setPublishedAt(toLocalDate(new Date(bsonReader.readDateTime(PUBLISHED_AT))));
        surveyEntity.setEndAt(toLocalDate(new Date(bsonReader.readDateTime(END_AT))));
        surveyEntity.setTitle(bsonReader.readString(TITLE));
        surveyEntity.setDescription(bsonReader.readString(DESCRIPTION));
        surveyEntity.setQuestions(decodeQuestions(bsonReader));

        if (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT)
            surveyEntity.setFeedback(new FeedbackCodec().decode(bsonReader, decoderContext));

        bsonReader.readEndDocument();

        return surveyEntity;
    }

    @Override
    public void encode(BsonWriter bsonWriter, SurveyEntity surveyEntity, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();

        if (surveyEntity.getId() != null)
            bsonWriter.writeObjectId(ID, new ObjectId(surveyEntity.getId()));

        bsonWriter.writeDateTime(CREATED_AT, surveyEntity.getCreatedAt().toEpochDay());
        bsonWriter.writeDateTime(PUBLISHED_AT, surveyEntity.getPublishedAt().toEpochDay());
        bsonWriter.writeDateTime(END_AT, surveyEntity.getEndAt().toEpochDay());
        bsonWriter.writeString(TITLE, surveyEntity.getTitle());
        bsonWriter.writeString(DESCRIPTION, surveyEntity.getDescription());
        encodeQuestions(bsonWriter, surveyEntity.getQuestions());

        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<SurveyEntity> getEncoderClass() {
        return SurveyEntity.class;
    }

    private List<QuestionEntity> decodeQuestions(BsonReader bsonReader) {
        var questions = new ArrayList<QuestionEntity>();

        bsonReader.readStartArray();
        while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            var question = new QuestionEntity();

            bsonReader.readStartDocument();

            question.setTitle(bsonReader.readString(QuestionExtensions.TITLE));
            question.setDescription(bsonReader.readString(QuestionExtensions.DESCRIPTION));
            question.setType(QuestionTypeEntity.valueOf(bsonReader.readString(QuestionExtensions.TYPE).toUpperCase()));

            if (question.getType() != QuestionTypeEntity.TEXT)
                question.setChoices(SurveyCodecHelper.decodeStringArray(bsonReader));

            bsonReader.readEndDocument();

            questions.add(question);
        }
        bsonReader.readEndArray();

        return questions;
    }

    private void encodeQuestions(BsonWriter bsonWriter, List<QuestionEntity> questions) {
        bsonWriter.writeStartArray(QUESTIONS);

        for (var question : questions) {
            bsonWriter.writeStartDocument();

            bsonWriter.writeString(QuestionExtensions.TITLE, question.getTitle());
            bsonWriter.writeString(QuestionExtensions.DESCRIPTION, question.getDescription());
            bsonWriter.writeString(QuestionExtensions.TYPE, question.getType().name().toLowerCase());
            SurveyCodecHelper.encodeStringArray(bsonWriter, question.getChoices());

            bsonWriter.writeEndDocument();
        }

        bsonWriter.writeEndArray();
    }
}
