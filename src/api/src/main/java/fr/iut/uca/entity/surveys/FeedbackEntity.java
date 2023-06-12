package fr.iut.uca.entity.surveys;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FeedbackEntity {

    @BsonProperty("_id")
    private ObjectId id;

    @BsonProperty("survey_id")
    private ObjectId surveyId;

    @BsonProperty("created_at")
    private LocalDate createdAt;

    private String author;

    private QuestionEntity question;

    private List<String> answers;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(ObjectId surveyId) {
        this.surveyId = surveyId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
//        this.answers.clear();
//        this.answers.addAll(answers);
        this.answers = answers;
    }
}
