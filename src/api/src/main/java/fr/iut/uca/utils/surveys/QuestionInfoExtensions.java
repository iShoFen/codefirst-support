package fr.iut.uca.utils.surveys;

import fr.iut.uca.entity.surveys.QuestionInfoEntity;
import fr.iut.uca.model.surveys.QuestionInfo;

import java.util.List;

public abstract class QuestionInfoExtensions {

    public static QuestionInfoEntity toEntity(QuestionInfo questionInfo) {
        var questionInfoEntity = new QuestionInfoEntity();

        questionInfoEntity.setTitle(questionInfo.getTitle());
        questionInfoEntity.setType(QuestionTypeExtensions.toEntity(questionInfo.getType()));

        return questionInfoEntity;
    }

    public static List<QuestionInfoEntity> toEntities(List<QuestionInfo> questionInfos) {
        return questionInfos.stream().map(QuestionInfoExtensions::toEntity).toList();
    }

    public static QuestionInfo toModel(QuestionInfoEntity questionInfoEntity) {
        return new QuestionInfo(
                questionInfoEntity.getTitle(),
                QuestionTypeExtensions.toModel(questionInfoEntity.getType())
        );
    }

    public static List<QuestionInfo> toModels(List<QuestionInfoEntity> questionInfoEntities) {
        return questionInfoEntities.stream().map(QuestionInfoExtensions::toModel).toList();
    }
}
