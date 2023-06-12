package fr.iut.uca.repository.surveys;

import fr.iut.uca.entity.surveys.SurveyEntity;
import fr.iut.uca.repository.IGenericRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface for survey repository.
 * @see IGenericRepository
 */
public interface ISurveyRepository extends IGenericRepository<SurveyEntity> {

    /**
     * Get surveys created at a specific date.
     * @param createdAt the date of creation
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys created at the specific date
     */
    List<SurveyEntity> getSurveysCreatedAt(LocalDate createdAt, int index, int count);

    /**
     * Get surveys published at a specific date.
     * @param publishedAt the date of publication
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys published at the specific date
     */
    List<SurveyEntity> getSurveysPublishedAt(LocalDate publishedAt, int index, int count);

    /**
     * Get surveys ending at a specific date.
     * @param publishedAt the date of end
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys ending at the specific date
     */
    List<SurveyEntity> getSurveysEndAt(LocalDate publishedAt, int index, int count);

    /**
     * Get surveys created before a specific date.
     * @param createdAt the date of creation
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys created before the specific date
     */
    List<SurveyEntity> getSurveysCreatedBefore(LocalDate createdAt, int index, int count);

    /**
     * Get surveys published before a specific date.
     * @param publishedAt the date of publication
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys published before the specific date
     */
    List<SurveyEntity> getSurveysPublishedBefore(LocalDate publishedAt, int index, int count);

    /**
     * Get surveys ending before a specific date.
     * @param publishedAt the date of end
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys ending before the specific date
     */
    List<SurveyEntity> getSurveysEndBefore(LocalDate publishedAt, int index, int count);

    /**
     * Get surveys created after a specific date.
     * @param createdAt the date of creation
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys created after the specific date
     */
    List<SurveyEntity> getSurveysCreatedAfter(LocalDate createdAt, int index, int count);

    /**
     * Get surveys published after a specific date.
     * @param publishedAt the date of publication
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys published after the specific date
     */
    List<SurveyEntity> getSurveysPublishedAfter(LocalDate publishedAt, int index, int count);

    /**
     * Get surveys ending after a specific date.
     * @param publishedAt the date of end
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys ending after the specific date
     */
    List<SurveyEntity> getSurveysEndAfter(LocalDate publishedAt, int index, int count);

    /**
     * Get surveys created between two dates.
     * @param start the start date
     * @param end the end date
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys created between the two dates
     */
    List<SurveyEntity> getSurveysCreatedBetween(LocalDate start, LocalDate end, int index, int count);

    /**
     * Get surveys published between two dates.
     * @param start the start date
     * @param end the end date
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys published between the two dates
     */
    List<SurveyEntity> getSurveysPublishedBetween(LocalDate start, LocalDate end, int index, int count);

    /**
     * Get surveys ending between two dates.
     * @param start the start date
     * @param end the end date
     * @param index the index of the first survey to get
     * @param count the number of surveys to get
     * @return the list of surveys ending between the two dates
     */
    List<SurveyEntity> getSurveysEndBetween(LocalDate start, LocalDate end, int index, int count);

    /**
     * Get surveys with feedbacks
     * @param id the id of the survey
     * @param index the index of the first feedback to get
     * @param count the number of feedbacks to get
     * @return the survey with feedbacks
     */
    Optional<SurveyEntity> getSurveyWithFeedbacks(String id, int index, int count);
}
