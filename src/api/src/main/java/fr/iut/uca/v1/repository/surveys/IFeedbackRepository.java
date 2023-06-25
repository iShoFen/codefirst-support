package fr.iut.uca.v1.repository.surveys;

import fr.iut.uca.v1.entity.surveys.FeedbackEntity;
import fr.iut.uca.v1.entity.surveys.SurveyEntity;
import fr.iut.uca.v1.repository.IGenericRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for feedback repository.
 * Currently empty. But created for future use if needed.
 * @see IGenericRepository
 */
public interface IFeedbackRepository extends IGenericRepository<FeedbackEntity> {

    /**
     * Get survey with feedback.
     * @param feedbackId Feedback id
     * @return Survey with feedbacks
     */
    SurveyEntity getSurveyWithFeedback(String feedbackId);

    /**
     * Get feedbacks from a survey.
     * @param surveyId Survey id
     * @param index Index of the first feedback
     * @param count Number of feedbacks
     * @return List of feedbacks from a survey
     */
    List<FeedbackEntity> getFeedbackFromSurvey(String surveyId, int index, int count);

    /**
     * Get feedbacks from a survey and an author.
     * @param surveyId Survey id
     * @param mail Author mail
     * @param index Index of the first feedback
     * @param count Number of feedbacks
     * @return List of feedbacks from a survey and an author
     */
    List<FeedbackEntity> getFeedbackFromSurveyAndAuthor(String surveyId, String mail, int index, int count);

    /**
     * Get feedbacks from a survey at a date.
     * @param surveyId Survey id
     * @param date Date
     * @param index Index of the first feedback
     * @param count Number of feedbacks
     * @return List of feedbacks from a survey at a date
     */
    List<FeedbackEntity> getFeedbackFromSurveyAtDate(String surveyId, LocalDate date, int index, int count);

    /**
     * Get feedbacks from a survey before a date.
     * @param surveyId Survey id
     * @param date Date
     * @param index Index of the first feedback
     * @param count Number of feedbacks
     * @return List of feedbacks from a survey before a date
     */
    List<FeedbackEntity> getFeedbackFromSurveyBeforeDate(String surveyId, LocalDate date, int index, int count);

    /**
     * Get feedbacks from a survey after a date.
     * @param surveyId Survey id
     * @param date Date
     * @param index Index of the first feedback
     * @param count Number of feedbacks
     * @return List of feedbacks from a survey after a date
     */
    List<FeedbackEntity> getFeedbackFromSurveyAfterDate(String surveyId, LocalDate date, int index, int count);

    /**
     * Get feedbacks from a survey between two dates.
     * @param surveyId Survey id
     * @param date1 First date
     * @param date2 Second date
     * @param index Index of the first feedback
     * @param count Number of feedbacks
     * @return List of feedbacks from a survey between two dates
     */
    List<FeedbackEntity> getFeedbackFromSurveyBetweenDates(String surveyId, LocalDate date1, LocalDate date2, int index, int count);







}
