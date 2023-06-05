package fr.iut.uca.model.surveys;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class Feedback {

    private final String id;

    private String surveyId;

    private final LocalDate createdAt;

    private String author;

    private Question question;

    private final List<String> answers = new ArrayList<>();

    public Feedback(String id, String surveyId,LocalDate createdAt, String author, Question question) {
        this.id = id;
        this.surveyId = surveyId;
        this.createdAt = createdAt;
        this.author = author;
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public LocalDate getCreatedAt() {
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
