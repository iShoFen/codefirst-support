package fr.iut.uca.v1.service;

import fr.iut.uca.v1.dto.OperatorDTO;
import fr.iut.uca.v1.entity.surveys.SurveyEntity;
import fr.iut.uca.v1.model.surveys.Survey;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.v1.repository.surveys.ISurveyRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;

import static fr.iut.uca.v1.extension.DateExtensions.toLocalDate;
import static fr.iut.uca.v1.extension.surveys.SurveyExtensions.toModels;

@ApplicationScoped
public class SurveyService {

    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    ISurveyRepository surveyRepository;

    public List<Survey> getAll(int index,
                               int count,
                               Date createdAt,
                               Date publishedAt,
                               Date endAt,
                               Date endDate,
                               OperatorDTO operator) throws IllegalArgumentException {
        List<SurveyEntity> result;
        if (createdAt != null) {
            result = getCreatedAt(index, count, createdAt, endDate, operator);
        } else if (publishedAt != null) {
            result = getPublishedAt(index, count, publishedAt, endDate, operator);
        } else if (endAt != null) {
            result = getEndAt(index, count, endAt, endDate, operator);
        } else {
            result = surveyRepository.getItems(index, count);
        }

        return toModels(result);
    }

    private List<SurveyEntity> getEndAt(int index, int count, Date endAt, Date endDate, OperatorDTO operator) {
        List<SurveyEntity> result;
        if (endDate != null) {
            result = surveyRepository.getSurveysEndBetween(toLocalDate(endAt), toLocalDate(endDate), index, count);
        } else {
            switch (operator) {
                case BEFORE -> result = surveyRepository.getSurveysEndBefore(toLocalDate(endAt), index, count);
                case AFTER -> result = surveyRepository.getSurveysEndAfter(toLocalDate(endAt), index, count);
                default -> result = surveyRepository.getSurveysEndAt(toLocalDate(endAt), index, count);
            }
        }
        return result;
    }

    private List<SurveyEntity> getPublishedAt(int index, int count, Date publishedAt, Date endDate, OperatorDTO operator) {
        List<SurveyEntity> result;
        if (endDate != null) {
            result = surveyRepository.getSurveysPublishedBetween(toLocalDate(publishedAt), toLocalDate(endDate), index, count);
        } else {
            switch (operator) {
                case BEFORE -> result = surveyRepository.getSurveysPublishedBefore(toLocalDate(publishedAt), index, count);
                case AFTER -> result = surveyRepository.getSurveysPublishedAfter(toLocalDate(publishedAt), index, count);
                default -> result = surveyRepository.getSurveysPublishedAt(toLocalDate(publishedAt), index, count);
            }
        }
        return result;
    }

    private List<SurveyEntity> getCreatedAt(int index, int count, Date createdAt, Date endDate, OperatorDTO operator) {
        List<SurveyEntity> result;
        if (endDate != null) {
            result = surveyRepository.getSurveysCreatedBetween(toLocalDate(createdAt), toLocalDate(endDate), index, count);
        } else {
            switch (operator) {
                case BEFORE -> result = surveyRepository.getSurveysCreatedBefore(toLocalDate(createdAt), index, count);
                case AFTER -> result = surveyRepository.getSurveysCreatedAfter(toLocalDate(createdAt), index, count);
                default -> result = surveyRepository.getSurveysCreatedAt(toLocalDate(createdAt), index, count);
            }
        }
        return result;
    }
}
