package fr.iut.uca.v1.model.surveys;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Feedback {

    private final String id;

    private String surveyId;

    private final LocalDate createdAt;

    private String author;

    private QuestionInfo question;

    private final List<String> answers = new ArrayList<>();

    public Feedback(String id, String surveyId,LocalDate createdAt, String author, QuestionInfo question, List<String> answers) {
        this.id = id;
        this.surveyId = surveyId;
        this.createdAt = createdAt;
        this.author = author;
        this.question = question;
        this.answers.addAll(answers);
    }

    public Feedback(String surveyId,LocalDate createdAt, String author, QuestionInfo question, List<String> answers) {
        this(null, surveyId, createdAt, author, question, answers);
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

    public QuestionInfo getQuestion() {
        return question;
    }

    public void setQuestion(QuestionInfo question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void updateAnswers(List<String> answers) {
        this.answers.clear();
        this.answers.addAll(answers);
    }
}
