package fr.iut.uca.v1.repository.mongo.codec.surveys;

import fr.iut.uca.exception.UnsupportedVersionException;
import fr.iut.uca.v1.entity.surveys.FeedbackEntity;
import fr.iut.uca.v1.entity.surveys.QuestionInfoEntity;
import fr.iut.uca.v1.entity.surveys.QuestionTypeEntity;
import fr.iut.uca.v1.extension.surveys.QuestionInfoExtensions;
import fr.iut.uca.v1.extension.DateExtensions;
import fr.iut.uca.v1.extension.surveys.FeedbackExtensions;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

import static fr.iut.uca.v1.extension.DateExtensions.toLocalDate;

public class FeedbackCodec implements Codec<FeedbackEntity> {

    @Override
    public FeedbackEntity decode(BsonReader bsonReader, DecoderContext decoderContext) {
        var feedback = new FeedbackEntity();

        bsonReader.readStartDocument();

        feedback.setId(bsonReader.readObjectId(FeedbackExtensions.ID).toString());
        var version = bsonReader.readInt32(FeedbackExtensions.VERSION);

        if (version == 1) decodeFeedback(bsonReader, decoderContext, feedback);
        else throw new UnsupportedVersionException("Feedback version not supported");

        bsonReader.readEndDocument();

        return feedback;
    }

    @Override
    public void encode(BsonWriter bsonWriter, FeedbackEntity feedbackEntity, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();

        if (feedbackEntity.getId() != null)
            bsonWriter.writeObjectId(FeedbackExtensions.ID, new ObjectId(feedbackEntity.getId()));

        bsonWriter.writeInt32(FeedbackExtensions.VERSION, 1);
        bsonWriter.writeObjectId(FeedbackExtensions.SURVEY_ID, new ObjectId(feedbackEntity.getSurveyId()));
        bsonWriter.writeDateTime(FeedbackExtensions.CREATED_AT, feedbackEntity.getCreatedAt().toEpochDay());
        bsonWriter.writeString(FeedbackExtensions.AUTHOR, feedbackEntity.getAuthor());
        encodeQuestionInfo(bsonWriter, feedbackEntity.getQuestion());
        encodeAnswers(bsonWriter, feedbackEntity.getAnswers());

        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<FeedbackEntity> getEncoderClass() {
        return null;
    }

    private void decodeFeedback(BsonReader bsonReader, DecoderContext decoderContext, FeedbackEntity feedback) {
        feedback.setSurveyId(bsonReader.readObjectId(FeedbackExtensions.SURVEY_ID).toString());
        feedback.setCreatedAt(DateExtensions.toLocalDate(new Date(bsonReader.readDateTime(FeedbackExtensions.CREATED_AT))));
        feedback.setAuthor(bsonReader.readString(FeedbackExtensions.AUTHOR));
        feedback.setQuestion(decodeQuestionInfo(bsonReader));
        feedback.setAnswers(SurveyCodecHelper.decodeStringArray(bsonReader));
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
        bsonWriter.writeStartDocument(FeedbackExtensions.QUESTION);

        bsonWriter.writeString(QuestionInfoExtensions.TITLE, questionInfoEntity.getTitle());
        bsonWriter.writeString(QuestionInfoExtensions.TYPE, questionInfoEntity.getType().name().toLowerCase());

        bsonWriter.writeEndDocument();
    }

    private void encodeAnswers(BsonWriter bsonWriter, List<String> answers) {
        bsonWriter.writeStartArray(FeedbackExtensions.ANSWERS);

        for (var answer : answers) {
            bsonWriter.writeString(answer);
        }

        bsonWriter.writeEndArray();
    }
}
