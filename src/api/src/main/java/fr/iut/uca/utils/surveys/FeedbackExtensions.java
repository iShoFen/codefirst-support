package fr.iut.uca.utils.surveys;

import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.model.surveys.Feedback;
import org.bson.types.ObjectId;

import java.util.List;

public abstract class FeedbackExtensions {

    public static FeedbackEntity toEntity(Feedback feedback) {
        var feedbackEntity = new FeedbackEntity();

        if (!feedback.getId().equals("")) {
            feedbackEntity.setId(new ObjectId(feedback.getId()));
        }

        feedbackEntity.setAnswers(feedback.getAnswers());
        feedbackEntity.setSurveyId(new ObjectId(feedback.getSurveyId()));
        feedbackEntity.setAuthor(feedback.getAuthor());
        feedbackEntity.setCreatedAt(feedback.getCreatedAt());
        feedbackEntity.setQuestion(QuestionExtensions.toEntity(feedback.getQuestion()));

        return feedbackEntity;
    }

    public static List<FeedbackEntity> toEntities(List<Feedback> feedbacks) {
        return feedbacks.stream().map(FeedbackExtensions::toEntity).toList();
    }

    public static Feedback toModel(FeedbackEntity feedbackEntity) {
        return new Feedback(
                feedbackEntity.getId().toString(),
                feedbackEntity.getSurveyId().toString(),
                feedbackEntity.getCreatedAt(),
                feedbackEntity.getAuthor(),
                QuestionExtensions.toModel(feedbackEntity.getQuestion()
        ));
    }

    public static List<Feedback> toModels(List<FeedbackEntity> feedbackEntities) {
        return feedbackEntities.stream().map(FeedbackExtensions::toModel).toList();
    }
}
