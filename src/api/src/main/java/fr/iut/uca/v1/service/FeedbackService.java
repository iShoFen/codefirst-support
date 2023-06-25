package fr.iut.uca.v1.service;

import fr.iut.uca.exception.InsertException;
import fr.iut.uca.exception.UpdateException;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.v1.dto.OperatorDTO;
import fr.iut.uca.v1.dto.surveys.feedback.*;
import fr.iut.uca.v1.entity.surveys.FeedbackEntity;
import fr.iut.uca.v1.extension.DateExtensions;
import fr.iut.uca.v1.extension.issues.IssueStatusExtensions;
import fr.iut.uca.v1.extension.surveys.FeedbackExtensions;
import fr.iut.uca.v1.extension.surveys.QuestionExtensions;
import fr.iut.uca.v1.extension.surveys.QuestionInfoExtensions;
import fr.iut.uca.v1.model.surveys.Feedback;
import fr.iut.uca.v1.repository.surveys.IFeedbackRepository;
import fr.iut.uca.v1.repository.surveys.ISurveyRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@ApplicationScoped
public class FeedbackService {

    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    IFeedbackRepository feedbackRepository;

    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    ISurveyRepository surveyRepository;

    private void searchSurvey(String surveyId) throws NotFoundException {
        if (surveyRepository.getItemById(surveyId).isEmpty()) {
            throw new NotFoundException(String.format("Survey with id %s not found", surveyId));
        }
    }

    public List<FeedbackDTO> getAll(String surveyId, FeedbackGetDTO feedbackGetDTO) throws NotFoundException {
        searchSurvey(surveyId);

        var index = feedbackGetDTO.getIndex();
        var count = feedbackGetDTO.getCount();
        var author = feedbackGetDTO.getAuthor();
        var createdAt = feedbackGetDTO.getCreatedAt();
        List<FeedbackEntity> result;
        if (author != null) {
            result = feedbackRepository.getFeedbackFromSurveyAndAuthor(surveyId, author, index, count);
        } else if (createdAt != null) {
            result = getCreatedAT(surveyId, index, count, feedbackGetDTO.getOperator(), createdAt, feedbackGetDTO.getEndDate());
        } else {
            result = feedbackRepository.getFeedbackFromSurvey(surveyId, index, count);
        }

        List<Feedback> feedbacks = FeedbackExtensions.entitiesToModels(result);
        return FeedbackExtensions.modelsToDTOs(feedbacks);
    }

    private List<FeedbackEntity> getCreatedAT(String surveyId, int index, int count, OperatorDTO operator, Date createdAt, Date endDate) {
        List<FeedbackEntity> result;
        var createdAtLocalDate = DateExtensions.toLocalDate(createdAt);
        if (endDate != null) {
            result = feedbackRepository.getFeedbackFromSurveyBetweenDates(surveyId, createdAtLocalDate, DateExtensions.toLocalDate(endDate), index, count);
        } else if (operator == null) {
            result = feedbackRepository.getFeedbackFromSurveyAtDate(surveyId, createdAtLocalDate, index, count);
        } else {
            switch (operator) {
                case BEFORE -> result = feedbackRepository.getFeedbackFromSurveyBeforeDate(surveyId, createdAtLocalDate, index, count);
                case AFTER -> result = feedbackRepository.getFeedbackFromSurveyAfterDate(surveyId, createdAtLocalDate, index, count);
                default -> result = feedbackRepository.getFeedbackFromSurveyAtDate(surveyId, createdAtLocalDate, index, count);
            }
        }

        return result;
    }

    private Optional<FeedbackEntity> getFeedbackEntity(String surveyId, String feedbackId) throws NotFoundException {
        var feedbackEntity = feedbackRepository.getItemById(feedbackId);
        if (feedbackEntity.isEmpty()) {
            throw new NotFoundException(String.format("Feedback with id %s not found", feedbackId));
        }
        if (!feedbackEntity.get().getSurveyId().equals(surveyId)) {
            throw new NotFoundException(String.format("Feedback with id %s not found in survey with id %s", feedbackId, surveyId));
        }
        return feedbackEntity;
    }

    public FeedbackDetailDTO getOne(String surveyId, String feedbackId) throws NotFoundException {
        searchSurvey(surveyId);

        Optional<FeedbackEntity> feedbackEntity = getFeedbackEntity(surveyId, feedbackId);

        var feedback = FeedbackExtensions.entityToModel(feedbackEntity.get());
        return FeedbackExtensions.modelToDetailDTO(feedback);
    }

    public FeedbackDetailDTO create(String surveyId, FeedbackInsertDTO feedbackInsertDTO) throws NotFoundException, IllegalArgumentException, InsertException {
        searchSurvey(surveyId);

        var feedback = new Feedback(
                feedbackInsertDTO.surveyId(),
                feedbackInsertDTO.createdAt(),
                feedbackInsertDTO.author(),
                QuestionInfoExtensions.dtoToModel(feedbackInsertDTO.question()),
                feedbackInsertDTO.answer()
        );

        var result = feedbackRepository.addItem(FeedbackExtensions.modelToEntity(feedback));

        if (result.isEmpty()) {
            throw new InsertException("Feedback not inserted");
        }

        var resultFeedback = FeedbackExtensions.entityToModel(result.get());
        return FeedbackExtensions.modelToDetailDTO(resultFeedback);
    }

    public FeedbackDetailDTO update(String surveyId, String feedbackId, FeedbackUpdateDTO feedbackUpdateDTO) throws NotFoundException, IllegalArgumentException, UpdateException {
        searchSurvey(surveyId);
        Optional<FeedbackEntity> feedbackEntity = getFeedbackEntity(surveyId, feedbackId);

        var feedback = FeedbackExtensions.entityToModel(feedbackEntity.get());
        feedback.updateAnswers(feedbackUpdateDTO.answer());

        var result = feedbackRepository.updateItem(FeedbackExtensions.modelToEntity(feedback));
        if (result.isEmpty()) {
            throw new UpdateException("An error occurred while updating the feedback");
        }

        var resultFeedback = FeedbackExtensions.entityToModel(result.get());
        return FeedbackExtensions.modelToDetailDTO(resultFeedback);
    }

    public void delete(String surveyId, String feedbackId) {
        searchSurvey(surveyId);

        boolean result = feedbackRepository.deleteItem(feedbackId);

        if (!result) {
            throw new NotFoundException(String.format("Feedback with id %s not found", feedbackId));
        }
    }
}
