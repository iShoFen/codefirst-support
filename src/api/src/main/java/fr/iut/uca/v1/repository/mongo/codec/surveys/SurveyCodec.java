package fr.iut.uca.v1.repository.mongo.codec.surveys;

import fr.iut.uca.exception.UnsupportedVersionException;
import fr.iut.uca.v1.entity.surveys.QuestionEntity;
import fr.iut.uca.v1.entity.surveys.QuestionTypeEntity;
import fr.iut.uca.v1.entity.surveys.SurveyEntity;
import fr.iut.uca.v1.extension.surveys.QuestionExtensions;
import fr.iut.uca.v1.extension.DateExtensions;
import fr.iut.uca.v1.extension.surveys.SurveyExtensions;
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

import static fr.iut.uca.v1.extension.DateExtensions.toLocalDate;

public class SurveyCodec implements Codec<SurveyEntity> {
    @Override
    public SurveyEntity decode(BsonReader bsonReader, DecoderContext decoderContext) {
        var surveyEntity = new SurveyEntity();

        bsonReader.readStartDocument();

        surveyEntity.setId(bsonReader.readObjectId(SurveyExtensions.ID).toString());
        var version = bsonReader.readInt32(SurveyExtensions.VERSION);

        if (version == 1) decodeSurvey(bsonReader, decoderContext, surveyEntity);
        else throw new UnsupportedVersionException("Survey version not supported");

        bsonReader.readEndDocument();

        return surveyEntity;
    }

    @Override
    public void encode(BsonWriter bsonWriter, SurveyEntity surveyEntity, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();

        if (surveyEntity.getId() != null)
            bsonWriter.writeObjectId(SurveyExtensions.ID, new ObjectId(surveyEntity.getId()));

        bsonWriter.writeInt32(SurveyExtensions.VERSION, 1);
        bsonWriter.writeDateTime(SurveyExtensions.CREATED_AT, surveyEntity.getCreatedAt().toEpochDay());
        bsonWriter.writeDateTime(SurveyExtensions.PUBLISHED_AT, surveyEntity.getPublishedAt().toEpochDay());
        bsonWriter.writeDateTime(SurveyExtensions.END_AT, surveyEntity.getEndAt().toEpochDay());
        bsonWriter.writeString(SurveyExtensions.TITLE, surveyEntity.getTitle());
        bsonWriter.writeString(SurveyExtensions.DESCRIPTION, surveyEntity.getDescription());
        encodeQuestions(bsonWriter, surveyEntity.getQuestions());

        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<SurveyEntity> getEncoderClass() {
        return SurveyEntity.class;
    }

    private void decodeSurvey(BsonReader bsonReader, DecoderContext decoderContext, SurveyEntity surveyEntity) {
        surveyEntity.setCreatedAt(DateExtensions.toLocalDate(new Date(bsonReader.readDateTime(SurveyExtensions.CREATED_AT))));
        surveyEntity.setPublishedAt(DateExtensions.toLocalDate(new Date(bsonReader.readDateTime(SurveyExtensions.PUBLISHED_AT))));
        surveyEntity.setEndAt(DateExtensions.toLocalDate(new Date(bsonReader.readDateTime(SurveyExtensions.END_AT))));
        surveyEntity.setTitle(bsonReader.readString(SurveyExtensions.TITLE));
        surveyEntity.setDescription(bsonReader.readString(SurveyExtensions.DESCRIPTION));
        surveyEntity.setQuestions(decodeQuestions(bsonReader));

        if (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT)
            surveyEntity.setFeedback(new FeedbackCodec().decode(bsonReader, decoderContext));
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
        bsonWriter.writeStartArray(SurveyExtensions.QUESTIONS);

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
