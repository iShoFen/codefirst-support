package fr.iut.uca.model.surveys;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Feedback {

    private final ObjectId id;

    private ObjectId surveyId;

    private final Date createdAt;

    private String author;

    private Question question;

    private final List<String> answers = new ArrayList<>();

    public Feedback(ObjectId id, ObjectId surveyId, Date createdAt, String author, Question question) {
        this.id = id;
        this.surveyId = surveyId;
        this.createdAt = createdAt;
        this.author = author;
        this.question = question;
    }

    public ObjectId getId() {
        return id;
    }

    public ObjectId getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(ObjectId surveyId) {
        this.surveyId = surveyId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
