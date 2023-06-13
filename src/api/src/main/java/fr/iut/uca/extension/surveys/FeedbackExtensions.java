package fr.iut.uca.extension.surveys;

import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.model.surveys.Feedback;
import org.bson.types.ObjectId;

import java.util.List;

public abstract class FeedbackExtensions {

    public static final String ID = "_id";
    public static final String SURVEY_ID = "survey_id";
    public static final String CREATED_AT = "created_at";
    public static final String AUTHOR = "author";
    public static final String QUESTION = "question";
    public static final String ANSWERS = "answers";

    private FeedbackExtensions() { }

    public static FeedbackEntity toEntity(Feedback feedback) {
        var feedbackEntity = new FeedbackEntity();

        if (feedback.getId() != null) {
            feedbackEntity.setId(feedback.getId());
        }

        feedbackEntity.setAnswers(feedback.getAnswers());
        feedbackEntity.setSurveyId(feedback.getSurveyId());
        feedbackEntity.setAuthor(feedback.getAuthor());
        feedbackEntity.setCreatedAt(feedback.getCreatedAt());
        feedbackEntity.setQuestion(QuestionInfoExtensions.toEntity(feedback.getQuestion()));

        return feedbackEntity;
    }

    public static List<FeedbackEntity> toEntities(List<Feedback> feedbacks) {
        return feedbacks.stream().map(FeedbackExtensions::toEntity).toList();
    }

    public static Feedback toModel(FeedbackEntity feedbackEntity) {
        return new Feedback(
                feedbackEntity.getId(),
                feedbackEntity.getSurveyId(),
                feedbackEntity.getCreatedAt(),
                feedbackEntity.getAuthor(),
                QuestionInfoExtensions.toModel(feedbackEntity.getQuestion()),
                feedbackEntity.getAnswers()
        );
    }

    public static List<Feedback> toModels(List<FeedbackEntity> feedbackEntities) {
        return feedbackEntities.stream().map(FeedbackExtensions::toModel).toList();
    }
}
