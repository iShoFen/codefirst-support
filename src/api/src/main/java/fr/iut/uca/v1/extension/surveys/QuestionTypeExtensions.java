package fr.iut.uca.v1.extension.surveys;

import fr.iut.uca.v1.dto.surveys.QuestionTypeDTO;
import fr.iut.uca.v1.entity.surveys.QuestionTypeEntity;
import fr.iut.uca.v1.model.surveys.QuestionType;

public abstract class QuestionTypeExtensions {

    public static QuestionTypeEntity modelToEntity(QuestionType questionType) throws IllegalArgumentException {
        return QuestionTypeEntity.valueOf(questionType.name());
    }

    public static QuestionType entityToModel(QuestionTypeEntity questionTypeEntity) throws IllegalArgumentException {
        return QuestionType.valueOf(questionTypeEntity.name());
    }

    public static QuestionTypeDTO modelToDTO(QuestionType questionType) throws IllegalArgumentException {
        return QuestionTypeDTO.valueOf(questionType.name());
    }

    public static QuestionType dtoToModel(QuestionTypeDTO questionTypeDTO) throws IllegalArgumentException {
        return QuestionType.valueOf(questionTypeDTO.name());
    }
}
