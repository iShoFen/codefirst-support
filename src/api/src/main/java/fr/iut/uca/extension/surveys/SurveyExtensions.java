package fr.iut.uca.extension.surveys;

import fr.iut.uca.entity.surveys.SurveyEntity;
import fr.iut.uca.model.surveys.Survey;
import org.bson.types.ObjectId;

import java.util.List;

public abstract class SurveyExtensions {

    public static final String ID = "_id";
    public static final String CREATED_AT = "created_at";
    public static final String PUBLISHED_AT = "published_at";
    public static final String END_AT = "end_at";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String QUESTIONS = "questions";
    public static final String FEEDBACK = "feedback";

    private SurveyExtensions() { }

    public static SurveyEntity toEntity(Survey survey) {
        var surveyEntity =  new SurveyEntity();
        surveyEntity.setCreatedAt(survey.getCreatedAt());
        surveyEntity.setDescription(survey.getDescription());
        surveyEntity.setTitle(survey.getTitle());
        surveyEntity.setEndAt(survey.getEndAt());
        surveyEntity.setPublishedAt(survey.getPublishedAt());
        surveyEntity.setId(survey.getId());

        surveyEntity.setQuestions(QuestionExtensions.toEntities(survey.getQuestions()));

        return surveyEntity;
    }
    public List<SurveyEntity> toEntities(List<Survey> surveys) {
        return surveys.stream().map(SurveyExtensions::toEntity).toList();
    }

    public static Survey toModel(SurveyEntity surveyEntity) {
        return new Survey(
                surveyEntity.getId(),
                surveyEntity.getTitle(),
                surveyEntity.getCreatedAt(),
                surveyEntity.getPublishedAt(),
                surveyEntity.getEndAt(),
                surveyEntity.getDescription(),
                QuestionExtensions.toModels(surveyEntity.getQuestions()),
                surveyEntity.getFeedback().isPresent() ? FeedbackExtensions.toModel(surveyEntity.getFeedback().get()) : null
        );
    }

    public static List<Survey> toModels(List<SurveyEntity> surveyEntities) {
        return surveyEntities.stream().map(SurveyExtensions::toModel).toList();
    }
}
