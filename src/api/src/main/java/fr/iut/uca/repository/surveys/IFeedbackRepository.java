package fr.iut.uca.repository.surveys;

import fr.iut.uca.entity.surveys.FeedbackEntity;
import fr.iut.uca.entity.surveys.SurveyEntity;
import fr.iut.uca.repository.IGenericRepository;

/**
 * Interface for feedback repository.
 * Currently empty. But created for future use if needed.
 * @see IGenericRepository
 */
public interface IFeedbackRepository extends IGenericRepository<FeedbackEntity> {

    /**
     * Get survey with feedbacks.
     * @param feedbackId Feedback id
     * @return Survey with feedbacks
     */
    SurveyEntity getSurveyWithFeedbacks(String feedbackId);
}
