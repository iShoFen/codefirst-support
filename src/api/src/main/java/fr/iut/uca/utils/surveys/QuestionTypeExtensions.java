package fr.iut.uca.utils.surveys;

import fr.iut.uca.entity.surveys.QuestionTypeEntity;
import fr.iut.uca.model.surveys.QuestionType;

public abstract class QuestionTypeExtensions {

    public static QuestionTypeEntity toEntity(QuestionType questionType) {
        try {
            return QuestionTypeEntity.valueOf(questionType.name());
        } catch (IllegalArgumentException e) {
            return QuestionTypeEntity.NONE;
        }
    }

    public static QuestionType toModel(QuestionTypeEntity questionTypeEntity) {
        try {
            return QuestionType.valueOf(questionTypeEntity.name());
        } catch (IllegalArgumentException e) {
            return QuestionType.NONE;
        }
    }
}
