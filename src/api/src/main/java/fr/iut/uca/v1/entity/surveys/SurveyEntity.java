package fr.iut.uca.v1.entity.surveys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SurveyEntity {

    private String id;

    private String title;

    private LocalDate createdAt;

    private LocalDate publishedAt;

    private LocalDate endAt;

    private String description;

    private final List<QuestionEntity> questions = new ArrayList<>();

    private FeedbackEntity feedback;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDate endAt) {
        this.endAt = endAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
    }

    public Optional<FeedbackEntity> getFeedback() {
        return Optional.ofNullable(feedback);
    }

    public void setFeedback(FeedbackEntity feedback) {
        this.feedback = feedback;
    }
}
