package fr.iut.uca.v1.extension.surveys;

import fr.iut.uca.v1.dto.surveys.QuestionInfoDTO;
import fr.iut.uca.v1.entity.surveys.QuestionInfoEntity;
import fr.iut.uca.v1.model.surveys.QuestionInfo;

import java.util.List;

public abstract class QuestionInfoExtensions {

    public static final String TITLE = "title";
    public static final String TYPE = "type";

    private QuestionInfoExtensions() { }

    public static QuestionInfoEntity modelToEntity(QuestionInfo questionInfo) {
        var questionInfoEntity = new QuestionInfoEntity();

        questionInfoEntity.setTitle(questionInfo.getTitle());
        questionInfoEntity.setType(QuestionTypeExtensions.modelToEntity(questionInfo.getType()));

        return questionInfoEntity;
    }

    public static List<QuestionInfoEntity> modelsToEntities(List<QuestionInfo> questionInfos) {
        return questionInfos.stream().map(QuestionInfoExtensions::modelToEntity).toList();
    }

    public static QuestionInfo entityToModel(QuestionInfoEntity questionInfoEntity) {
        return new QuestionInfo(
                questionInfoEntity.getTitle(),
                QuestionTypeExtensions.entityToModel(questionInfoEntity.getType())
        );
    }

    public static List<QuestionInfo> entitiesToModels(List<QuestionInfoEntity> questionInfoEntities) {
        return questionInfoEntities.stream().map(QuestionInfoExtensions::entityToModel).toList();
    }

    public static QuestionInfoDTO modelToDTO(QuestionInfo questionInfo) {
        return new QuestionInfoDTO(
                questionInfo.getTitle(),
                QuestionTypeExtensions.modelToDTO(questionInfo.getType())
        );
    }

    public static List<QuestionInfoDTO> modelsToDTOs(List<QuestionInfo> questionInfos) {
        return questionInfos.stream().map(QuestionInfoExtensions::modelToDTO).toList();
    }

    public static QuestionInfo dtoToModel(QuestionInfoDTO questionInfoDTO) {
        return new QuestionInfo(
                questionInfoDTO.title(),
                QuestionTypeExtensions.dtoToModel(questionInfoDTO.type())
        );
    }

    public static List<QuestionInfo> dtosToModels(List<QuestionInfoDTO> questionInfoDTOs) {
        return questionInfoDTOs.stream().map(QuestionInfoExtensions::dtoToModel).toList();
    }
}
