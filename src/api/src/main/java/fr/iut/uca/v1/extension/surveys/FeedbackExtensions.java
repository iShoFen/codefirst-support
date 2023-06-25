package fr.iut.uca.v1.extension.surveys;

import fr.iut.uca.v1.dto.surveys.feedback.FeedbackDTO;
import fr.iut.uca.v1.dto.surveys.feedback.FeedbackDetailDTO;
import fr.iut.uca.v1.entity.surveys.FeedbackEntity;
import fr.iut.uca.v1.model.surveys.Feedback;

import java.util.List;

public abstract class FeedbackExtensions {

    public static final String ID = "_id";
    public static final String VERSION = "version";
    public static final String SURVEY_ID = "survey_id";
    public static final String CREATED_AT = "created_at";
    public static final String AUTHOR = "author";
    public static final String QUESTION = "question";
    public static final String ANSWERS = "answers";

    private FeedbackExtensions() { }

    public static FeedbackEntity modelToEntity(Feedback feedback) {
        var feedbackEntity = new FeedbackEntity();

        if (feedback.getId() != null) {
            feedbackEntity.setId(feedback.getId());
        }

        feedbackEntity.setAnswers(feedback.getAnswers());
        feedbackEntity.setSurveyId(feedback.getSurveyId());
        feedbackEntity.setAuthor(feedback.getAuthor());
        feedbackEntity.setCreatedAt(feedback.getCreatedAt());
        feedbackEntity.setQuestion(QuestionInfoExtensions.modelToEntity(feedback.getQuestion()));

        return feedbackEntity;
    }

    public static List<FeedbackEntity> modelsToEntities(List<Feedback> feedbacks) {
        return feedbacks.stream().map(FeedbackExtensions::modelToEntity).toList();
    }

    public static Feedback entityToModel(FeedbackEntity feedbackEntity) {
        return new Feedback(
                feedbackEntity.getId(),
                feedbackEntity.getSurveyId(),
                feedbackEntity.getCreatedAt(),
                feedbackEntity.getAuthor(),
                QuestionInfoExtensions.entityToModel(feedbackEntity.getQuestion()),
                feedbackEntity.getAnswers()
        );
    }

    public static List<Feedback> entitiesToModels(List<FeedbackEntity> feedbackEntities) {
        return feedbackEntities.stream().map(FeedbackExtensions::entityToModel).toList();
    }

    public static FeedbackDTO modelToDTO(Feedback feedback) {
        return new FeedbackDTO(
                feedback.getId(),
                feedback.getSurveyId(),
                feedback.getCreatedAt(),
                feedback.getAuthor()
        );
    }

    public static List<FeedbackDTO> modelsToDTOs(List<Feedback> feedbacks) {
        return feedbacks.stream().map(FeedbackExtensions::modelToDTO).toList();
    }

    public static FeedbackDetailDTO modelToDetailDTO(Feedback feedback) {
        return new FeedbackDetailDTO(
                feedback.getId(),
                feedback.getSurveyId(),
                feedback.getCreatedAt(),
                feedback.getAuthor(),
                QuestionInfoExtensions.modelToDTO(feedback.getQuestion()),
                feedback.getAnswers()
        );
    }

    public static List<FeedbackDetailDTO> modelsToDetailDTOs(List<Feedback> feedbacks) {
        return feedbacks.stream().map(FeedbackExtensions::modelToDetailDTO).toList();
    }
}
