package fr.iut.uca.v1.extension.surveys;

import fr.iut.uca.v1.dto.surveys.QuestionDTO;
import fr.iut.uca.v1.entity.surveys.QuestionEntity;
import fr.iut.uca.v1.model.surveys.Question;

import java.util.List;

public abstract class QuestionExtensions {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String TYPE = "type";
    public static final String CHOICES = "choices";

    private QuestionExtensions() { }

    public static QuestionEntity modelToEntity(Question question) {
        var questionEntity = new QuestionEntity();

        questionEntity.setChoices(question.getChoices());
        questionEntity.setDescription(question.getDescription());
        questionEntity.setTitle(question.getTitle());
        questionEntity.setType(QuestionTypeExtensions.modelToEntity(question.getType()));

        return questionEntity;
    }

    public static List<QuestionEntity> modelsToEntities(List<Question> questions) {
        return questions.stream().map(QuestionExtensions::modelToEntity).toList();
    }

    public static Question entityToModel(QuestionEntity questionEntity) {
        return new Question(
                questionEntity.getTitle(),
                QuestionTypeExtensions.entityToModel(questionEntity.getType()),
                questionEntity.getDescription(),
                questionEntity.getChoices()
        );
    }

    public static List<Question> entitiesToModels(List<QuestionEntity> questionEntities) {
        return questionEntities.stream().map(QuestionExtensions::entityToModel).toList();
    }

    public static QuestionDTO modelToDTO(Question question) {
        return new QuestionDTO(
                question.getTitle(),
                question.getDescription(),
                QuestionTypeExtensions.modelToDTO(question.getType()),
                question.getChoices()
        );
    }

    public static List<QuestionDTO> modelsToDTOs(List<Question> questions) {
        return questions.stream().map(QuestionExtensions::modelToDTO).toList();
    }

    public static Question dtoToModel(QuestionDTO questionDTO) {
        return new Question(
                questionDTO.title(),
                QuestionTypeExtensions.dtoToModel(questionDTO.type()),
                questionDTO.description(),
                questionDTO.choices()
        );
    }

    public static List<Question> dtosToModels(List<QuestionDTO> questionDTOs) {
        return questionDTOs.stream().map(QuestionExtensions::dtoToModel).toList();
    }
}
