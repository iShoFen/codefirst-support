package fr.iut.uca.utils.surveys;

import fr.iut.uca.entity.surveys.QuestionTypeEntity;
import fr.iut.uca.model.surveys.QuestionType;

public abstract class QuestionTypeExtensions {

    public static QuestionTypeEntity toEntity(QuestionType questionType) {
        return QuestionTypeEntity.valueOf(questionType.name());
    }

    public static QuestionType toModel(QuestionTypeEntity questionTypeEntity) {
        return QuestionType.valueOf(questionTypeEntity.name());
    }
}
