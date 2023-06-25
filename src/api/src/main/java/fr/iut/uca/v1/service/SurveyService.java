package fr.iut.uca.v1.service;

import fr.iut.uca.exception.InsertException;
import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.v1.dto.OperatorDTO;
import fr.iut.uca.v1.dto.surveys.survey.*;
import fr.iut.uca.v1.entity.surveys.SurveyEntity;
import fr.iut.uca.v1.extension.surveys.QuestionExtensions;
import fr.iut.uca.v1.extension.surveys.SurveyExtensions;
import fr.iut.uca.v1.model.surveys.Survey;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.v1.repository.surveys.ISurveyRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static fr.iut.uca.v1.extension.DateExtensions.toLocalDate;

/**
 * Survey service
 */
@ApplicationScoped
public class SurveyService {

    /**
     * Survey repository
     */
    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    ISurveyRepository surveyRepository;

    /**
     * Get all surveys with pagination and filters
     * @param surveyGetDTO DTO with filters
     * @return List of surveys
     * @throws IllegalArgumentException if filters are invalid
     */
    public List<SurveyDTO> getAll(SurveyGetDTO surveyGetDTO) throws IllegalArgumentException {
        List<SurveyEntity> result;
        int index = surveyGetDTO.getIndex();
        int count = surveyGetDTO.getCount();
        Date createdAt = surveyGetDTO.getCreatedAt();
        Date publishedAt = surveyGetDTO.getPublishedAt();
        Date endAt = surveyGetDTO.getEndAt();
        Date endDate = surveyGetDTO.getEndDate();
        OperatorDTO operator = surveyGetDTO.getOperator();

        if (createdAt != null) {
            result = getCreatedAt(index, count, createdAt, endDate, operator);
        } else if (publishedAt != null) {
            result = getPublishedAt(index, count, publishedAt, endDate, operator);
        } else if (endAt != null) {
            result = getEndAt(index, count, endAt, endDate, operator);
        } else {
            result = surveyRepository.getItems(index, count);
        }

        List<Survey> surveys = SurveyExtensions.entitiesToModels(result);
        return SurveyExtensions.modelsToDTOs(surveys);
    }

    /**
     * Get all surveys for endAt date
     * @param index index
     * @param count count
     * @param endAt endAt date
     * @param endDate end date
     * @param operator operator
     * @return List of surveys
     */
    private List<SurveyEntity> getEndAt(int index, int count, Date endAt, Date endDate, OperatorDTO operator) {
        List<SurveyEntity> result;
        if (endDate != null) {
            result = surveyRepository.getSurveysEndBetween(toLocalDate(endAt), toLocalDate(endDate), index, count);

        } else if (operator == null) {
            result = surveyRepository.getSurveysEndAt(toLocalDate(endAt), index, count);
        } else {
            switch (operator) {
                case BEFORE -> result = surveyRepository.getSurveysEndBefore(toLocalDate(endAt), index, count);
                case AFTER -> result = surveyRepository.getSurveysEndAfter(toLocalDate(endAt), index, count);
                default -> result = surveyRepository.getSurveysEndAt(toLocalDate(endAt), index, count);
            }
        }
        return result;
    }

    /**
     * Get all surveys for publishedAt date
     * @param index index
     * @param count count
     * @param publishedAt publishedAt date
     * @param endDate end date
     * @param operator operator
     * @return List of surveys
     */
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

    /**
     * Get all surveys for createdAt date
     * @param index index
     * @param count count
     * @param createdAt createdAt date
     * @param endDate end date
     * @param operator operator
     * @return List of surveys
     */
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

    /**
     * Get one survey by id
     * @param id id
     * @return Survey
     * @throws NotFoundException if survey not found
     */
    public SurveyDetailDTO getOne(String id) throws NotFoundException {
        Optional<SurveyEntity> surveyEntity = surveyRepository.getItemById(id);

        if (surveyEntity.isEmpty()) {
            throw new NotFoundException(String.format("Survey with id %s not found", id));
        }

        Survey survey = SurveyExtensions.entityToModel(surveyEntity.get());
        return SurveyExtensions.modelToDetailDTO(survey);
    }

    /**
     * Create a survey
     * @param surveyInsertDTO survey to create
     * @return Survey created
     * @throws IllegalArgumentException if survey is invalid
     * @throws InsertException if an error occurred while inserting the survey
     */
    public SurveyDetailDTO create(SurveyInsertDTO surveyInsertDTO) throws IllegalArgumentException, InsertException {
        var survey = new Survey(
                surveyInsertDTO.title(),
                surveyInsertDTO.createdAt(),
                surveyInsertDTO.publishedAt(),
                surveyInsertDTO.endAt(),
                surveyInsertDTO.description(),
                QuestionExtensions.dtosToModels(surveyInsertDTO.questions()));

        var result = surveyRepository.addItem(SurveyExtensions.modelToEntity(survey));

        if (result.isEmpty()) {
            throw new InsertException("An error occurred while inserting the survey");
        }

        var resultSurvey = SurveyExtensions.entityToModel(result.get());
        return SurveyExtensions.modelToDetailDTO(resultSurvey);
    }

    /**
     * Update a survey
     * @param id id
     * @param surveyUpdateDTO survey to update
     * @return Survey updated
     * @throws NotFoundException if survey not found
     * @throws IllegalArgumentException if survey is invalid
     * @throws UpdateException if an error occurred while updating the survey
     */
    public SurveyDetailDTO update(String id, SurveyUpdateDTO surveyUpdateDTO) throws NotFoundException, IllegalArgumentException, UpdateException {
        Optional<SurveyEntity> surveyEntity = surveyRepository.getItemById(id);

        if (surveyEntity.isEmpty()) {
            throw new NotFoundException(String.format("Survey with id %s not found", id));
        }

        var survey = SurveyExtensions.entityToModel(surveyEntity.get());
        survey.setTitle(surveyUpdateDTO.title());
        survey.setPublishedAt(surveyUpdateDTO.publishedAt());
        survey.setEndAt(surveyUpdateDTO.endAt());
        survey.setDescription(surveyUpdateDTO.description());
        surveyUpdateDTO.questions().forEach(questionUpdateDTO -> survey.updateQuestion(QuestionExtensions.dtoToModel(questionUpdateDTO)));

        var result = surveyRepository.updateItem(survey.getId(), SurveyExtensions.modelToEntity(survey));

        if (result.isEmpty()) {
            throw new UpdateException("An error occurred while updating the survey");
        }

        var resultSurvey = SurveyExtensions.entityToModel(result.get());
        return SurveyExtensions.modelToDetailDTO(resultSurvey);
    }

    /**
     * Delete a survey
     * @param id id
     * @throws NotFoundException if survey not found
     */
    public void delete(String id) throws NotFoundException {
        boolean result = surveyRepository.deleteItem(id);

        if (!result) {
            throw new NotFoundException(String.format("Survey with id %s not found", id));
        }
    }
}
