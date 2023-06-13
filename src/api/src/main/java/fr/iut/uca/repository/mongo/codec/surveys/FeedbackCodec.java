package fr.iut.uca.repository.mongo.codec.surveys;

import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.entity.surveys.QuestionInfoEntity;
import fr.iut.uca.entity.surveys.QuestionTypeEntity;
import fr.iut.uca.extension.surveys.QuestionInfoExtensions;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

import static fr.iut.uca.extension.DateExtensions.toLocalDate;
import static fr.iut.uca.extension.surveys.FeedbackExtensions.*;

public class FeedbackCodec implements Codec<FeedbackEntity> {

    @Override
    public FeedbackEntity decode(BsonReader bsonReader, DecoderContext decoderContext) {
        var feedback = new FeedbackEntity();

        bsonReader.readStartDocument();

        feedback.setId(bsonReader.readObjectId(ID).toString());
        feedback.setSurveyId(bsonReader.readObjectId(SURVEY_ID).toString());
        feedback.setCreatedAt(toLocalDate(new Date(bsonReader.readDateTime(CREATED_AT))));
        feedback.setAuthor(bsonReader.readString(AUTHOR));
        feedback.setQuestion(decodeQuestionInfo(bsonReader));
        feedback.setAnswers(SurveyCodecHelper.decodeStringArray(bsonReader));

        bsonReader.readEndDocument();

        return feedback;
    }

    @Override
    public void encode(BsonWriter bsonWriter, FeedbackEntity feedbackEntity, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();

        if (feedbackEntity.getId() != null)
            bsonWriter.writeObjectId(ID, new ObjectId(feedbackEntity.getId()));

        bsonWriter.writeObjectId(SURVEY_ID, new ObjectId(feedbackEntity.getSurveyId()));
        bsonWriter.writeDateTime(CREATED_AT, feedbackEntity.getCreatedAt().toEpochDay());
        bsonWriter.writeString(AUTHOR, feedbackEntity.getAuthor());
        encodeQuestionInfo(bsonWriter, feedbackEntity.getQuestion());
        encodeAnswers(bsonWriter, feedbackEntity.getAnswers());

        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<FeedbackEntity> getEncoderClass() {
        return null;
    }

    private QuestionInfoEntity decodeQuestionInfo(BsonReader bsonReader) {
        var questionInfo = new QuestionInfoEntity();

        bsonReader.readStartDocument();
        questionInfo.setTitle(bsonReader.readString(QuestionInfoExtensions.TITLE));
        questionInfo.setType(QuestionTypeEntity.valueOf(bsonReader.readString(QuestionInfoExtensions.TYPE).toUpperCase()));
        bsonReader.readEndDocument();

        return questionInfo;
    }



    private void encodeQuestionInfo(BsonWriter bsonWriter, QuestionInfoEntity questionInfoEntity) {
        bsonWriter.writeStartDocument(QUESTION);

        bsonWriter.writeString(QuestionInfoExtensions.TITLE, questionInfoEntity.getTitle());
        bsonWriter.writeString(QuestionInfoExtensions.TYPE, questionInfoEntity.getType().name().toLowerCase());

        bsonWriter.writeEndDocument();
    }

    private void encodeAnswers(BsonWriter bsonWriter, List<String> answers) {
        bsonWriter.writeStartArray(ANSWERS);

        for (var answer : answers) {
            bsonWriter.writeString(answer);
        }

        bsonWriter.writeEndArray();
    }
}
