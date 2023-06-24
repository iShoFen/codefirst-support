package fr.iut.uca.v1.extension.surveys;

import fr.iut.uca.v1.dto.surveys.QuestionTypeDTO;
import fr.iut.uca.v1.entity.surveys.QuestionTypeEntity;
import fr.iut.uca.v1.model.surveys.QuestionType;

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

    public static QuestionTypeDTO toDTO(QuestionType questionType) {
        try {
            return QuestionTypeDTO.valueOf(questionType.name());
        } catch (IllegalArgumentException e) {
            return QuestionTypeDTO.NONE;
        }
    }
}
