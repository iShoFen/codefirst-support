package fr.iut.uca.model.surveys;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Feedback {

    private final String id;

    private String surveyId;

    private final LocalDate createdAt;

    private String author;

    private Question question;

    private final List<String> answers = new ArrayList<>();

    public Feedback(String id, String surveyId,LocalDate createdAt, String author, Question question, List<String> answers) {
        this.id = id;
        this.surveyId = surveyId;
        this.createdAt = createdAt;
        this.author = author;
        this.question = question;
        this.answers.addAll(answers);
    }

    public Feedback(String id, String surveyId,LocalDate createdAt, String author, Question question) {
        this(id, surveyId, createdAt, author, question, Collections.emptyList());
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
