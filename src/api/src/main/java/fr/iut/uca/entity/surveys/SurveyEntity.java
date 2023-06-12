package fr.iut.uca.entity.surveys;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SurveyEntity {

    @BsonProperty("_id")
    private ObjectId id;

    private String title;

    @BsonProperty("created_at")
    private LocalDate createdAt;

    @BsonProperty("published_at")
    private LocalDate publishedAt;

    @BsonProperty("end_at")
    private LocalDate endAt;

    private String description;

    private List<QuestionEntity> questions;

    private List<FeedbackEntity> feedbacks;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
//        this.questions.clear();
//        this.questions.addAll(questions);
        this.questions = questions;
    }

    public List<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackEntity> feedbacks) {
//        this.feedbacks.clear();
//        this.feedbacks.addAll(feedbacks);
        this.feedbacks = feedbacks;
    }
}
