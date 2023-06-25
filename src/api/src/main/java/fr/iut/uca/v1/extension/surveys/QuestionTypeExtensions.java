package fr.iut.uca.v1.extension.surveys;

import fr.iut.uca.v1.dto.surveys.QuestionTypeDTO;
import fr.iut.uca.v1.entity.surveys.QuestionTypeEntity;
import fr.iut.uca.v1.model.surveys.QuestionType;

public abstract class QuestionTypeExtensions {

    public static QuestionTypeEntity toEntity(QuestionType questionType) throws IllegalArgumentException {
        return QuestionTypeEntity.valueOf(questionType.name());
    }

    public static QuestionType toModel(QuestionTypeEntity questionTypeEntity) throws IllegalArgumentException {
        return QuestionType.valueOf(questionTypeEntity.name());
    }

    public static QuestionTypeDTO toDTO(QuestionType questionType) throws IllegalArgumentException {
        return QuestionTypeDTO.valueOf(questionType.name());
    }
}
