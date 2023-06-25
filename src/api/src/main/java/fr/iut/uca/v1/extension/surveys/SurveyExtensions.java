package fr.iut.uca.v1.extension.surveys;

import fr.iut.uca.v1.dto.surveys.survey.SurveyDTO;
import fr.iut.uca.v1.dto.surveys.survey.SurveyDetailDTO;
import fr.iut.uca.v1.entity.surveys.SurveyEntity;
import fr.iut.uca.v1.model.surveys.Survey;

import java.util.List;

public abstract class SurveyExtensions {

    public static final String ID = "_id";
    public static final String VERSION = "version";
    public static final String CREATED_AT = "created_at";
    public static final String PUBLISHED_AT = "published_at";
    public static final String END_AT = "end_at";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String QUESTIONS = "questions";
    public static final String FEEDBACK = "feedback";

    private SurveyExtensions() { }

    public static SurveyEntity modelToEntity(Survey survey) {
        var surveyEntity =  new SurveyEntity();
        surveyEntity.setCreatedAt(survey.getCreatedAt());
        surveyEntity.setDescription(survey.getDescription());
        surveyEntity.setTitle(survey.getTitle());
        surveyEntity.setEndAt(survey.getEndAt());
        surveyEntity.setPublishedAt(survey.getPublishedAt());
        surveyEntity.setId(survey.getId());

        surveyEntity.setQuestions(QuestionExtensions.modelsToEntities(survey.getQuestions()));

        return surveyEntity;
    }
    public List<SurveyEntity> modelsToEntities(List<Survey> surveys) {
        return surveys.stream().map(SurveyExtensions::modelToEntity).toList();
    }

    public static Survey entityToModel(SurveyEntity surveyEntity) {
        return new Survey(
                surveyEntity.getId(),
                surveyEntity.getTitle(),
                surveyEntity.getCreatedAt(),
                surveyEntity.getPublishedAt(),
                surveyEntity.getEndAt(),
                surveyEntity.getDescription(),
                QuestionExtensions.entitiesToModels(surveyEntity.getQuestions()),
                surveyEntity.getFeedback().isPresent() ? FeedbackExtensions.entityToModel(surveyEntity.getFeedback().get()) : null
        );
    }

    public static List<Survey> entitiesToModels(List<SurveyEntity> surveyEntities) {
        return surveyEntities.stream().map(SurveyExtensions::entityToModel).toList();
    }

    public static SurveyDTO modelToDTO(Survey survey) {
        return new SurveyDTO(
                survey.getId(),
                survey.getCreatedAt(),
                survey.getPublishedAt(),
                survey.getEndAt(),
                survey.getTitle(),
                survey.getDescription()
        );
    }

    public static List<SurveyDTO> modelsToDTOs(List<Survey> surveys) {
        return surveys.stream().map(SurveyExtensions::modelToDTO).toList();
    }

    public static SurveyDetailDTO modelToDetailDTO(Survey survey) {
        return new SurveyDetailDTO(
                survey.getId(),
                survey.getCreatedAt(),
                survey.getPublishedAt(),
                survey.getEndAt(),
                survey.getTitle(),
                survey.getDescription(),
                QuestionExtensions.modelsToDTOs(survey.getQuestions()),
                survey.getFeedback().isPresent() ? FeedbackExtensions.modelToDTO(survey.getFeedback().get()) : null
        );
    }

    public static List<SurveyDetailDTO> modelsToDetailDTOs(List<Survey> surveys) {
        return surveys.stream().map(SurveyExtensions::modelToDetailDTO).toList();
    }
}
