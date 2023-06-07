package fr.iut.uca.utils.surveys;

import fr.iut.uca.entity.surveys.QuestionEntity;
import fr.iut.uca.model.surveys.Question;

import java.util.List;

public abstract class QuestionExtensions {
    public static QuestionEntity toEntity(Question question) {
        var questionEntity = new QuestionEntity();

        questionEntity.setChoices(question.getChoices());
        questionEntity.setDescription(question.getDescription());
        questionEntity.setTitle(question.getTitle());
        questionEntity.setType(QuestionTypeExtensions.toEntity(question.getType()));

        return questionEntity;
    }

    public static List<QuestionEntity> toEntities(List<Question> questions) {
        return questions.stream().map(QuestionExtensions::toEntity).toList();
    }

    public static Question toModel(QuestionEntity questionEntity) {
        return new Question(
                questionEntity.getTitle(),
                QuestionTypeExtensions.toModel(questionEntity.getType()),
                questionEntity.getDescription(),
                questionEntity.getChoices()
        );
    }

    public static List<Question> toModels(List<QuestionEntity> questionEntities) {
        return questionEntities.stream().map(QuestionExtensions::toModel).toList();
    }
}
